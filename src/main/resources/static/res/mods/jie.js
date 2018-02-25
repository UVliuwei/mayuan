/**

 @Name: 求解板块

 */

layui.define('fly', function (exports) {

    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var laytpl = layui.laytpl;
    var form = layui.form;
    var fly = layui.fly;

    var gather = {}, dom = {
        jieda: $('#jieda')
        , content: $('#L_content')
        , jiedaCount: $('#jiedaCount')
    };

    //监听专栏选择
    form.on('select(column)', function (obj) {
        var value = obj.value
            , elemQuiz = $('#LAY_quiz')
            , tips = {
            tips: 1
            , maxWidth: 250
            , time: 10000
        };
        elemQuiz.addClass('layui-hide');
        if (value === '0') {
            layer.tips('下面的信息将便于您获得更好的答案', obj.othis, tips);
            elemQuiz.removeClass('layui-hide');
        } else if (value === '99') {
            layer.tips('系统会对【分享】类型的帖子予以飞吻奖励，但我们需要审核，通过后方可展示', obj.othis, tips);
        }
    });

    //提交回答
    fly.form['/api/answer'] = function (data, required) {
        var tpl = '<li>\
      <div class="detail-about detail-about-reply">\
        <a class="fly-avatar" href="/u/{{ layui.cache.user.uid }}" target="_blank">\
          <img src="{{= d.user.avatar}}" alt="{{= d.user.username}}">\
        </a>\
        <div class="fly-detail-user">\
          <a href="/u/{{ layui.cache.user.uid }}" target="_blank" class="fly-link">\
            <cite>{{d.user.username}}</cite>\
          </a>\
        </div>\
        <div class="detail-hits">\
          <span>刚刚</span>\
        </div>\
      </div>\
      <div class="detail-body jieda-body photos">\
        {{ d.content}}\
      </div>\
    </li>'
        data.content = fly.content(data.content);
        laytpl(tpl).render($.extend(data, {
            user: layui.cache.user
        }), function (html) {
            required[0].value = '';
            dom.jieda.find('.fly-none').remove();
            dom.jieda.append(html);

            var count = dom.jiedaCount.text() | 0;
            dom.jiedaCount.html(++count);
        });
    };

    //求解管理
    gather.jieAdmin = {
        //删求解
        del: function (div) {
            layer.confirm('确认删除该求解么？', function (index) {
                layer.close(index);
                var id = div.attr("id");
                $.ajax({
                    type: 'delete',
                    url: '/api/post/' + id,
                    success: function (res) {
                        if (res.status == '-1') {
                            layer.msg(res.msg, {
                                time: 2000,//2秒关闭（如果不配置，默认是3秒）
                                anim: 6
                            });
                        } else {
                            layer.msg(res.msg, {
                                icon: 1,
                                time: 1000 //1秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                window.location.href = res.action
                            });
                        }
                    }
                });
            });
        }

        //设置置顶、状态
        , set: function (div) {
            var othis = $(this);
            var id = div.attr("id");
            var rank = othis.attr('rank');
            var field = othis.attr('field');
            $.ajax({
                type: 'put',
                url: '/api/post/' + id + '/' + field + '/' + rank,
                success: function (res) {
                    if (res.status == '-1') {
                        layer.msg(res.msg, {
                            time: 2000,//2秒关闭（如果不配置，默认是3秒）
                            anim: 6
                        });
                    } else {
                        location.reload();
                    }
                }
            });
        }

        //收藏
        , collect: function (div) {
            var othis = $(this), type = othis.attr('ctype');
            var cdiv = $('.fly-admin-box');
            var cid = cdiv.attr('id');
            var uid = layui.cache.user.uid;
            if (type == 'add') {
                $.ajax({
                    type: 'post',
                    url: '/api/post/' + cid + '/user/' + uid,
                    success: function (res) {
                        othis.attr('ctype', 'remove').html('取消收藏').addClass('layui-btn-danger');
                    }
                });
            } else {
                $.ajax({
                    type: 'delete',
                    url: '/api/post/' + cid + '/user/' + uid,
                    success: function (res) {
                        othis.attr('ctype', 'add').html('收藏').removeClass('layui-btn-danger');
                    }
                });
            }

        }
    };

    $('body').on('click', '.jie-admin', function () {
        var othis = $(this), type = othis.attr('type');
        gather.jieAdmin[type] && gather.jieAdmin[type].call(this, othis.parent());
    });

    //异步渲染
    var asyncRender = function () {
        var div = $('.fly-admin-box'), jieAdmin = $('#LAY_jieAdmin');
        var cid = div.attr('id');
        var uid = layui.cache.user.uid;
        //查询帖子是否收藏
        if (jieAdmin[0] && layui.cache.user.uid != -1) {
            $.ajax({
                type: 'get',
                url: '/api/post/' + cid + '/user/' + uid,
                success: function (res) {
                    if (res == null || res == '') {
                        jieAdmin.append('<span class="layui-btn layui-btn-xs jie-admin" type="collect" ctype="add" data-th-type="add">收藏</span>');
                    } else {
                        jieAdmin.append(
                            '<span class="layui-btn layui-btn-xs jie-admin layui-btn-danger" type="collect" ctype="remove" data-th-type="remove">取消收藏</span>');
                    }
                }
            });
        }
    }();

    //解答操作
    gather.jiedaActive = {
        zan: function (li) { //赞
            var othis = $(this), ok = othis.hasClass('zanok');
            fly.json('/api/jieda-zan/', {
                ok: ok
                , id: li.data('id')
            }, function (res) {
                if (res.status === 0) {
                    var zans = othis.find('em').html() | 0;
                    othis[ok ? 'removeClass' : 'addClass']('zanok');
                    othis.find('em').html(ok ? (--zans) : (++zans));
                } else {
                    layer.msg(res.msg);
                }
            });
        }
        , reply: function (li) { //回复
            var val = dom.content.val();
            var aite = '@' + li.find('.fly-detail-user cite').text().replace(/\s/g, '');
            dom.content.focus()
            if (val.indexOf(aite) !== -1) {
                return;
            }
            dom.content.val(aite + ' ' + val);
        }
        , accept: function (li) { //采纳
            var cdiv = $('.fly-admin-box');
            var cid = cdiv.attr('id');
            var othis = $(this);
            var id = li.attr('id');
            layer.confirm('是否采纳该回答为最佳答案？', function (index) {
                layer.close(index);
                fly.json('/api/post/' + cid + "/accepted/" + id, {
                }, function (res) {
                    if (res.status == '1') {
                        $('.jieda-accept').remove();
                        li.addClass('jieda-daan');
                        li.find('.detail-about').append('<i class="iconfont icon-caina" title="最佳答案"></i>');
                    } else {
                        layer.msg(res.msg);
                    }
                },{type : 'put'});
            });
        }
        , del: function (li) { //删除
            layer.confirm('确认删除该回答么？', function (index) {
                layer.close(index);
                var id = li.attr('id');
                var flag = 'false';
                if (li.hasClass('jieda-daan')) {
                    flag = 'true';
                }
                fly.json('/api/answer/' + id + '/' + flag, {
                }, function (res) {
                    if (res.status == '1') {
                        var count = dom.jiedaCount.text() | 0;
                        dom.jiedaCount.html(--count);
                        li.remove();
                        //如果删除了最佳答案
                        if (li.hasClass('jieda-daan')) {
                            $('.jie-status').removeClass('jie-status-ok').text('求解中');
                        }
                    } else {
                        layer.msg(res.msg);
                    }
                },{type : 'delete'});
            });
        }
    };

    $('.jieda-reply span').on('click', function () {
        var othis = $(this), type = othis.attr('type');
        gather.jiedaActive[type].call(this, othis.parents('li'));
    });

    //定位分页
    if (/\/page\//.test(location.href) && !location.hash) {
        var replyTop = $('#flyReply').offset().top - 80;
        $('html,body').scrollTop(replyTop);
    }

    exports('jie', null);
});