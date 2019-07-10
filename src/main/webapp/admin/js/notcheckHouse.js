//未审核房屋数据
$(function () {
    $('#dg').datagrid({
        title:"未审核信息",
        url:'getHouseByNopass',//服务器连接地址
        fitColumns:true,
        fit:true,
        toolbar:"#ToolBar",
        pagination:true,  //分页
        pageSize:5,  //页面大小
        pageList:[5,6,7,9],
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'编号',width:100},
            {field:'userId',title:'用户编号',width:100},
            {field:'typeName',title:'类型名',width:100},
            {field:'title',title:'标题',width:100},
            {field:'description',title:'描述',width:100},
            {field:'price',title:'出租价',width:100},
            {field:'pubdate',title:'发布日期',width:100},
            {field:'floorage',title:'面积',width:100},
            {field:'contact',title:'联系人',width:100},
            {field:'streetName',title:'街道名',width:100},
            {field:'path',title:'房屋图片',width:100},
            {field:'ispass',title:'未审核',width:100,align:'right',formatter: function(value,row,index){
                    return "<a href='javascript:checkHouse("+row.id+")'>审核</a>"
                }
            }
        ]]
    });
})

//绑定查询参数
function search(){
    var title=$("#title").val();
    $("#dg").datagrid("load",{"title":title});
}

//重审
function checkHouse(id){
    $.post("checkHouse",{"id":id},function (data) {
        if(data.result){
            $("#dg").datagrid("reload")
        }else {
            $.messager.alert('提示框','重新审核失败!','info');
        }
    },"json")
}

//审核多个用户
function checkAll() {
    var rows=$("#dg").datagrid("getSelections")
    var ids=[];
    for(var i=0;i<rows.length;i++){
        ids[i]=rows[i].id
    }
    if( rows.length <1){
        $.messager.alert("系统提示", "请选择行");
        return;
    }
    $.messager.confirm('提示框', '确认全部审核吗?', function(r){
        if (r){
            $.post("checkMoreHouse",{"ids":ids.join(",")},function (data) {
                if(data.result){
                    $("#dg").datagrid("reload");
                }else {
                    $.messager.alert('提示框','审核失败!','info');
                }
            },"json")
        }
    });
}