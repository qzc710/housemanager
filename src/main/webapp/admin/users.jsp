
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script  src="js/jquery.easyui.min.js"></script>
    <script type="application/javascript" src="js/users.js">
    </script>
</head>
<body>
<table id="dis"></table>

<!--工具包-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
    </div>
    <div>
        用户名: <input type="text" name="name" id="s_name"> &nbsp;&nbsp;
        电话号码:<input type="text" name="telephone" id="s_telephone">
        <a href="javascript:search()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">查询</a>
    </div>
</div>

<!--添加街道对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="addDialogForm" method="post">
        <table>
             <tr>
                <td>用户名:</td>
                <td><input type="text" class="easyui-validatebox" required  name="name"  /></td>
             </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" class="easyui-validatebox" required  name="password"  /></td>
            </tr>
            <tr>
                <td>手机号:</td>
                <td><input type="text" class="easyui-validatebox" required  name="telephone"  /></td>
            </tr>
            <input type="hidden"    name="isadmin"  value="1" />
        </table>
    </form>
</div>
<%--添加区域操作设置按钮--%>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--修改区域对话框-->
<div id="updateDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="updateDialogForm" method="post">
        <input type="text" class="easyui-validatebox"  readonly style="border: none"  name="id"  />
        <input type="hidden" class="easyui-validatebox"   name="isadmin"  value="1" />
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" class="easyui-validatebox" required  name="name"  /></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" class="easyui-validatebox" required  name="password"  /></td>
            </tr>
            <tr>
                <td>手机号:</td>
                <td><input type="text" class="easyui-validatebox" required  name="telephone"  /></td>
            </tr>
        </table>
    </form>
</div>
<%--修改区域操作设置按钮--%>
<div id="updateDialogButtons">
    <a href="javascript:updateDialog()" class="easyui-linkbutton" iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('updateDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
