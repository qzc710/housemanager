
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  Object users = session.getAttribute("users");
  if(users==null){
    response.sendRedirect("login.jsp");
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../admin/js/jquery-1.8.3.js"></script>
  <script src="../admin/js/jquery.form.js"></script>
  <script type="application/javascript">
    //加载街道下拉列表框
    $(function () {
        $("#districtId").change(function () {
            var d_id=$("#districtId").val();
            if(d_id!=-1){
                $.post("getStreetByDid",{"id":d_id},function (data) {
                    $("#streetId>option:gt(0)").remove();
                    for (var i=0;i<data.length;i++){
                        var node= $("<option  value="+data[i].id+">"+data[i].name+"</option>");
                        $("#streetId").append(node);
                    }
                },"json")
            }else {
                $("#streetId>option:gt(0)").remove();
            }
        });

        $("#pFile").change(function () {
            $("#addHouseForm").ajaxSubmit({
                url:'showPicture',
                success:function (data) {
                    $("#path").val(data);
                    $("#showImage").attr("src","http://localhost:80/"+data);
                }
            })
        })
    })

    $(function () {
        $("#btn").click(function () {
            if($("#title").val()==''){
                $("#title").focus();
            }else if ($("#typeId").val()==''){
                $("#typeId").focus();
            }else if ($("#floorage").val()==''){
                $("#floorage").focus();
            }else if ($("#price").val()==''){
                $("#price").focus();
            }else if ($("#pubdate").val()==''){
                $("#pubdate").focus();
            }else if ($("#districtId").val()==''){
                $("#districtId").focus();
            }else if ($("#contact").val()==''){
                $("#contact").focus();
            }
            else if ($("#description").val()==''){
                $("#description").focus();
            }
            else if ($("#pFile").val()==''){
                $("#pFile").focus();
            }else {
                $("#addHouseForm").submit()
            }
        })
    })
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=addHouseForm method=post name=add.action action=addHouse enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=title class=text type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT  name=typeId id="typeId" >

      <c:forEach items="${typeList}" var="type">
        <OPTION  value=${type.id}>${type.name}</OPTION>
      </c:forEach>

    </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=floorage class=text type=number
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=price class=text type=number name=price> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date id="pubdate" name=pubdate></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=districtId id="districtId">
      <OPTION selected value="-1" >-请选择-</OPTION>
      <c:forEach items="${districtList}" var="district">
        <OPTION  value=${district.id}>${district.name}</OPTION>
      </c:forEach>
    </SELECT> 街：
      <SELECT class=text name=streetId  id=streetId>
        <OPTION  value="">-请选择-</OPTION>
      </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=contact class=text type=number name=contact> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA id="description" name=description></TEXTAREA></TD>
  </TR>
  <TR>
    <TD class=field>房屋图片：</TD>
    <TD><INPUT id=pFile  type=file name=pFile >
      <INPUT id=path  type=hidden name=path>
    </TD>
  </TR>
  <TR>
  <TR><TD class=field></TD>
    <TD><img id="showImage" style="height: 100px"> </TD></TR>
  <TR>
  </TBODY></TABLE>
<DIV class=buttons>
  <INPUT  value=立即发布 type=button id="btn">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
