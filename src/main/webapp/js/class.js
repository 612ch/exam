function addClass() {
    layer.open({
        type: 1 //Page层类型
        ,area: ['500px', '300px']
        ,title: '添加班级'
        ,shade: 0.6 //遮罩透明度
        ,maxmin: false //允许全屏最小化
        ,anim: 1 //0-6的动画形式，-1不开启
        ,content:["/class/toAddClass.action","no"],
    });
}
function delClass(classid) {
    layer.confirm('您确定要删除吗？', {
        btn: ['删除','取消'] //按钮
    }, function(){
             $.ajax(
            {
                type:"POST",
                url:path+"/class/deleteClass.action",
                data:{"cid":classid},
                success:function (data) {
                    var state=data.state;
                    if(state==1){
                        layer.alert('啊哦，删除成功了叭', {
                            skin: 'layui-layer-lan'
                            ,closeBtn: 0
                            ,anim: 4
                        },function() {
                            location.href =path+"/class/classList.action";
                        })
                    }else if(state==-1){
                        layer.alert('啊哦，删除失败该班级下有学生', {
                            skin: 'layui-layer-lan'
                            ,closeBtn: 0
                            ,anim: 4
                        })
                    } else {
                        layer.alert('啊哦，删除失败了叭', {
                            skin: 'layui-layer-lan'
                            ,closeBtn: 0
                            ,anim: 4
                        })
                    }

                }
            }
        )


    },function(){
        layer.msg('取消删除', {icon: 2});
    } );
}