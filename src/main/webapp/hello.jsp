<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
    <title>Home</title>
    <style type="text/css">
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

        .menu-option {
            display:flex;
            align-items: center;
            justify-content: center;
            width: 400px;
            height: 60px;
            padding: 20px; border: 2px solid green; border-radius: 16px;
            background-color: darkseagreen;
            color: white;
            text-decoration: none;
            font-size: 2rem;
            margin-bottom: 0;
            text-align: center;
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

<div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: start; align-items: center">
    <div style="width: 100%; height: 48px; display: flex; flex-direction: row; align-items: center; justify-content: space-between; padding-top: 8px; padding-bottom:10px; background-color: #57ae57">
        <a href="/" style="margin-left:12px; font-size: 24px; color: white; text-decoration: none">
            DentalClinic
        </a>
        <span style="margin-right: 12px">
            <span style="margin-right: 12px">
                <a style="background-color: lightyellow; color: black; padding: 8px; border-radius: 8px;" href="?lang=pl">pl</a> |
                <a style="background-color: lightyellow; color: black; padding: 8px; border-radius: 8px;" href="?lang=en">en</a> |
                <a style="background-color: lightyellow; color: black; padding: 8px; border-radius: 8px;" href="?lang=de">de</a>
            </span>
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

    <sec:authorize access="isAnonymous()">
        <a href="/login"><spring:message code="label.login"/></a> <br/>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center">
            <div style="display: flex; flex-direction: column; justify-content: center; align-items: center">
                <a class="menu-option" href="/appUsers"><spring:message code="label.manageUsers"/></a> <br/>
                <a class="menu-option" href="/visits"><spring:message code="label.manageVisits"/></a> <br/>
                <a class="menu-option" href="/procedures"><spring:message code="label.manageProcedures"/></a> <br/>
            </div>
        </div>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_USER')">
        <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center">
            <div style="display: flex; flex-direction: column; justify-content: center; align-items: center">
                <a class="menu-option" href="/user"><spring:message code="label.personalInfo"/></a> <br/>
                <a class="menu-option" href="/scheduleVisit"><spring:message code="label.scheduleVisit"/></a> <br/>
                <a class="menu-option" href="/scheduledVisits"><spring:message code="label.viewScheduledVisits"/></a> <br/>
                <a class="menu-option" href="/pastVisits"><spring:message code="label.viewPastVisits"/></a> <br/>
            </div>
        </div>
    </sec:authorize>
</div>
</body>
</html>
