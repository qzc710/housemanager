<%--
  Created by IntelliJ IDEA.
  User: huange910820
  Date: 2019/7/1
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="easyUI/css/demo.css"/>
<link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script  src="js/jquery.easyui.min.js"></script>
<script type="application/javascript" src="js/checkHouse.js">
</script>
<html>
<head>
    <title>房屋审核</title>
</head>
<body>
<table id="dg"></table>

<!--工具包-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:checkAll()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">批量重审</a>
    </div>
    <div>
        标题: <input type="text" name="title" id="title"> &nbsp;&nbsp;
        <a href="javascript:search()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">查询</a>
    </div>
</div>

</body>
</html>
