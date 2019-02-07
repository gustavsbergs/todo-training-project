<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<html>
<body>
<div id="modalContentCreate" class="modal-content">
    <span class="close" onclick="onClickSpan('modalFormCreate')">&times;</span>
    <div id="formContentCreate">
        <h1 class="modalHeading">CREATE A TASK</h1>
        <label for="newNameCreate"><b>Name</b></label>
        <br>
        <input id="newNameCreate" class="modalInput" type="text" placeholder="Enter name here...">
        <br>
        <br>
        <label for="newDescCreate"><b>Description</b></label>
        <br>
        <input id="newDescCreate" class="modalInput" type="text" placeholder="Enter description here...">
        <br>
        <br>
        <label for="newStartDate"><b>Starting date</b></label>
        <br>
        <input id="newStartDate" class="modalInput" type="date">
        <br>
        <br>
        <div>
            <label for="hourRangeStart"><b>Starting time:</b></label>
            <br>
            <input type="range" min="0" max="23" class="slider" id="hourRangeStart" value="12"
                   oninput="createSlider('hourRangeStart', 'hourValueStart', 'minuteRangeStart', 'minuteValueStart', 'timeOfDayCreateStart')">
            <input type="range" min="0" max="59" class="slider" id="minuteRangeStart" value="30"
                   oninput="createSlider('hourRangeStart', 'hourValueStart', 'minuteRangeStart', 'minuteValueStart', 'timeOfDayCreateStart')">
            <p class="sliderValue" value="12"><span id="hourValueStart">12</span> : </p>
            <p class="sliderValue" value="30"><span id="minuteValueStart">30</span><span
                    id="timeOfDayCreateStart">  PM</span>
            </p>
        </div>
        <br>
        <label for="newEndDate"><b>Ending date:</b></label>
        <br>
        <input id="newEndDate" class="modalInput" type="date">
        <br>
        <br>
        <div>
            <label for="hourRangeStart"><b>Ending time:</b></label>
            <br>
            <input type="range" min="0" max="23" class="slider" id="hourRangeEnd"
                   oninput="createSlider('hourRangeEnd', 'hourValueEnd', 'minuteRangeEnd', 'minuteValueEnd', 'timeOfDayCreateEnd')">
            <input type="range" min="0" max="59" class="slider" id="minuteRangeEnd"
                   oninput="createSlider('hourRangeEnd', 'hourValueEnd', 'minuteRangeEnd', 'minuteValueEnd', 'timeOfDayCreateEnd')">
            <p class="sliderValue"><span id="hourValueEnd">12</span> : </p>
            <p class="sliderValue"><span id="minuteValueEnd">30</span><span id="timeOfDayCreateEnd">  PM</span>
            </p>
        </div>
        <br>
        <button class="modalButton" onclick="createTask(document.getElementById('newNameCreate').value, document.getElementById('newDescCreate').value, document.getElementById('newStartDate').value, document.getElementById('hourRangeStart').value,
            document.getElementById('minuteRangeStart').value, document.getElementById('newEndDate').value, document.getElementById('hourRangeEnd').value, document.getElementById('minuteRangeEnd').value)">
            CREATE
        </button>
    </div>
</div>
</body>
</html>
