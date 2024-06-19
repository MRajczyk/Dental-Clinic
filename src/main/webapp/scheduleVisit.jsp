<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 16.06.2024
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
    <title>Schedule an appointment</title>
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
        <div style="height: fit-content; box-sizing: border-box">
            <div style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px">
                <c:if test="${empty visits}">
                    <div>No appointments available</div>
                </c:if>
                <c:if test="${not empty visits}">
                    <h3>Appointments available</h3>
                </c:if>
                <c:if test="${!empty visits}">
                    <table>
                        <tr>
                            <th>Appointment Time</th>
                            <th>Appointment Doctor</th>
                            <th>Appointment Procedures</th>
                            <th>&nbsp;</th>
                        </tr>
                        <c:forEach items="${visits}" var="visit">
                            <tr>
                                <td>${visit.appointmentDateTime} </td>
                                <td>${visit.dentist} </td>
                                <td>[<c:forEach items="${visit.procedures}" var="proced">
                                    ${proced.procedureName}
                                </c:forEach>]</td>
                                <td><a class="back" href="/scheduleVisit/${visit.id}">Schedule Appointment</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${not empty error}">
                    <div style="color: red">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div style="color: green" class="msg">${msg}</div>
                </c:if>
                <a class="back" href="/">Back</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
