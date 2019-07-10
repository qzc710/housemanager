<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBliC "-//W3C//dtd HTML 4.01 transitional//EN" "http://www.w3c.org/tr/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEaD><TITLE>青鸟租房 - 首页</TITLE>
    <METa content="text/html; charset=utf-8" http-equiv=Content-Type>
    <liNK rel=stylesheet type=text/css href="../css/style.css">
    <METa name=GENERaTOR content="MSHTML 8.00.7601.17514">
    <script src="../admin/js/jquery-1.8.3.js"></script>
    <script type="application/javascript">
      function do_page(pn) {
          $("#page").val(pn);
          $("#searchForm").submit();
      }
      $(function () {
          $("#title").val("${houseParam.title}");
          $("#typeId").val(${houseParam.typeId});
          $("#price").val("${houseParam.price}");
          $("#districtId").val(${houseParam.districtId});
          $("#floorage").val("${houseParam.floorage}");
          $("#streetId").val(${houseParam.streetId});
          $("#districtId").change(function () {
              var d_id=$("#districtId").val();
                  if(d_id!=""){
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
          })

      })
    </script>
</HEaD>
<BODY>
<div id=header class=wrap>
    <div id=logo><IMG src="../images/logo.gif"></div>

</div>

<div id=navbar class=wrap>
    <div style=" height: 35px; padding-left: 20px;padding-top:30px;">
        <span class=ui-green >欢迎${sessionScope.users.name}登录</span>
        <span  class=ui-green id="span1" style="padding-left: 50px;font-size: 15px">
            <a href=" getHouseByUid" >个人管理</a>
        </span>
    </div>
    <dl class="search clearfix">

        <form id=searchForm method=post ACTION="getHouseByPass"  >

            <input type="hidden" id="page" name="page" value="1">
            <dt>
                <ul>
                    <li class=bold>房屋信息</li>
                    <li>标题：<input class=text type=text name=title id="title">
                        <label class=ui-blue>
                            <input id="btn" value=搜索房屋 type=submit name=search></label>
                    </li>
                </ul>
            </dt>
            <dd >
                <ul>
                    <li class=first>价格</li>
                    <li><select name=price id="price">
                        <option  value="">不限</option>
                        <option value=0-1000>1000元以下</option>
                        <option value=1000-2000>1000元—2000元</option>
                        <option value=2000-5000>2000元以上</option>
                    </select></li>
                </ul>
            </dd>

            <dd>
                <ul>
                    <li class=first>区域</li>
                    <li>
                        <select  name=districtId id="districtId">
                            <option selected value="">不限</option>
                            <c:forEach items="${districtList}" var="d">
                                <option value=${d.id}>${d.name}</option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>
            </dd>
            <dd>
                <ul>
                <li class=first>街道</li>
                <li>
                        <select  name=streetId id="streetId">
                            <option  value="">不限</option>
                            <c:forEach items="${streets}" var="street">
                                <option  value="${street.id}">${street.name}</option>
                            </c:forEach>
                        </select>
                </li>
                </ul>
            </dd>
            <dd>
                <ul>
                    <li class=first>房型</li>
                    <li><select name=typeId id="typeId">
                        <option selected value="">不限</option>
                        <c:forEach items="${typeList}" var="t">
                            <option value=${t.id}>${t.name}</option>
                        </c:forEach>
                    </select>
                    </li>
                </ul>
            </dd>
            <dd>
                <ul>
                    <li class=first>面积</li>
                    <li><select name=floorage id="floorage">
                        <option selected value="">不限</option>
                        <option
                                value="0-70">70以下
                        </option>
                        <option value=70-120>70-150</option>
                        <option
                                value=120-500>150以上
                        </option>
                    </select></li>
                </ul>
            </dd>
        </form>

    </dl>
</div>
<div class="main wrap">
    <c:if test="${ ! empty pageInfo.list}">
    <table class=house-list>
        <tbody>
          <c:forEach items="${pageInfo.list}" var="house">
            <tr>
                <td class=house-thumb>
                <a href="details.jsp" target="_blank"> </a>
                 <span> <img src="http://localhost:80/${house.path}" width="100" height="95" ></span>
                </td>
                <td>
                    <dl>
                        <dt><a href="details.jsp" target="_blank">${house.title}</a></dt>
                        <dd>${house.districtName}${house.streetName},${house.floorage}平米<BR>联系方式：${house.contact} </dd>
                        <dd><span> 发布日期:  <fmt:formatDate value="${house.pubdate}" pattern="yyyy年MM月dd日"></fmt:formatDate></span></dd>
                    </dl>
                </td>
                <td class=house-type>${house.floorage}平</td>
                <td class=house-price><span>${house.price}</span>元/月</td>
            </tr>
          </c:forEach>
        <tr>无租房信息</tr>
        </tbody>
    </table>
    <div class=pager>
        <ul>
            <li class=current><a href="javascript:do_page(1)">首页</a></li>
            <li class=current><a href="javascript:do_page(${pageInfo.pageNum-1})">上一页</a></li>
            <li class=current><a href="javascript:do_page(${pageInfo.pageNum+1})">下一页</a></li>
            <li class=current><a href="javascript:do_page(${pageInfo.pages})">末页</a></li>
        </ul>
        <span class=total>${pageInfo.pageNum}/${pageInfo.pages}页</span>
    </div>
    </c:if>
    <c:if test="${empty pageInfo.list}">
        <center style="color: red; font-size: 20px;">暂无该信息</center>
    </c:if>
</div>
<div id=footer class=wrap>
    <dl>
        <dt>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</dt>
        <dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
    </dl>
</div>
</BODY>
</HTML>
