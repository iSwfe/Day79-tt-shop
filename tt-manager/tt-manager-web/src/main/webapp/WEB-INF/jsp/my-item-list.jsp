<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>item-list</title>

		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.color-2.1.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.color.svg-names-2.1.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/buttons.css">

		<%--提交查询--%>
		<script type="text/javascript">
			$(function(){
				$(".submit").on("click",search);
			});
		</script>

		<script type="text/javascript">
			function search(){
				$.ajax({
					url:"${pageContext.request.contextPath }/list",
					type:"POST",
					//data:$("#submitForm").serialize(),
					dataType:"json",
					success:function(json) {
						//清除以前表格中的数据
						$("#listData").text("");
						$(json).each(
							function(i, item) {
								var date1 = new Date(item.created);
								var date2 = new Date(item.updated);
								var d1 = date1.toLocaleDateString();
								var d2 = date2.toLocaleDateString();
								$("#listData").append(
									"<tr>"+
									"<td>"+ (((json.currentIndex-1) * json.pageSize)+(i+1)) +"</td>"+
									"<td style='min-width: 100px;word-break: break-all;word-wrap: break-word'>"+ item.id +"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>"+ item.title +"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>"+ item.sellPoint +"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>"+ item.price +"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>"+ item.num +"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>"+ item.barcode +"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>" +
										d1+
									"</td>"+
									"<td style='max-width: 100px;word-break: break-all;word-wrap: break-word'>" +
										d2+
									"</td>"+
									"<td>"+
									"<div class='btn-group'>"+
									"<a type='button' class='btn btn-info btn-xs' href='${pageContext.request.contextPath }/replies-list.action?invid="+item.id+"'>查看回复</a>"+
									"<a class='btn btn-warning btn-xs' href='${pageContext.request.contextPath }/delete.action?id="+item.id+"'>删除</a>" +
									"</div>"+
									"</td>"+
									"</tr>"
								);
							}
						);
						list(json);
					}

				})
			}
		</script>

		<%--分页按钮--%>
		<script type="text/javascript">
			//分页按钮的点击事件,让其更改currentIndex后再执行查询按钮的点击事件(见list-index.jsp的jQuery代码)
			function toPage(currentIndex){
				if(0 === currentIndex)
					return false;
				$("#currentIndex").val(currentIndex);
				$(".submit").click();
			}

			//根据AJAX给出的json绘制分页按钮
			function list(json) {
				//定义上一页/下一页按钮的禁用状态
				var disabledPrevious = (1 >= json.currentIndex);
				var disabledNext = (json.totalPage <= json.currentIndex);
				//将之前的清除,并设置一个隐藏的input,用于提交请求的页码
				$("#listPage").html(
					"<input type='hidden' id='currentIndex' name='currentIndex' value="+ json.currentIndex +" />"
				)
				//绘制左边的[上一页]按钮
					.append(
						"<nav aria-label='...'>"+
						"<ul id='ul' class='pagination' style='margin: 0'>"+
						"<li class='"+ (disabledPrevious?"disabled":"") +"' >"+
						"<a href='javascript:toPage("+ (disabledPrevious?"0":(json.currentIndex - 1)) +")' aria-label='Previous'>"+
						"<span aria-hidden='true'>«</span>"+
						"</a>"+
						"</li>"
					);
				//循环绘制[中间]所有的页码
				for(var i = 1; i <= json.totalPage; i++) {
					//定义当前页码按钮的激活状态
					var activeButton = (i === json.currentIndex);
					$("#ul").append(
						"<li class='"+ (activeButton?"active":"") +"'>"+
						"<a type='button' href='javascript:toPage("+ i +");' >"+
						i +
						"</a>"+
						"</li>"
					)
				}
				//绘制右边的[下一页]按钮
				$("#ul").append(
					"<li class='"+ (disabledNext?"disabled":"") +"' >"+
					"<a href='javascript:toPage("+ (disabledNext?"0":(json.currentIndex + 1)) +")' aria-label='Next'>"+
					"<span aria-hidden='true'>»</span>"+
					"</a>"+
					"</li>"+
					"</ul>"+
					"</nav>"
				);
			}
		</script>

	</head>

	<body onload="search()">
		<form id="invitationForm" style="text-align: center">


			<div class="form-group">
				<label>标题
					<input type="text" class="form-control" name="invitation.title"/>
				</label>
			</div>
			<button type="button" class="btn btn-info submit">查询</button>
			<br/><br/>


			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h3>
						查询结果
						<span class="label label-info">帖子列表</span>
						<span class="label label-default">
							<em style="font-weight: 400">每页显示:</em>
							<select name="pageSize" id="pageSize">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3" selected="selected">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select>
							<button type="button" class="btn btn-info btn-xs submit">查询</button>
						</span>
						<a href="${pageContext.request.contextPath }/" style="float: right">
						</a>
					</h3>
				</div>

				<!-- Table -->
				<table class="table">
					<thead>
					<tr>
						<th class="th th-primary" style="min-width: 60px">商品ID</th>
						<th>宝贝标题</th>
						<th>卖点</th>
						<th>价格</th>
						<th>数量</th>
						<th>barcode</th>
						<th>状态</th>
						<th>建立时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="listData">
					<%-- 已用jQuery-AJAX生成,见"list-index.jsp" --%>
					</tbody>
				</table>

				<%--Panel Footer--%>
				<div  id="listPage" class="panel-footer" style="text-align: center">

				</div>
			</div>


		</form>
	</body>

</html>