<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 16.06.2024
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User page</title>
    <style>

        html {
            font-family: "Trebuchet MS", sans-serif;
        }

        body {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0
        }

        input {
            width: 100%;
            padding: 8px;
            border-radius: 12px;
            border: 2px solid black;
        }

        button {
            width: 80%;
            padding: 8px;
            border-radius: 12px;
            border: 2px solid darkgreen;
            cursor: pointer;
            background-color: darkseagreen;
            transition: 0.2s linear background-color, color;
        }

        .back {
            width: 30%;
            padding: 8px;
            border-radius: 12px;
            border: 2px solid darkgreen;
            cursor: pointer;
            color: black;
            background-color: floralwhite;
            text-decoration: none;
            transition: 0.2s linear background-color, color;
            text-align: center;
        }

        .back:hover {
            background-color: #f8d593;
        }

        button:hover {
            background-color: #004805;
            color: white;
        }

        tr {
            text-align: center;
        }
    </style>
</head>
<body>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: start; flex-direction: column">
    <div style="width: 100%; height: 48px; display: flex; flex-direction: row; align-items: center; justify-content: space-between; padding-top: 8px; padding-bottom:10px; background-color: #57ae57">
        <a href="/" style="margin-left:12px; font-size: 24px; color: white; text-decoration: none">
            DentalClinic
        </a>
        <span style="margin-right: 12px">
<%--                    <span style="margin-right: 12px">--%>
<%--                        <a style="background-color: lightyellow; color: black; padding: 8px; border-radius: 8px;" href="?lang=pl">pl</a> |--%>
<%--                        <a style="background-color: lightyellow; color: black; padding: 8px; border-radius: 8px;" href="?lang=en">en</a> |--%>
<%--                        <a style="background-color: lightyellow; color: black; padding: 8px; border-radius: 8px;" href="?lang=de">de</a>--%>
<%--                    </span>--%>
                    <span style="color: white">
                        <spring:message code="label.welcome"/> : ${login} |
                        <a href="javascript:formSubmit()"> <spring:message code="label.logout"/></a>
                    </span>
                </span>
    </div>
    <!-- csrf for log out-->
    <form action="/logout" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center">
        <div style="width: 400px; height: fit-content; box-sizing: border-box">
            <form:form style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px" method="post" action="updateUser" modelAttribute="appUser">
                <h1>User Profile</h1>
                <table>
                    <tr>
                        <td><form:hidden path="id"/>
                    </tr>
                    <tr>
                        <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
                        <td><form:input path="login"/></td>
                        <td><form:errors path="login"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
                        <td><form:input type="password" path="password" /></td>
                        <td><form:errors path="password"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName"><spring:message code="label.firstName"/></form:label></td>
                        <td><form:input path="firstName"/></td>
                        <td><form:errors path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName"><spring:message code="label.lastName"/></form:label></td>
                        <td><form:input path="lastName"/></td>
                        <td><form:errors path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
                        <td><form:input path="email"/></td>
                        <td><form:errors path="email"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
                        <td><form:input path="telephone"/></td>
                        <td><form:errors path="telephone"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="pesel.PESEL"><spring:message code="label.pesel"/></form:label></td>
                        <td><form:input path="pesel.PESEL" /></td>
                        <td><form:errors path="pesel"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="address"><spring:message code="label.address"/></form:label></td>
                        <td><form:input path="address" /></td>
                        <td><form:errors path="address"/></td>
                        <td><form:errors path="address"/></td>
                    </tr>
                </table>
                <c:if test="${not empty error}">
                    <div style="color: red">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div style="color: green">${success}</div>
                </c:if>
                <button type="submit"><spring:message code="label.editUserData"/></button>
                <a class="back" href="/">Go Back</a>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
