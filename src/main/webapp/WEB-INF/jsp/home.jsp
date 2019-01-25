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
<table id="tasks" class="tasks" >
    <thead id="tasksHead">
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
<br>
<button class="createNewButton" name="Create a NEW task!" onclick="createTaskModal()">CREATE TASK</button>
<div id="modalForm" class="modal">
    <div id="modalContent" class="modal-content">
        <span class="close" onclick="onClickSpan()">&times;</span>
        <div id="formContent" class="modalContent">
            <h1 class="modalHeading">UPDATE SELECTED TASK!</h1>
            <br>
            <label for="newName"><b>Name</b></label>
            <br>
            <input id="newName" type="text" class="modalInput" placeholder="Enter new name here...">
            <br>
            <br>
            <label for="newDesc"><b>Description</b></label>
            <br>
            <input id="newDesc" type="text" class="modalInput" placeholder="Enter new description here..">
            <br>
            <br>
            <label for="newStartDateUpdate"><b>Starting date</b></label>
            <br>
            <input id="newStartDateUpdate" class="modalInput"  type="date">
            <br>
            <br>
            <div>
                <label for="hourRangeStartUpdate"><b>Starting time:</b></label>
                <br>
                <input type="range" min="0" max="23" class="slider" id="hourRangeStartUpdate" value="12" oninput="updateStartSlider()">
                <input type="range" min="0" max="59" class="slider" id="minuteRangeStartUpdate" value="30" oninput="updateStartSlider()">
                <p class="sliderValue" value="12"><span id="hourValueStartUpdate">12</span> : </p>
                <p class="sliderValue" value="30"><span id="minuteValueStartUpdate">30</span><span id="timeOfDayUpdateStart">  PM</span></p>
            </div>
            <br>
            <label for="newEndDateUpdate"><b>Ending date:</b></label>
            <br>
            <input id="newEndDateUpdate" class="modalInput"  type="date">
            <br>
            <br>
            <div>
                <label for="hourRangeStartUpdate"><b>Ending time:</b></label>
                <br>
                <input type="range" min="0" max="23" class="slider" id="hourRangeEndUpdate" oninput="updateEndSlider()">
                <input type="range" min="0" max="59" class="slider" id="minuteRangeEndUpdate" oninput="updateEndSlider()">
                <p class="sliderValue" ><span id="hourValueEndUpdate" >12</span> : </p>
                <p class="sliderValue" ><span id="minuteValueEndUpdate" >30</span><span id="timeOfDayUpdateEnd">  PM</span></p>
            </div>
            <br>
            <button class="modalButton" onclick="updateTask(document.getElementById('newName').value, document.getElementById('newDesc').value, document.getElementById('newStartDateUpdate').value, document.getElementById('hourRangeStartUpdate').value,
            document.getElementById('minuteRangeStartUpdate').value, document.getElementById('newEndDateUpdate').value, document.getElementById('hourRangeEndUpdate').value, document.getElementById('minuteRangeEndUpdate').value)">
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
            <label for="newStartDate"><b>Starting date</b></label>
            <br>
            <input id="newStartDate" class="modalInput"  type="date">
            <br>
            <br>
            <div>
                <label for="hourRangeStart"><b>Starting time:</b></label>
                <br>
                <input type="range" min="0" max="23" class="slider" id="hourRangeStart" value="12" oninput="createStartSlider()">
                <input type="range" min="0" max="59" class="slider" id="minuteRangeStart" value="30" oninput="createStartSlider()">
                <p class="sliderValue" value="12"><span id="hourValueStart">12</span> : </p>
                <p class="sliderValue" value="30"><span id="minuteValueStart">30</span><span id="timeOfDayCreateStart">  PM</span></p>
            </div>
            <br>
            <label for="newEndDate"><b>Ending date:</b></label>
            <br>
            <input id="newEndDate" class="modalInput"  type="date">
            <br>
            <br>
            <div>
                <label for="hourRangeStart"><b>Ending time:</b></label>
                <br>
                <input type="range" min="0" max="23" class="slider" id="hourRangeEnd" oninput="createEndSlider()">
                <input type="range" min="0" max="59" class="slider" id="minuteRangeEnd" oninput="createEndSlider()">
                <p class="sliderValue" ><span id="hourValueEnd" >12</span> : </p>
                <p class="sliderValue" ><span id="minuteValueEnd" >30</span><span id="timeOfDayCreateEnd">  PM</span></p>
            </div>
            <br>
            <button class="modalButton" onclick="createTask(document.getElementById('newNameCreate').value, document.getElementById('newDescCreate').value, document.getElementById('newStartDate').value, document.getElementById('hourRangeStart').value,
            document.getElementById('minuteRangeStart').value, document.getElementById('newEndDate').value, document.getElementById('hourRangeEnd').value, document.getElementById('minuteRangeEnd').value)">
                CREATE
            </button>
        </div>
    </div>
</div>
</body>
</html>