function login() {
    var a= layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
/*    layer.msg('登陆中', {
        icon: 16
        ,shade: 0.01
    });*/
    var username=$("#username").val();
    var pwd=$("#pwd").val();
    if(username==null||username==''){
        $(".error-msg-name").css("display","inline-block");
        $(".error-msg-name").html("请输入用户名！");
        layer.close(a);
    }
    if(pwd==null||pwd==''){
        $(".error-msg-pwd").css("display","inline-block");
        $(".error-msg-pwd").html("请输入密码！");
        layer.close(a);

    }
    $.ajax(
        {
            type:"POST",
            url:path+"/teacher/login.action",
            data:{"username":username,"pwd":pwd},
            success:function (data) {
                layer.close(a);
                var state=data.state;
                if(state==0){
                    layer.msg(data.msg,{
                        icon: 1,shade: 0.01,time:600
                    },function() {
                        location.href =path+"/admin/index.jsp";
                    })
                }else if(state==1){
                    layer.msg(data.msg, {
                        icon: 7,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else if(state==2){
                    layer.msg(data.msg, {
                        icon: 2,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else if(state==3){
                    layer.msg(data.msg, {
                        icon: 5
                        ,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else{
                    layer.msg("登陆失败", {
                        icon: 8
                        ,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }
            }
        }
    )
}
function toStuLogin() {
    zeroModal.show({
        title : '登 录',
        iframe : true,
        url : path+'/reception/login.jsp',
        width : '30%',
        height : '50%',
        top : '100px',
        left : '430px',
        esc : true,
        overlay : true,
        onClosed : function() {
            location.reload();
        }
    });
}
function toStuRegister() {
    zeroModal.show({
        title : '登 录',
        iframe : true,
        url : path+'/student/toRegister.action',
        width : '30%',
        height : '50%',
        top : '100px',
        left : '430px',
        esc : true,
        overlay : true,
        onClosed : function() {
            location.reload();
        }
    });
}

function stuLogin() {
    var a= layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    /*    layer.msg('登陆中', {
            icon: 16
            ,shade: 0.01
        });*/
    var username=$("#studentAccount").val();
    var pwd=$("#studentPwd").val();
    if(username==null||username==''){
        $(".error-msg-name").css("display","inline-block");
        $(".error-msg-name").html("请输入用户名！");
        layer.close(a);
    }
    if(pwd==null||pwd==''){
        $(".error-msg-pwd").css("display","inline-block");
        $(".error-msg-pwd").html("请输入密码！");
        layer.close(a);

    }
    $.ajax(
        {
            type:"POST",
            url:path+"/student/login.action",
            data:{"username":username,"pwd":pwd},
            success:function (data) {
                layer.close(a);
                var state=data.state;
                if(state==0){
                    layer.msg(data.msg,{
                        icon: 1,shade: 0.01,time:600
                    },function() {

                        /*var indexifame = parent.layer.getFrameIndex(window.name);
                        alert(indexifame);
                        parent.layer.close(indexifame);*/

                        parent.location.href=path+"/index.jsp";
                    })
                }else if(state==1){
                    layer.msg(data.msg, {
                        icon: 7,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else if(state==2){
                    layer.msg(data.msg, {
                        icon: 2,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else if(state==3){
                    layer.msg(data.msg, {
                        icon: 5
                        ,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else{
                    layer.msg("登陆失败", {
                        icon: 8
                        ,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }
            }
        }
    )
}
function stuReg() {
    var a= layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    /*    layer.msg('登陆中', {
            icon: 16
            ,shade: 0.01
        });*/
    var studentName=$("#studentName").val();
    var studentAccount=$("#studentAccount").val();
    var studentPwd=$("#studentPwd").val();
    var classId=$("#classId").val();
    $.ajax(
        {
            type:"POST",
            url:path+"/student/register.action",
            data:{"studentName":studentName,"studentAccount":studentAccount,"studentPwd":studentPwd,"classId":classId},
            success:function (data) {
                layer.close(a);
                var state=data.state;
                if(state==1){
                    layer.msg("注册成功",{
                        icon: 1,shade: 0.01,time:600
                    },function() {
                        parent.location.href="/index.jsp";
                    })
                }else if(state==-1){
                    layer.msg("注册失败，用户已存在", {
                        icon: 7,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else if(state==0){
                    layer.msg("注册失败", {
                        icon: 2,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }else{
                    layer.msg("注册失败", {
                        icon: 8
                        ,shade: 0.01,time:600
                    });
                    $("#username").val(username);
                    $("#pwd").val("");
                }
            }
        }
    )
}

