//加载区域数据
$(function () {
    $('#dis').datagrid({
        title:"区域信息",
        url:'getDistrictByPage',//服务器连接地址
        fitColumns:true,
        fit:true,
        toolbar:"#ToolBar", //工具类
        pagination:true,  //分页
        pageSize:5,  //页面大小
        pageList:[5,6,7,9],
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'区域编号',width:100},
            {field:'name',title:'区域名',width:100},
            {field:'oper',title:'操作',width:100,align:'right',formatter:function (value,row,index) {
                    return "<a href='javascript:do_deleteDistrict("+row.id+")'>删除</a>||" +
                        "<a href='javascript:findStreet("+row.id+")'>查看街道</a>";
                }}
        ]]
    });
})
//绑定查询条件
function search() {
    var s_name=$("#s_name").val();
    $("#dis").datagrid("load",{"name":s_name})
}
//按钮关闭对话框
function CloseDialog(id){
    $("#"+id).dialog("close")
}
//打开添加窗口
function Add() {
    $("#AddDialog").dialog("open").dialog("setTitle","添加区域")
}
//添加操作,发送一步请求
function SaveDistrictDialog() {
    $('#addDialogForm').form('submit', {
        url:"addDistrict",
        success:function(data){
            var data=$.parseJSON(data);
            if(data.result){
                $.messager.alert('提示框','添加成功!','info');
                $("#dis").datagrid("reload");
                $("#AddDialog").dialog("close")
            }else {
                $.messager.alert('提示框','添加失败!','info');
            }
        }
    });
}
//修改操作,回显数据
function ModifyBySelect() {
    var rows=$("#dis").datagrid("getSelections")
    if( 1 != rows.length ){
        $.messager.alert("系统提示", "请选择行,或者选择多行");
        return;
    }
    $("#updateDialog").dialog("open").dialog("setTitle","修改区域")
    var row = rows[0];
    $.post("getDistrictById",{"id":row.id},function (data) {
        $("#updateDialogForm").form("load",data);
    },"json")
}
//修改操作
function updateDistrictDialog() {
    $('#updateDialogForm').form('submit', {
        url:"updateDistrict",
        success:function(data){
            var data=$.parseJSON(data);
            if(data.result){
                $.messager.alert('提示框','修改成功!','info');
                $("#dis").datagrid("reload");
                $("#updateDialog").dialog("close")
            }else {
                $.messager.alert('提示框','修改失败!','info');
            }
        }
    });

}
//删除区域
function do_deleteDistrict(id) {
    var rows=$("#dis").datagrid("getSelections")
    if( 1 != rows.length ){
        $.messager.alert("系统提示", "请选择行,或者选择多行");
        return;
    }
    $.messager.confirm('提示框', '确认删除吗?', function(r){
        if (r){
            $.post("deleteDistrict",{"id":id},function (data) {
                if(data.result){
                    $.messager.alert('提示框','删除成功!','info');
                    $("#dis").datagrid("reload");
                }else {
                    $.messager.alert('提示框','删除失败!','info');
                }
            },"json")
        }
    });
}
//批量删除区域
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
            $.post("deleteDistrictList",{"ids":ids.join(",")},function (data) {
                if(data.result){
                    $.messager.alert('提示框','删除成功!','info');
                    $("#dis").datagrid("reload");
                }else {
                    $.messager.alert('提示框','删除失败!','info');
                }
            },"json")
        }
    });
}
//查看街道信息
var s_id;
function findStreet(id) {
    s_id=id;
    $("#s_id").val(id);
    $("#StreetDialog").dialog("open").dialog("setTitle","街道详情");
    $('#showStreet').datagrid({
        url:'getStreetByDid?id='+id,//服务器连接地址
        fitColumns:true,
        fit:true,
        toolbar:"#StreetToolBar", //工具类
        pagination:true,  //分页
        pageSize:5,  //页面大小
        pageList:[5,6,7,9],
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'街道编号',width:100},
            {field:'name',title:'街道名',width:100},
            {field:'districtName',title:'区域名',width:100},
            {field:'oper',title:'操作',width:100,align:'right',formatter:function (value,row,index) {
                    return "<a href='javascript:do_deleteStreet("+row.id+")'>删除</a>"
                }}
        ]]
    });
}
//添加街道信息
function saveStreet() {

    var s_name=$("#name").val();

    $.post("addStreet",{"districtId":s_id,"name":s_name},function (data) {
        if(data.result){
            $.messager.alert('提示框','添加成功!','info');
            $("#showStreet").datagrid("reload");
        }else {
            $.messager.alert('提示框','添加失败!','info');
        }
    },"json")
}
//删除街道
function do_deleteStreet(id) {
    var rows=$("#showStreet").datagrid("getSelections")
    if( 1 != rows.length ){
        $.messager.alert("系统提示", "请选择行,或者选择了多行");
        return;
    }
    $.messager.confirm('提示框', '确认删除吗?', function(r){
        if (r){
            $.post("deleteStreet",{"id":id},function (data) {
                if(data.result){
                    $("#showStreet").datagrid("reload");
                }else {
                    $.messager.alert('提示框','删除失败!','info');
                }
            },"json")
        }
    });
}