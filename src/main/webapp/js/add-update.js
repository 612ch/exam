var index;
function _iframe(flag,url) {
	// path+'/student/selectByPrimaryKey.action?studentId='+id
	// alert(url);
	index=layer.open({
		type:2,
		title:flag==1?'修改':'添加',
		skin: 'layui-layer-demo', //样式类名
		area: ['420px', '540px'], //宽高
		offset: '20px',
		closeBtn: 0, //不显示关闭按钮
		anim: 4,
		shadeClose: true, //开启遮罩关闭
        content: [path+url,'no']
	})
}function exam_iframe(flag,url) {
	// path+'/student/selectByPrimaryKey.action?studentId='+id
	// alert(url);
	index=layer.open({
		type:2,
		title:flag==1?'修改':'添加',
		skin: 'layui-layer-demo', //样式类名
		area: ['70%', '90%'], //宽高
		offset: '20px',
		closeBtn: 0, //不显示关闭按钮
		anim: 4,
		shadeClose: true, //开启遮罩关闭
        content: [path+url,'no']
	})
}
function subject_iframe(flag,url) {
	// path+'/student/selectByPrimaryKey.action?studentId='+id
	// alert(url);
	index=layer.open({
		type:2,
		title:flag==1?'修改':'添加',
		skin: 'layui-layer-demo', //样式类名
		area: ['1100px', '540px'], //宽高
		offset: '20px',
		closeBtn: 0, //不显示关闭按钮
		anim: 4,
		shadeClose: true, //开启遮罩关闭
		content: path+url
	})
}

function  onSubmit(flag,url,action){
	// path+"/student/updateStudent.action"
	// "#studentAction"
	var msg;
	if(flag==1){
		msg='修改成功';
	}else{
		msg='添加成功';
	}
	$.ajax(
		{
			type:"POST",
			url:path+url,
			data:$(action).serialize(),
			success:function (data) {
				layer.msg(msg,{
						icon: 1,shade: 0.01,time:1000
					},function(){
						parent.layer.close(index);
						parent.location.reload();
					})

			}
		}
	)
}
