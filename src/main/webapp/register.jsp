<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 16.06.2024
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Register page</title>
    <script src="https://www.google.com/recaptcha/api.js"></script>
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

        button:hover {
            background-color: #004805;
            color: white;
        }

        tr {
            text-align: center;
        }
    </style>
</head>

<body onload='document.login.focus();'>

<div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center">
    <div style="width: 400px; height: fit-content; box-sizing: border-box">
        <form:form style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px" method="post" action="registerAppUser" modelAttribute="appUser">
            <form:hidden path="id"/>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="login"><spring:message code="label.login"/></form:label>
                <form:input path="login"/>
                <form:errors cssStyle="color: red" path="login"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="password"><spring:message code="label.password"/></form:label>
                <form:input type="password" path="password" />
                <form:errors cssStyle="color: red" path="password"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="firstName"><spring:message code="label.firstName"/></form:label>
                <form:input path="firstName"/>
                <form:errors cssStyle="color: red" path="firstName"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="lastName"><spring:message code="label.lastName"/></form:label>
                <form:input path="lastName"/>
                <form:errors cssStyle="color: red" path="lastName"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="email"><spring:message code="label.email"/></form:label>
                <form:input path="email"/>
                <form:errors cssStyle="color: red" path="email"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="telephone"><spring:message code="label.telephone"/></form:label>
                <form:input path="telephone"/>
                <form:errors cssStyle="color: red" path="telephone"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="pesel.PESEL"><spring:message code="label.pesel"/></form:label>
                <form:input path="pesel.PESEL" />
                <form:errors cssStyle="color: red" path="pesel.PESEL"/>
            </span>
            <span style="display: flex; flex-direction: column; gap: 4px">
                <form:label path="address"><spring:message code="label.address"/></form:label>
                <form:input path="address" />
                <form:errors cssStyle="color: red" path="address"/>
            </span>
            <div class="g-recaptcha" data-sitekey=${captchaSiteKey}></div>
            <button type="submit">Register</button>
        </form:form>
        <c:if test="${not empty error}">
            <div  style="color: red">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div style="color: green">${msg}</div>
        </c:if>
        <p>Already have an account? <a href="/login">Login!</a></p>
    </div>
</div>

</body>
</html>
