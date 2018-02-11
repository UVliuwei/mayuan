layui.define(['layer', 'laytpl', 'form', 'element', 'upload', 'util'], function (exports) {

    var $ = layui.jquery
        , layer = layui.layer
        , form = layui.form;

    //监听提交
    form.on('submit(form)', function (data) {
        var action = $(data.form).attr('action');
        var type = $(data.form).attr('method');
        $.ajax({
            type: type,
            url: action,
            data: data.field,
            success: function (res) {
                if (res.status === "-1") {
                    layer.msg(res.msg, {
                        time: 2000,//2秒关闭（如果不配置，默认是3秒）
                        anim: 6
                    });
                } else {
                    if (res.action != null && res.action != "") {
                        if (res.msg != null && res.msg != "") {
                            layer.msg(res.msg, {
                                icon: 1,
                                time: 1000 //1秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                window.location.href = res.action
                            });
                        } else {
                            window.location.href = res.action;
                        }
                    } else {
                        layer.msg(res.msg);
                    }

                }
            }
        });
        return false;
    });
});

function getUserId() {
    var id = -1;
    if(document.cookie.length>0) {
        var cookies = document.cookie.split(";");
        for(var i=0; i<cookies.length; i++) {
            var cookie = cookies[i];
            var sp = cookie.split("=");
            if(sp[0] == "myuan_id") {
                id = sp[1];
                break;
            }
        }
    }
    return id;
}