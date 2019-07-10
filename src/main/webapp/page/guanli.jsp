
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
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR >
  <script src="../admin/js/jquery-1.8.3.js"></script>
  <script type="application/javascript">
    $(function () {
        $("#btn1").click(function () {
            location.href="login.jsp"
        })
        $("#btn2").click(function () {
            location.href="getHouseByPass"
        })
    })
    function do_updateHouse(id) {

        location.href="preUpdate?id="+id;
    }
    function do_deleteHouse(id) {
        location.href="deleteHouse?id="+id;
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>
   <DIV class=search> <label class=ui-green style="color: red">欢迎${sessionScope.users.name}登录</label> &nbsp;
  <LABEL class="ui-green searchs"><a href="goFabu" title="">发布房屋信息</a></LABEL>
  <LABEL class=ui-green><INPUT   value="租       房" type=button id="btn2" name=search></LABEL>
<LABEL class=ui-green><INPUT  value="退       出" type=button id="btn1" name=search></LABEL>
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="house">
     <TR>
    <TD class=house-thumb><SPAN><A href="details.jsp" target="_blank"><img src="http://localhost:80/${house.path}" width="100" height="75" ></A></SPAN>
     </TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${house.title}</A> &nbsp;  <span style="color: red">
          ${house.state}
        </span></DT>
        <DD>${house.districtName}${house.streetName},${house.floorage}平米<BR>联系方式：${house.contact} </DD>
        <DD><span> 发布日期:  <fmt:formatDate value="${house.pubdate}" pattern="yyyy年MM月dd日"></fmt:formatDate></span></DD>
      </DL>
    </TD>
    <TD ><a class=ui-green id="updateHouse" href="javascript:do_updateHouse(${house.id})" >修  改</a></TD>
       <TD ><a class=ui-green id="deleteHouse" href="javascript:do_deleteHouse(${house.id})" >删  除</a></TD>
    </TD>
  </TR>
  </c:forEach>
  </TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
  <c:forEach items="${pageInfo.navigatepageNums}" var="i" >
  <LI class=current>
    <A id=widget_338868862  href="http://localhost:8080/page/getHouseByUid?page=${i}"
  parseContent="true" showError="true" targets="houseArea" 
  ajaxAfterValidation="false" validate="false" 
dojoType="struts:BindAnchor">${i}</A>
   </LI>
  </c:forEach>
  <a href="http://localhost:8080/page/getHouseByUid?page=${pageInfo.pageNum-1}">上页</a>
  <a href="http://localhost:8080/page/getHouseByUid?page=${pageInfo.pageNum+1}">下页</a>
 </UL>

  <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN>
</DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
