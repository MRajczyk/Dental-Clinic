<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 16.06.2024
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Dental Visits</title>
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

        select {
            width: 100%;
            padding: 8px;
            border-radius: 12px;
            border: 2px solid black;
        }

        textarea {
            width: 100%;
            height: 180px;
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
            <h1>Dental visits</h1>
            <form:form method="post" action="addVisit" modelAttribute="dentalVisit" cssStyle="text-align: center; display: flex; flex-direction: column; gap: 6px; align-items: center">
                <table>
                    <tr>
                        <td><form:hidden path="id"/>
                    </tr>
                    <tr>
                        <td><form:label path="paid">Paid</form:label></td>
                        <td><form:checkbox path="paid"/></td>
                        <td><form:errors path="paid"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="booked">Booked</form:label></td>
                        <td><form:checkbox path="booked"/></td>
                        <td><form:errors path="booked"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="dentist">Dentist</form:label></td>
                        <td><form:input path="dentist"/></td>
                        <td><form:errors path="dentist"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="patientId">Patient Id</form:label></td>
                        <td><form:input path="patientId"/></td>
                        <td><form:errors path="patientId"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="medicalDescription">Medical Description</form:label></td>
                        <td><form:textarea path="medicalDescription" cssStyle="resize: none"/></td>
                        <td><form:errors path="medicalDescription"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="appointmentDateTime">Appointment date</form:label></td>
                        <td><form:input type="datetime-local" path="appointmentDateTime"/></td>
                        <td><form:errors path="appointmentDateTime"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="bookedDateTime">Booked date</form:label></td>
                        <td><form:input type="datetime-local" path="bookedDateTime"/></td>
                        <td><form:errors path="bookedDateTime"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="procedures">Procedures</form:label></td>
                        <td><form:select path="procedures" multiple="true">
                            <form:options items="${proceduresList}" itemValue="id" itemLabel="procedureName"/>
                        </form:select></td>
                        <td><form:errors path="procedures"/></td>
                    </tr>
                </table>
                <c:if test="${dentalVisit.id == 0}">
                    <button type="submit">Add visit</button>
                </c:if>
                <c:if test="${dentalVisit.id != 0}">
                    <button type="submit">Edit visit</button>
                </c:if>
                <c:if test="${dentalVisit.id == 0}">
                    <a class="back" href="/">Back</a>
                </c:if>
                <c:if test="${dentalVisit.id != 0}">
                    <a class="back" href="/visits">Stop editing</a>
                </c:if>
            </form:form>
        </div>
        <c:if test="${!empty dentalVisitsList}">
            <div style="display: flex; flex-direction: column; gap: 6px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center; padding-top: 12px; box-sizing: border-box">
                <h3>Dental visits</h3>
                <table class="data">
                    <tr>
                        <th>Visit date</th>
                        <th>Booked by user</th>
                        <th>Paid for</th>
                        <th>Dentist</th>
                        <th>Patient Id</th>
                        <th>Procedures</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${dentalVisitsList}" var="visit">
                        <tr style="line-height: 40px">
                            <td>${visit.appointmentDateTime} </td>
                            <td>${visit.booked ? "yes" : "no"} </td>
                            <td>${visit.paid ? "yes" : "no"}</td>
                            <td>${visit.dentist}</td>
                            <td>${visit.patientId}</td>
<%--                            <td>[<c:forEach items="${visit.procedures}" var="proced">--%>
<%--                                ${proced.procedureName}--%>
<%--                            </c:forEach>]</td>--%>
                            <td>
                                ${visit.procedures.size()}
                            </td>
                            <td><a class="back" href="deleteVisit/${visit.id}">Delete</a></td>
                            <td><a class="back" href="visits?visitId=${visit.id}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>