<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<div class="easyui-panel" title="商品规格参数模板详情" data-options="fit:true">
	<form class="form" id="itemParamAddForm" name="itemParamAddForm" method="post">
		<table style="width: 100%;">
			<tr>
				<td class="label">商品类目:</td>
				<td>
					<input id="cid" name="cid" style="width: 200px;"/>
				</td>
			</tr>

			<tr>
				<td class="label">规格参数:</td>
				<td>
					<button class="easyui-linkbutton" onclick="addGroup()" type="button"
							data-options="iconCls:'icon-add'">添加分组
					</button>
					<ul id="item-param-group">

					</ul>
					<ul id="item-param-group-template" style="display:none">
						<li>
							<input name="group" />
							<button title="添加参数" class="easyui-linkbutton" onclick="addParam(this)" type="button"
									data-options="iconCls:'icon-add'"></button>
							<button title="删除分组" class="easyui-linkbutton" onclick="delGroup(this)" type="button"
									data-options="iconCls:'icon-cancel'"></button>
							<ul class="item-param">
								<li>
									<input name="param" />
									<button title="删除参数" class="easyui-linkbutton" onclick="delParam(this)"
											type="button" data-options="iconCls:'icon-cancel'"></button>
								</li>
							</ul>
						</li>
					</ul>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<button class="easyui-linkbutton" onclick="submitForm()" type="button"
						data-options="iconCls:'icon-ok'">保存
					</button>
					<button class="easyui-linkbutton" onclick="clearForm()" type="button"
							data-options="iconCls:'icon-undo'">重置
					</button>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>
	//初始化类别选择树
	$('#cid').combotree({
		url: 'itemCats?parentId=0',
		required: true,
		method: 'GET',
		onBeforeExpand: function (node) {
			//获取当前树
			//获取当前树控件的属性
			var options = $('#cid').combotree('tree').tree('options');
			//修改控件属性url变成新的nodeid
			options.url = 'itemCats?parentId=' + node.id;
		},
		//在点击页面上节点选中条目时
		onBeforeSelect: function (node) {
			//判断是否是叶子节点
			var isLeaf = $('#cid').tree('isLeaf',node.target);
			if(!isLeaf){
				//能进入这里说明不是叶子节点，要通知用户重新选择
				$.messager.alert('警告','请选择最终类目！','warning');
				return false;
			}
		}
	});

	//添加分组
	function addGroup() {
		//取到模板中的第一个li
		var $liTemplate = $('#item-param-group-template li').eq(0).clone();
		//将该节点追加到容器中
		$('#item-param-group').append($liTemplate);
	}

	//添加参数
	function addParam(ele) {
		var $templateLiSub = $('#item-param-group-template .item-param li').eq(0).clone();
		$(ele).parent().find('.item-param').append($templateLiSub);
	}

	//删除参数
	function delParam(ele) {
		$(ele).parent().remove();
	}

	//删除分组
	function delGroup(ele) {
		$(ele).parent().remove();
	}

	//保存
	function submitForm() {
		//1 创建空数组
		//参数组数组
		var paramsGroups = [];
		//参数组的jQuery对象
		var $groups = $('#item-param-group [name=group]');

		//2 拼接需要保存的JOSN字符串
		$groups.each(function(i, e) {	//i为遍历的index,e为遍历的当前元素
			//1 拿到第一个参数:"group"
			var _group = $(e).val();

			//2 拿到第二个参数:"params"
			//新建一个数组,装所有参数
			var _params = [];
			//拿到参数组的jQuery对象
			var $params = $(e).parent().find(".item-param [name=param]");
			//遍历该数组
			$params.each(function(_i, _e) {
				var param = $(_e).val();
				if ($.trim(param).length > 0)
					_params.push(param);
			});

			//创建空的该参数组json对象
			var obj = {};
			//将两个参数放入当前json对象中
			obj.group = _group;
			obj.params = _params;
			//将该json对象塞入参数组数组中
			if ($.trim($(e).val()).length > 0 && _params.length > 0)
				paramsGroups.push(obj);
		});

		//console.log(paramsGroups);
		//3 发送异步请求,接收并实现跳转
		$.post(
			'itemParam/'+ $('#cid').combotree('getValue'),
			{'paramData':JSON.stringify(paramsGroups)},
			function (data) {
				if (data > 0) {
					$.messager.alert('温馨提示', '参数规格添加成功!', 'info');
					ttshopTabs.closeTabs('新增规格参数');
					ttshopTabs.addTabs('查询规格参数', 'item-param-list');
				}
			}
		);
	}

	function clearForm() {
		window.href("reload");
	}
</script>