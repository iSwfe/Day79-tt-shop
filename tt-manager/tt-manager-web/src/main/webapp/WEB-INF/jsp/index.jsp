<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
		<!-- 导入easyui的样式表 -->
		<%--如果在页面上使用easyui框架：1、CSS  2、JS(注意载入顺序)--%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5/themes/bootstrap/easyui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5/themes/icon.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north'" style="height:70px;padding-left:10px;">
			<h2>天天小商城后台管理系统</h2>
		</div>

		<div data-options="region:'south'" style="padding:5px;background:#eee;">
			系统版本：V2.0
		</div>

		<div data-options="region:'west'" style="width:200px;">
			<div id="menu" class="easyui-accordion">
				<div title="商品管理" data-options="selected:true,iconCls:'icon-tip'" style="padding:10px 0;">
					<ul class="easyui-tree">
						<li data-options="attributes:{'href':'item-add'}">新增商品</li>
						<li data-options="attributes:{'href':'item-list'}">查询商品</li>
						<li data-options="attributes:{'href':'item-param-add'}">新增规格参数</li>
						<li data-options="attributes:{'href':'item-param-list'}">查询规格参数</li>
					</ul>
				</div>
				<div title="网站内容管理" data-options="iconCls:'icon-tip'" style="padding:10px 0;">
					<ul class="easyui-tree">
						<li data-options="attributes:{'href':'content-category'}">内容分类管理</li>
						<li data-options="attributes:{'href':'content'}">内容管理</li>
					</ul>
				</div>
				<div title="索引库管理" data-options="iconCls:'icon-tip'" style="padding:10px 0;">
					<ul class="easyui-tree">
						<li data-options="attributes:{'href':'index-item'}">solr索引库维护</li>
					</ul>
				</div>
			</div>
		</div>

		<div data-options="region:'center'" style="background:#eee;">
			<div id="tab" class="easyui-tabs" data-options="fit:true">
				<div title="欢迎页面" style="padding:20px;">千锋欢迎你</div>
			</div>
		</div>

		<!-- jquery -->
		<script src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.min.js"></script>
		<!-- jquery easyui -->
		<script src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/common.js"></script>
		<%--添加moment.js--%>
		<script src="${pageContext.request.contextPath}/js/common.js"></script>
		<script src="${pageContext.request.contextPath}/js/moment-with-locales.js"></script>
		<%--添加富文本编辑器的相关文件--%>
		<script src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
		<script src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"></script>

		<script>
			ttshopTabs.onTreeClick();
		</script>
	</body>
</html>