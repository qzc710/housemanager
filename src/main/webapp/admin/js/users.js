//加载类型数据
$(function () {
    $('#dis').datagrid({
        title:"用户信息",
        url:'getUsersByPage',//服务器连接地址
        fitColumns:true,
        fit:true,
        toolbar:"#ToolBar", //工具类
        pagination:true,  //分页
        pageSize:5,  //页面大小
        pageList:[5,6,7,9],
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'用户编号',width:100},
            {field:'name',title:'用户名',width:100},
            {field:'telephone',title:'手机号',width:100},
            {field:'oper',title:'操作',width:100,align:'right',formatter:function (value,row,index) {
                    return "<a href='javascript:do_delete("+row.id+")'>删除</a>"
                }}
        ]]
    });
})

//绑定查询条件
function search(){
    var s_name=$("#s_name").val();
    var s_telephone=$("#s_telephone").val();
    $("#dis").datagrid("load",{"name":s_name,"telephone":s_telephone});
}
//关闭
function CloseDialog(id) {
    $("#"+id).dialog("close")
}
//打开添加对话框
function Add() {
    $("#AddDialog").dialog("open").dialog("setTitle","添加用户");
}
//添加操作
function SaveDialog() {
    $('#addDialogForm').form('submit', {
        url:"addUsers",
        success:function(data){
            var d=$.parseJSON(data);
            if(d.result){
                $("#dis").datagrid("reload");
                $("#AddDialog").dialog("close")
            }else {
                $.messager.alert('提示框','添加失败!','info');
            }
        }
    });
}
//修改用户之前,会显示数据
function ModifyBySelect() {
var rows=$("#dis").datagrid("getSelections")
if( 1 != rows.length ){
    $.messager.alert("系统提示", "请选择行,或者选择多行");
    return;
}
$("#updateDialog").dialog("open").dialog("setTitle","修改类型")
var row = rows[0];
$.post("getUsersById",{"id":row.id},function (data) {
    $("#updateDialogForm").form("load",data);
   },"json")
}
//修改用户
function updateDialog() {
    $('#updateDialogForm').form('submit', {
        url:"updateUsers",
        success:function(data){
            var data=$.parseJSON(data);
            if(data.result){
                $("#dis").datagrid("reload");
                $("#updateDialog").dialog("close")
            }else {
                $.messager.alert('提示框','修改失败!','info');
            }
        }
    });
}
//删除多个用户
function DeleteByFruitName() {
    var rows=$("#dis").datagrid("getSelections")
    var ids=[];
    for(var i=0;i<rows.length;i++){
        ids[i]=rows[i].id
    }
    if( rows.length <1){
        $.messager.alert("系统提示", "请选择行");
        return;
    }
    $.messager.confirm('提示框', '确认删除吗?', function(r){
        if (r){
            $.post("deleteUsersList",{"ids":ids.join(",")},function (data) {
                if(data.result){
                    $("#dis").datagrid("reload");
                }else {
                    $.messager.alert('提示框','删除失败!','info');
                }
            },"json")
        }
    });
}

//删除单个用户
function  do_delete(id){

    var rows=$("#dis").datagrid("getSelections")
    if( 1 != rows.length ){
        $.messager.alert("系统提示", "请选择行,或者选择多行");
        return;
    }
    $.messager.confirm('提示框', '确认删除吗?', function(r){
        if (r){
            $.post("deleteUsers",{"id":id},function (data) {
                if(data.result){
                    $("#dis").datagrid("reload");
                }else {
                    $.messager.alert('提示框','删除失败!','info');
                }
            },"json")
        }
    });
}
