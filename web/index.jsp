<%--
  Created by IntelliJ IDEA.
  User: TsuiXh
  Date: 16/11/21
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>简易天气预报</title>
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h2>中国天气</h2>
        <table class="table table-hover">
            <tr>
                <th>城市</th>
                <th>天气</th>
                <th>温度</th>
                <th>风力</th>
                <th>详情</th>
            </tr>
            <c:forEach items="${cities}" var="city">
                <tr>
                    <td>${city.cityName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${city.state1 != city.state2}">
                                <img src="${imgPath}c${city.state1}.gif"/><img src="${imgPath}c${city.state2}.gif"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${imgPath}c${city.state1}.gif"/>
                            </c:otherwise>
                        </c:choose>
                            ${city.stateDetailed}
                    </td>
                    <td>${city.tem2}°C~${city.tem1}°C</td>
                    <td>${city.windState}&nbsp;${city.windDir}&nbsp;${city.windPower}</td>
                    <td>
                        <c:choose>
                            <c:when test="${city.pyName == ''}">
                                <!-- 不显示操作 -->
                            </c:when>
                            <c:otherwise>
                                <a href="weather?pyName=${city.pyName}">详情</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
