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

/*试题添加处理，手动组卷*/
$(function() {
	$("input:checkbox").click(function() {
		//试题编号
		var checked = $(this).attr("id");
		//试题对应的分数
		var score = $(this).parent().siblings("#subjectScore").text();
		
		//维持已选试题数量
		var choosed = parseInt($("#choosed").text());
		if(this.checked) {
			$("#choosed").text(choosed+1);
		} else {
			$("#choosed").text(choosed-1);			
		}
		
		//获取试卷编号
		var examPaperId = $("#examPaperId").text();
		if(examPaperId == null || examPaperId.trim() == "") {
			alert("无法获取试卷信息，暂时无法添加！");
			return false;
		}

		$.ajax({
			type: "POST",
			data: "subjectId="+checked+"&score="+score+"&examPaperId="+examPaperId,
			url: path+"/esm/getChooseSubId.action",
			success: function(data) {
				if(data.trim().indexOf("f-exists-") != -1) {
					zeroModal.show({
						title : "错误的提交",
						content : "此试题已经存在该试卷中了!",
						width : '200px',
						height : '130px',
						overlay : false,
						ok : true,
						onClosed : function() {
							//刷新当前页面
							//location.reload();
						}
					});
					var choosed = parseInt($("#choosed").text());
					$("#choosed").text(choosed-1);
					//截取题号
					var subjectId = data.substring(data.lastIndexOf("-")+1);
					//移除选择
					$("#"+subjectId).replaceWith("<span style='color:red;font-size:12px;'>exists</span>");
					return false;
				}				
			},
			error: function(data) {
				alert("提交失败");
			}
		});
	});
	
	//添加试题集合到指定试卷中
	$("#isAddHandle").click(function() {
		//获取试卷编号
		var examPaperId = $("#examPaperId").text();
		if(examPaperId == null || examPaperId.trim() == "") {
			alert("无法获取试卷信息，暂时无法添加！");
			return false;
		}
		$.ajax({
			type: "POST",
			data: "examPaperId="+examPaperId,
			url: path+"/esm/handAdd.action",
			success: function(data) {
				alert(data);
			}
		});
	});
	
	
	//分页跳转到指定页码
	$("#scannerPageForm").submit(function() {
		//获取页码
		var page = $("#scannerPage").val();
		if(isNaN(page) || page.trim() == "" || page.trim() == null || page<1) {
			$("#scannerPage").val("");
			return false;
		}
		return true;
	});
});


//手动添加试题到试卷中
function examPaperAddSubjects(examPaperId) {
	zeroModal.show({
		title : '添加试题-手动',
		iframe : true,
		url : '../subject/subjects.action?handAdd=1&examPaperId='+examPaperId,
		width : '90%',
		height : '90%',
		cancel : true,
		top : '0px',
		left : '0px',
		esc : true,
		overlay : true,
		overlayClose : true,
		// 关闭后回调刷新数据
		onClosed : function() {
			$("form input").attr("disabled", "disabled");
			$("form").attr("method", "get");
			$("form").attr("action", "clearSubjectIdsWithSession").submit();
		}
	});
}

//跳转自动添加试题
function autoAddSubjects(examPaperId) {
	zeroModal.show({
		title : '自动生成试题',
		iframe : true,
		url : '../admin/frame/autoSubjectSelect.jsp?examPaperId='+examPaperId,
		width : '30%',
		height : '80%',
		top : '30px',
		left : '430px',
		overlay : true,
		ok: true,
		onClosed : function() {
			location.href = "getAll.action";
		}
	});
}


//鼠标移入当前行 显示被隐藏的按钮
$(function() {
	$("tr").mouseover(function() {
		$(this).children("td").children("div").children(".dynamicBtn").show();
	}).mouseout(function() {
		$(this).children("td").children("div").children(".dynamicBtn").hide();		
	});
});