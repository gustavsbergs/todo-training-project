<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="/resources/css/modal.css"/>
    <spring:url value="/resources/js/ajax.js" var="ajaxJs"/>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ajaxJs}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            retrieveTask();
        });
    </script>
    <title>Simple table</title>
</head>
<body>
<button class="createNewButton" name="Create a NEW task!" onclick="createTaskModal()">CREATE TASK</button>
<table id="tasks" class="tasks">
    <thead>
    <tr class="tr">
        <th class="th">TASK</th>
        <th class="th">DESCRIPTION</th>
        <th class="th">START</th>
        <th class="th">END</th>
        <th class="th"></th>
        <th class="th"></th>
    </tr>
    </thead>
    <tbody id="tbody">
    </tbody>
</table>
<div id="modalForm" class="modal">
    <div id="modalContent" class="modal-content">
        <span class="close" onclick="onClickSpan()">&times;</span>
        <div id="formContent" class="modalContent">
            <h1 class="modalHeading">UPDATE SELECTED TASK!</h1>
            <br>
            <label for="newName"><b>Name</b></label>
            <br>
            <input id="newName" type="text" class="modalInput" placeholder="Enter the new name here...">
            <br>
            <br>
            <label for="newDesc"><b>Description</b></label>
            <br>
            <input id="newDesc" type="text" class="modalInput" placeholder="Enter the new description here...">
            <br>
            <br>
            <label for="newStart"><b>Starting date and time:</b></label>
            <br>
            <input id="newStart" class="modalInput"  type="text" placeholder="YYYY-MM-DD HH:MM">
            <br>
            <br>
            <label for="newEnd"><b>Ending date and time:</b></label>
            <br>
            <input id="newEnd" class="modalInput"  type="text" placeholder="YYYY-MM-DD HH:MM">
            <br>
            <br>
            <button class="modalButton" onclick="updateTask(document.getElementById('newName').value, document.getElementById('newDesc').value, document.getElementById('newStart').value, document.getElementById('newEnd').value)">
                UPDATE
            </button>
        </div>
    </div>
</div>
<div id="modalFormCreate" class="modalCreate">
    <div id="modalContentCreate" class="modal-content">
        <span class="close" onclick="onClickCreateSpan()">&times;</span>
        <div id="formContentCreate">

            <h1 class="modalHeading">CREATE A TASK</h1>
            <label for="newNameCreate"><b>Name</b></label>
            <br>
            <input id="newNameCreate" class="modalInput" type="text" placeholder="Enter name here...">
            <br>
            <br>
            <label for="newDescCreate"><b>Description</b></label>
            <br>
            <input id="newDescCreate" class="modalInput"  type="text" placeholder="Enter description here...">
            <br>
            <br>
            <label for="newStartDate"><b>Starting date and time:</b></label>
            <br>
            <input id="newStartDate" class="modalInput"  type="text" placeholder="YYYY-MM-DD HH:MM">
            <br>
            <br>
            <label for="newEndDate"><b>Ending date and time:</b></label>
            <br>
            <input id="newEndDate" class="modalInput"  type="text" placeholder="YYYY-MM-DD HH:MM">
            <br>
            <br>
            <button class="modalButton" onclick="createTask(document.getElementById('newNameCreate').value, document.getElementById('newDescCreate').value, document.getElementById('newStartDate').value, document.getElementById('newEndDate').value)">
                CREATE
            </button>
        </div>
    </div>
</div>
</body>
</html>