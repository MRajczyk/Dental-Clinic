<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Login Page</title>
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

<body>
	<div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center">
		<div style="width: 400px; height: fit-content; box-sizing: border-box">
			<form style="display: flex; flex-direction: column; gap: 16px; padding: 20px; border: 2px solid green; border-radius: 16px; justify-content: center; align-items: center" name='loginForm' action="<c:url value='/login' />" method='POST'>
				<input type="text" name='login' id="inputUsername" placeholder="Username" required autofocus/>
				<input type="password" name='password' id="inputPassword" placeholder="Password" required/>
				<button type="submit">Sign in</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<c:if test="${not empty error}">
				<div style="color: red">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div style="color: green" class="msg">${msg}</div>
			</c:if>
			<p>Don't have an account yet? <a style="color: blue" href="/register">Register!</a></p>
		</div>
	</div>
</body>
</html>
