/*----------------------------添加修改操作相关--------------------------------*/

//教师管理
var index;
function _iframe1() {
    index=layer.open({
        type:2,
        title:flag==1?'修改':'添加',
        skin: 'layui-layer-demo', //样式类名
        area: ['420px', '540px'], //宽高
        offset: '20px',
        closeBtn: 0, //不显示关闭按钮
        anim: 4,
        shadeClose: true, //开启遮罩关闭
        content: flag==1?[path+'/grade/selectById.action?gradeId='+id , 'no']
            :['../../admin/frame/gradeedit.jsp', 'no']
    })
}