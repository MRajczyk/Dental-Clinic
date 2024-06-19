<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 09.04.2024
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title><spring:message code="label.manageUsers"/>Manage user</title>
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
        <c:if test="${appUser.id != 0}">
            <div style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px; box-sizing: border-box">
                <h1>Manage users</h1>
                <form:form method="post" action="editAppUser" modelAttribute="appUser" cssStyle="text-align: center">
                    <table>
                        <tr>
                            <td><form:hidden path="id"/>
                        </tr>
                        <c:if test="${appUser.id != 0}">
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
                                <td><form:label path="enabled"><spring:message code="label.enabled"/></form:label></td>
                                <td><form:checkbox path="enabled" /></td>
                                <td><form:errors path="enabled"/></td>
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
                            <tr>
                                <td><form:label path="role"><spring:message code="label.role"/></form:label></td>
                                <td><form:select path="role">
                                    <form:options items="${appUserRoleList}"/>
                                </form:select></td>
                                <td><form:errors path="role"/></td>
                            </tr>
                        </c:if>
                    </table>
                    <button style="margin-top: 16px" type="submit"><spring:message code="label.editAppUser"/></button>
                </form:form>
            </div>
        </c:if>
        <div style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px; box-sizing: border-box">
            <h3><spring:message code="label.userList"/></h3>
            <c:if test="${!empty appUserList}">
                <table class="data">
                    <tr>
                        <th><spring:message code="label.firstName"/></th>
                        <th><spring:message code="label.lastName"/></th>
                        <th><spring:message code="label.email"/></th>
                        <th><spring:message code="label.telephone"/></th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach items="${appUserList}" var="appUser">
                        <tr style="line-height: 40px">
                            <td>${appUser.firstName} </td>
                            <td>${appUser.lastName} </td>
                            <td>${appUser.email}</td>
                            <td>${appUser.telephone}</td>
                            <td><a class="back" href="delete/${appUser.id}">delete</a></td>
                            <td><a class="back" href="appUsers?appUserId=${appUser.id}">edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <a class="back" href="/">Go Back</a>
        </div>
    </div>
</div>
</body>
</html>
