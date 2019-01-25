var currentToUpdate = 0;

//Retrieves tasks from database and populates a dynamic table based on the amount of entries!
function retrieveTask() {

    $.ajax(
        {
            url: "http://localhost:8080/tasks",
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                $(data).each(
                    function () {

                        //TR for single task
                        var row = document.createElement("TR");
                        row.setAttribute("class", "tr")

                        //TD for task name
                        var nameTd = createTD("class", "td", this.name);
                        row.appendChild(nameTd);

                        //TD for task description
                        var descriptionTd = createTD("class", "td", this.description);
                        row.appendChild(descriptionTd);

                        //TD for starting date and time
                        var sDateTd = createTD("class", "td", this.startingDate);
                        row.appendChild(sDateTd);

                        //TD for ending date and time
                        var eDateTd = createTD("class", "td", this.endingDate);
                        row.appendChild(eDateTd);

                        //Update button for task row.
                        var updateButtonTd = createUpdateButton(this.id);
                        row.appendChild(updateButtonTd);

                        //Delete button for task row.
                        var delButtonTd = createDeleteButton(this.id);
                        row.appendChild(delButtonTd);

                        document.getElementById("tbody").appendChild(row);
                    }
                );
            },
            error: function (data) {
                alert("Oops!");
            }
        }
    );
}

//Checks if any rows were created. If not, hides TH
//Doesn't work in incognito mode
function hideTh(){

    if ($('#tasks > tbody > tr').length == 0){
        $('#tasks').css('display','none');
    }
}

//Function that creates update button
function createUpdateButton(id){
    var updateButtonTd = document.createElement("TD");
    updateButtonTd.setAttribute("class", "td");
    var updateButton = document.createElement("BUTTON");
    var updateButtonName = document.createTextNode("UPDATE");
    updateButton.setAttribute("id", id);
    updateButton.setAttribute("class", "tableUpdateButton")
    updateButton.setAttribute("onclick", "updateModal("+updateButton.getAttribute("id")+")");
    updateButton.appendChild(updateButtonName);
    updateButtonTd.appendChild(updateButton);

    return updateButtonTd;
}

//Function that creates delete button
function createDeleteButton(id){
    var delButtonTd = document.createElement("TD");
    delButtonTd.setAttribute("class", "td");
    var deleteCurrent = document.createElement("BUTTON");
    var deleteButtonName = document.createTextNode("DELETE");
    deleteCurrent.setAttribute("id", id);
    deleteCurrent.setAttribute("class", "tableDeleteButton");
    deleteCurrent.setAttribute("onclick","deleteTask("+deleteCurrent.getAttribute("id")+")");
    deleteCurrent.appendChild(deleteButtonName);
    delButtonTd.appendChild(deleteCurrent);

    return delButtonTd;
}

//Function that creates a TD with 1 attribute value
function createTD(attributeType, attributeValue, value){
    var td = document.createElement("TD");
    td.setAttribute(attributeType, attributeValue);
    var tdValue = document.createTextNode(value);
    td.appendChild(tdValue);
    return td;
}

//Validates and creates data for a new task
function createTask(name, description, startDate, startHours, startMinutes, endDate, endHours, endMinutes) {
    if (startDate != "" && endDate != "" && name != "" && description != "") {
        var startingDateAndTime = startDate + " " + startHours + ":" + startMinutes;
        var endingDateAndTime = endDate + " " + endHours + ":" + endMinutes;
        var validatedName = name;
        var validatedDescription = description;
        createAjax(validatedName, validatedDescription, startingDateAndTime, endingDateAndTime);
    } else {
        alert("Please input valid data!");

    }
}

//Validates and creates data for updating a task
function updateTask(name, description, startDate, startHours, startMinutes, endDate, endHours, endMinutes) {
    if (startDate != "" && endDate != "" && name != "" && description != "") {
        var startingDateAndTime = startDate + " " + startHours + ":" + startMinutes;
        var endingDateAndTime = endDate + " " + endHours + ":" + endMinutes;
        var validatedName = name;
        var validatedDescription = description;
        updateAjax(validatedName, validatedDescription, startingDateAndTime, endingDateAndTime);
    } else {
        alert("Please input valid data!");

    }
}

//Listener
//Controls slider and value output for create a new task starting time slider
function createStartSlider(){
    var slider = document.getElementById("hourRangeStart");
    var output = document.getElementById("hourValueStart");
    var amOrPm = document.getElementById("timeOfDayCreateStart");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        if(this.value.length == 1) {
            output.innerHTML = 0 + this.value;
        } else {
            output.innerHTML = this.value;
        }
        if(this.value < 12){
            amOrPm.innerHTML = "  AM";
        } else {
            amOrPm.innerHTML = "  PM";
        }
    };

    var slider2 = document.getElementById("minuteRangeStart");
    var output2 = document.getElementById("minuteValueStart");
    output2.innerHTML = slider2.value;

    slider2.oninput = function() {
        if(this.value.length == 1) {
            output2.innerHTML = 0 + this.value;
        } else {
            output2.innerHTML = this.value;
        }
    }
}

