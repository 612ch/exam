// REST 风格请求
// function update(url) {
// 	$("form input").attr("disabled", "disabled");
// 	$("form").attr("method", "get");
// 	$("form").attr("action", url).submit();
// 	return false;
// }
function del(ID,url) {
    layer.confirm('是否确认删除？', {
        btn: ['Yes','No'], //按钮
        offset: '200px',
        closeBtn: 0, //不显示关闭按钮
        anim: 4,
        shadeClose: true, //开启遮罩关闭
    }, function(){
        $.ajax(
            {
                type:"POST",
                url:path+url,
                data:{"ID":ID},
                success:function (data) {
                    layer.msg('删除成功', {icon: 1,offset: '200px',time:800},function () {
                        location.reload();
                    });
                }
            }
        )
    }, function(){
        layer.msg('删除已取消',{offset: '200px',time:800},function () {
            location.reload();
        });
    });

}

