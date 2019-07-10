
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  Object users = session.getAttribute("users");
  if(users==null){
    response.sendRedirect("login.jsp");
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -修改房屋信息</TITLE>
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
                $.post("getStreetByDid",{"id":d_id},function (data) {
                    $("#streetId>option").remove();
                    for (var i=0;i<data.length;i++){
                        var node= $("<option  value="+data[i].id+">"+data[i].name+"</option>");
                        $("#streetId").append(node);
                    }
                },"json")
        });

        $("#pFile").change(function () {
            $("#updateForm").ajaxSubmit({
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
            } else {
                $("#updateForm").submit()
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
  <DT>修改房屋信息</DT>
  <DD class=past>修改房屋信息</DD></DL>
<DIV class=box>
<FORM id=updateForm method=post name=add.action action=updateHouse enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <input type="hidden"  name=" id" value="${house.id}">
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><input id=title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><select  name=typeId id="typeId">
      <c:forEach items="${typeList}" var="type">
        <option    <c:if test="${house.typeId==type.id}">selected="selected"</c:if>  value=${type.id}>${type.name}</option>
      </c:forEach>

    </select>
    </TD>
  </TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><input id=floorage class=text type=number name=floorage value="${house.floorage}"> </TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><input id=price class=text type=number name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>

    <TD><input  type=date id="pubdate" name=pubdate value="<fmt:formatDate pattern="yyyy-MM-dd" value="${house.pubdate}"/>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<select class=text name=districtId id="districtId">
      <c:forEach items="${districtList}" var="d">
        <option <c:if test="${district.id==d.id}">selected="selected"</c:if>  value=${d.id}>${d.name}</option>
      </c:forEach>
    </select> 街：
      <select class=text name=streetId  id=streetId>
        <option  value="${street.id}">${street.name}</option>
      </select>
    </TD></TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><input id=contact class=text type=number name=contact value="${house.contact}"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD>
        <textarea  name="description">${house.description}</textarea>
    </TD>
  </TR>
  <TR>
    <TD class=field>房屋图片：</TD>
    <TD>
      <input id=pFile  type=file name=pFile >
      <input id=path  type=hidden name=path  value="${house.path}">
    </TD>
  </TR>
  <TR>
    <TD class=field></TD>
    <TD><img id="showImage" src="http://localhost:80/${house.path}" style="height: 100px"> </TD>
  </TR>
  </TBODY></TABLE>
<DIV class=buttons>
  <input  value=立即修改 type=button id="btn">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
