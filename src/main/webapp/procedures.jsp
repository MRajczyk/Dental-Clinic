<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 16.06.2024
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Procedures</title>
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
    <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; gap: 20px">
        <div style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px; box-sizing: border-box">
            <h1>Procedures</h1>
            <form:form method="post" action="addProcedure" modelAttribute="procedure" cssStyle="text-align: center; display: flex; flex-direction: column; gap: 6px; align-items: center">
                <table>
                    <tr>
                        <td><form:hidden path="id"/>
                    </tr>
                    <tr>
                        <td><form:label path="procedureName">Procedure name</form:label></td>
                        <td><form:input path="procedureName"/></td>
                        <td><form:errors path="procedureName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="cost">Procedure cost</form:label></td>
                        <td><form:input type="number" path="cost"/></td>
                        <td><form:errors path="cost"/></td>
                    </tr>
                </table>
                <button type="submit">Add procedure</button>
                <a class="back" href="/">Go Back</a>
            </form:form>
        </div>
        <div style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px; box-sizing: border-box">
            <h3>List of procedures</h3>
            <c:if test="${!empty procedures}">
                <table class="data">
                    <tr>
                        <th><span>Procedure name</span></th>
                        <th><span>Procedure cost</span></th>
                    </tr>
                    <c:forEach items="${procedures}" var="procedure">
                        <tr>
                            <td>${procedure.procedureName} </td>
                            <td>${procedure.cost} </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
