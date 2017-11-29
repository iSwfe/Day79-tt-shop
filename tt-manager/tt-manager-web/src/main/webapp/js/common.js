
//选项卡操作的js
var ttshopTabs = {
	onTreeClick:function () {
		//约定大于配置：定义DOM对象的时候，一般定义为tree
		//定义的是一个jquery对象的话，一般定义为$tree
		var $tree = $('#menu .easyui-tree');
		//创建一个当前对象供下面的function用
		var _this = this;
		$tree.tree({
			onClick: function (node) {
				_this.addTabs(node.text, node.attributes.href);
			}
		});
	},
	addTabs:function (text,href) {
		if ($('#tab').tabs('exists', text)) {
			//能进入这里说明该选项卡存在
			this.selectTabs(text);
		} else {
			//新增选项卡
			$('#tab').tabs('add', {
				title: text,
				href: href,
				closable: true
			});
		}
	},
	selectTabs:function (text) {
		$('#tab').tabs('select', text);
	},
	closeTabs:function (text) {
		$('#tab').tabs('close', text);
	}
};