//Listener
//Controls slider and value output for create a new task ending time slider
function createEndSlider(){
    var slider = document.getElementById("hourRangeEnd");
    var output = document.getElementById("hourValueEnd");
    var amOrPm = document.getElementById("timeOfDayCreateEnd");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        if(this.value.length == 1) {
            output.innerHTML = 0 + this.value;
        } else {
            output.innerHTML = this.value;
        }
        if(this.value < 12){
            amOrPm.innerHTML = "  AM";
        } else {
            amOrPm.innerHTML = "  PM";
        }
    };

    var slider2 = document.getElementById("minuteRangeEnd");
    var output2 = document.getElementById("minuteValueEnd");
    output2.innerHTML = slider2.value;

    slider2.oninput = function() {
        if(this.value.length == 1) {
            output2.innerHTML = 0 + this.value;
        } else {
            output2.innerHTML = this.value;
        }
    }
}

//Listener
//Controls update task start time slider
function updateStartSlider(){
    var slider = document.getElementById("hourRangeStartUpdate");
    var output = document.getElementById("hourValueStartUpdate");
    var amOrPm = document.getElementById("timeOfDayUpdateStart");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        if(this.value.length == 1) {
            output.innerHTML = 0 + this.value;
        } else {
            output.innerHTML = this.value;
        }
        if(this.value < 12){
            amOrPm.innerHTML = "  AM";
        } else {
            amOrPm.innerHTML = "  PM";
        }
    };

    var slider2 = document.getElementById("minuteRangeStartUpdate");
    var output2 = document.getElementById("minuteValueStartUpdate");
    output2.innerHTML = slider2.value;

    slider2.oninput = function() {
        if(this.value.length == 1) {
            output2.innerHTML = 0 + this.value;
        } else {
            output2.innerHTML = this.value;
        }
    }
}

//Listener
//Controls update task end time slider
function updateEndSlider(){
    var slider = document.getElementById("hourRangeEndUpdate");
    var output = document.getElementById("hourValueEndUpdate");
    var amOrPm = document.getElementById("timeOfDayUpdateEnd");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        if(this.value.length == 1) {
            output.innerHTML = 0 + this.value;
        } else {
            output.innerHTML = this.value;
        }
        if(this.value < 12){
            amOrPm.innerHTML = "  AM";
        } else {
            amOrPm.innerHTML = "  PM";
        }
    };

    var slider2 = document.getElementById("minuteRangeEndUpdate");
    var output2 = document.getElementById("minuteValueEndUpdate");
    output2.innerHTML = slider2.value;

    slider2.oninput = function() {
        if(this.value.length == 1) {
            output2.innerHTML = 0 + this.value;
        } else {
            output2.innerHTML = this.value;
        }
    }
}

//Listener
//Deletes a task by ID.
function deleteTask(id) {
    var url = "http://localhost:8080/tasks/delete/" + id;

    if (confirm("Are you sure you want to delete this task? If yes, press 'Ok'")) {
        $.ajax(
            {
                url: url,
                type: "DELETE",
                success: function () {
                    alert("Task deleted!")
                    setTimeout(location.reload().bind(location), 5000)
                },
                error: function () {
                    alert("Task not found!");
                }
            }
        );
    } else {
        window.close();
    }
}

//Listener
//Updates a task with new name, description and date/time parameters
function updateAjax(name, description, startingDate, endingDate){
    var url = "http://localhost:8080/tasks/update/"+currentToUpdate;
    $.ajax(
        {
            url: url,
            type: "PUT",
            dataType: "JSON",
            headers: {
                'Content-Type':'application/json'
            },
            data: JSON.stringify({"name": name,"description": description, "startingDate": startingDate, "endingDate": endingDate}),
            success: function () {
                alert("Task updated!");
                window.location='http://localhost:8080/home';
            },
            error: function(){
                alert("Oops! Something went wrong!");
            }

        }
    );
}

//Listener
//Creates new task by sending JSON to corresponding endpoint.
function createAjax(name, description, startingDateAndTime, endingDateAndTime){
    $.ajax(
        {
            url: "http://localhost:8080/tasks/new",
            type: "POST",
            dataType: "JSON",
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "name": name,
                "description": description,
                "startingDate": startingDateAndTime,
                "endingDate": endingDateAndTime
            }),
            success: function () {
                alert("Task created!");
                window.location = 'http://localhost:8080/home';
            },
            error: function () {
                alert("Oops! Something went wrong!");
            }

        }
    );
}

//Listener
//For task update modal control
function updateModal(id){
    var modal = document.getElementById("modalForm");
    modal.style.display = "block";
    currentToUpdate = id;
}

//Listener
//For task creation modal control
function createTaskModal(){
    var modal = document.getElementById("modalFormCreate");
    modal.style.display= "block";
}

//Listener
//For task update modal closing span
function onClickSpan(){
    var modal = document.getElementById("modalForm");
    modal.style.display = "none";

}

//Listener
//For task creation modal closing span
function onClickCreateSpan() {
    var modalCreate = document.getElementById("modalFormCreate");
    modalCreate.style.display = "none";

}



