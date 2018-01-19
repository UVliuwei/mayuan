layui.define(['layer', 'laytpl', 'form', 'element', 'upload', 'util'], function(exports){
  
  var $ = layui.jquery
  ,layer = layui.layer
  ,form = layui.form;

	  //监听提交
	  form.on('submit(form)', function(data){
	   var action = $(data.form).attr('action');
	   var method = $(data.form).attr('method');
		  $.ajax({
              type: method,
              url:action,
              data:data.field,
              success: function(res) {
            	var res = $.parseJSON(res);
              	if(res.status==="0") {
              		layer.msg(res.msg,{
          				  time: 2000 ,//2秒关闭（如果不配置，默认是3秒）
          				  anim:6
          				});
              	}else {
              		if(res.action != null && res.action != "") {
              			if(res.msg != null && res.msg != "") {
              				layer.msg(res.msg,{
              				  icon: 1,
              				  time: 1000 //1秒关闭（如果不配置，默认是3秒）
              				},function() {
              					window.location.href=res.action;
              				});
              			} else {
              				window.location.href=res.action;
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