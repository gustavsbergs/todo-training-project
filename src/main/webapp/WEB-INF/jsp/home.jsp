<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<div>
<h1 class="header">
    <pre>
  _____ _    _  _____ _______  __      _______             __  __           ___________ _   _  _____    _______        _____ _  __   _____  _               _   _ _   _ ______ _____
  / ____| |  | |/ ____|__   __|/\ \    / / ____|      /\   |  \/  |   /\    |___  /_   _| \ | |/ ____|  |__   __|/\    / ____| |/ /  |  __ \| |        /\   | \ | | \ | |  ____|  __ \
  | |  __| |  | | (___    | |  /  \ \  / / (___       /  \  | \  / |  /  \      / /  | | |  \| | |  __      | |  /  \  | (___ | ' /   | |__) | |       /  \  |  \| |  \| | |__  | |__) |
 | | |_ | |  | |\___ \   | | / /\ \ \/ / \___ \     / /\ \ | |\/| | / /\ \    / /   | | | . ` | | |_ |     | | / /\ \  \___ \|  <    |  ___/| |      / /\ \ | . ` | . ` |  __| |  _  /
 | |__| | |__| |____) |  | |/ ____ \  /  ____) |   / ____ \| |  | |/ ____ \  / /__ _| |_| |\  | |__| |     | |/ ____ \ ____) | . \   | |    | |____ / ____ \| |\  | |\  | |____| | \ \
  \______|\____/|_____/   |_/_/    \_\/  |_____/   /_/    \_\_|  |_/_/    \_\/_____|_____|_| \_|\_____|     |_/_/    \_\_____/|_|\_\  |_|    |______/_/    \_\_| \_|_| \_|______|_|  \_\

    </pre>
</h1>
</div>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="/resources/css/home.css"/>

    <spring:url value="/resources/js/ajax.js" var="ajaxJs"/>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ajaxJs}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            retrieveTask();
        });
    </script>
    <title>Task planner</title>
</head>
<body class="documentBody">
<%@include file="table.jsp"%>
<br>
<button class="createNewButton" name="Create a NEW task!" onclick="createTaskModal();">CREATE TASK</button>
<div id="modalForm" class="modal">
<%@include file="updateModal.jsp"%>
</div>
<div id="modalFormCreate" class="modalCreate">
<%@include file="createModal.jsp"%>
</div>
<div id="modalDisplay" class="modal">
<%@include file="rowModal.jsp"%>
</div>
</body>
</html>