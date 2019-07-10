
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
  <TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script type="application/javascript" src="../admin/js/jquery-1.8.3.js"> </script>

    <script type="application/javascript">
        //判断用户名是否存在
        $(function () {
            $("#span1").html(" ");
                //失去焦点事件
                $("#name").blur(function (){
                    var username=$("#name").val();
                    //判断用户名不为空
                    if(username!=""){
                    //异步请求
                    $.post("checkUserName",{"name":username},function (data) {
                        if(data.result){
                            $("#span1").html('用户名已存在!').css("color","red");
                        }else {
                            $("#span1").html('用户名通过!').css("color","green");
                        }
                    },"json")
                    }else {
                        $("#span1").html(" ");
                    }
                });

            //用户名注册
            //1判断用户名,密码,手机号不能为空&&两次输入密码相同
            $("#btn1").click(function () {
                var username=$("#name").val();
                var span=$("#span1").html();

                var password=$("#password").val();
                var repassword=$("#repassword").val();
                var telephone=$("#telephone").val();
                if(username!=""&&password!=""&&password!=""&&telephone!=""){
                    if(password==repassword&&span=="用户名通过!") {
                        $("#regsForm").submit()
                    }else {
                        $("#span2").html("注册失败,请重新注册");
                    }
                }else {
                    $("#span2").html("注册失败,请重新注册");
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
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
    <form action="regs" method="post" id="regsForm">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
      <INPUT class=hide type=text name="isadmin" id="isadmin" value="0">
      <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name=name id="name"> <span id="span1"></span></TD>
  </TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"> </TD>
  </TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword  id="repassword" ></TD>
  </TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone id="telephone"></TD>
  </TR>
  <TR>
  </TR>
  </TBODY>
</TABLE>
<DIV class=buttons>
<INPUT  value=立即注册 type=button id="btn1"> <span id="span2"></span>
</DIV></DIV></form></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
