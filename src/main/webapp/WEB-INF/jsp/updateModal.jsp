<%--
  Created by IntelliJ IDEA.
  User: gustavs.bergs
  Date: 2/7/2019
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<html>
<body>
<div id="modalContent" class="modal-content">
    <span class="close" onclick="onClickSpan('modalForm')">&times;</span>
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
        <input id="newStartDateUpdate" class="modalInput" type="date">
        <br>
        <br>
        <div>
            <label for="hourRangeStartUpdate"><b>Starting time:</b></label>
            <br>
            <input type="range" min="0" max="23" class="slider" id="hourRangeStartUpdate" value="12"
                   oninput="createSlider('hourRangeStartUpdate', 'hourValueStartUpdate', 'minuteRangeStartUpdate', 'minuteValueStartUpdate', 'timeOfDayCreateStartUpdate')">
            <input type="range" min="0" max="59" class="slider" id="minuteRangeStartUpdate" value="30"
                   oninput="createSlider('hourRangeStartUpdate', 'hourValueStartUpdate', 'minuteRangeStartUpdate', 'minuteValueStartUpdate', 'timeOfDayCreateStartUpdate')">
            <p class="sliderValue" value="12"><span id="hourValueStartUpdate">12</span> : </p>
            <p class="sliderValue" value="30"><span id="minuteValueStartUpdate">30</span><span
                    id="timeOfDayCreateStartUpdate">  PM</span>
            </p>
        </div>
        <br>
        <label for="newEndDateUpdate"><b>Ending date:</b></label>
        <br>
        <input id="newEndDateUpdate" class="modalInput" type="date">
        <br>
        <br>
        <div>
            <label for="hourRangeEndUpdate"><b>Starting time:</b></label>
            <br>
            <input type="range" min="0" max="23" class="slider" id="hourRangeEndUpdate" value="12"
                   oninput="createSlider('hourRangeEndUpdate', 'hourValueEndUpdate', 'minuteRangeEndUpdate', 'minuteValueEndUpdate', 'timeOfDayCreateEndUpdate')">
            <input type="range" min="0" max="59" class="slider" id="minuteRangeEndUpdate" value="30"
                   oninput="createSlider('hourRangeEndUpdate', 'hourValueEndUpdate', 'minuteRangeEndUpdate', 'minuteValueEndUpdate', 'timeOfDayCreateEndUpdate')">
            <p class="sliderValue" value="12"><span id="hourValueEndUpdate">12</span> : </p>
            <p class="sliderValue" value="30"><span id="minuteValueEndUpdate">30</span><span
                    id="timeOfDayCreateEndUpdate">  PM</span>
            </p>
        </div>
        <br>
        <button class="modalButton" onclick="updateTask(document.getElementById('newName').value, document.getElementById('newDesc').value, document.getElementById('newStartDateUpdate').value, document.getElementById('hourRangeStartUpdate').value,
            document.getElementById('minuteRangeStartUpdate').value, document.getElementById('newEndDateUpdate').value, document.getElementById('hourRangeEndUpdate').value, document.getElementById('minuteRangeEndUpdate').value)">
            UPDATE
        </button>
    </div>
</div>
</body>
</html>
