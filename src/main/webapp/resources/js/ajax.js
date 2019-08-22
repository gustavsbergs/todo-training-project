var currentToUpdate = 0;
var deleteCalled = false;
var restUrl;

//Retrieves tasks from database and populates a dynamic table based on the amount of entries
function retrieveTask() {
    $.ajax(
        {
            url: restUrl + "tasks",
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                $(data).each(
                    function () {
                        var row = createRow(this.id, this.name, this.startingDate, this.endingDate);
                        document.getElementById("tbody").appendChild(row);
                    }
                );
            },
            error: function () {
                alert("Failed to retrieve tasks! Try again.");
            }
        }
    );
}

function setRestUrl(url) {
    restUrl = url;
}

//Creates a table row with all the necessary elements.
function createRow(id, name, startingDate, endingDate) {
    var row = document.createElement("TR");
    row.setAttribute("id", id);
    row.setAttribute("class", "tr");
    row.setAttribute("onClick", "getSingleTask(" + row.getAttribute("id") + ")");

    var nameTd = createTD("class", "td", name);
    row.appendChild(nameTd);
    var sDateTd = createTD("class", "td", startingDate);
    row.appendChild(sDateTd);
    var eDateTd = createTD("class", "td", endingDate);
    row.appendChild(eDateTd);
    var updateButtonTd = createButton(id, "UPDATE");
    row.appendChild(updateButtonTd);
    var delButtonTd = createButton(id, "DELETE");
    row.appendChild(delButtonTd);

    return row;
}

//Function that creates a button
function createButton(id, buttonType) {
    var onClickFunction;
    var buttonClass;

    var buttonTd = document.createElement("TD");
    buttonTd.setAttribute("class", "td");
    var button = document.createElement("BUTTON");
    var buttonName = document.createTextNode(buttonType);
    button.setAttribute("id", id);
    if (buttonType === "UPDATE") {
        onClickFunction = "updateModal(" + button.getAttribute("id") + ")";
        buttonClass = "tableUpdateButton";
    } else {
        onClickFunction = "deleteTask(" + button.getAttribute("id") + ")";
        buttonClass = "tableDeleteButton"
    }
    button.setAttribute("class", buttonClass);
    button.setAttribute("onclick", onClickFunction);
    button.appendChild(buttonName);
    buttonTd.appendChild(button);

    return buttonTd;
}

//Function that creates a TD with 1 attribute value
function createTD(attributeType, attributeValue, value) {
    var td = document.createElement("TD");
    td.setAttribute(attributeType, attributeValue);
    var tdValue = document.createTextNode(value);
    td.appendChild(tdValue);
    return td;
}

//creates date string from input
function createDateString(date, hours, minutes) {
    var date = date + " " + hours + ":" + minutes;
    return date;
}

//Validates and creates data for a new task
function createTask(name, description, startDate, startHours, startMinutes, endDate, endHours, endMinutes) {
    if (startDate !== "" && endDate !== "" && name !== "" && description !== "") {
        var startingDateAndTime = createDateString(startDate, startHours, startMinutes);
        var endingDateAndTime = createDateString(endDate, endHours, endMinutes);

        createAjax(name, description, startingDateAndTime, endingDateAndTime);
    } else {
        alert("Please input valid data!");

    }
}

//Validates and creates data for updating a task
function updateTask(name, description, startDate, startHours, startMinutes, endDate, endHours, endMinutes) {
    if (startDate !== "" && endDate !== "" && name !== "" && description !== "") {
        var startingDateAndTime = startDate + " " + startHours + ":" + startMinutes;
        var endingDateAndTime = endDate + " " + endHours + ":" + endMinutes;

        updateAjax(name, description, startingDateAndTime, endingDateAndTime);
    } else {
        alert("Please input valid data!");

    }
}

//Listener
//Controls slider and value output for create a new task starting time slider
function createSlider(sliderh, valueh, sliderm, valuem, timeOfDay) {
    var slider = document.getElementById(sliderh);
    var output = document.getElementById(valueh);
    var amOrPm = document.getElementById(timeOfDay);
    output.innerText = slider.value;

    slider.oninput = function () {
        if (this.value.length === 1) {
            output.innerText = 0 + this.value;
        } else {
            output.innerText = this.value;
        }
        if (this.value < 12) {
            amOrPm.innerText = "  AM";
        } else {
            amOrPm.innerText = "  PM";
        }
    };

    var slider2 = document.getElementById(sliderm);
    var output2 = document.getElementById(valuem);
    output2.innerText = slider2.value;

    slider2.oninput = function () {
        if (this.value.length === 1) {
            output2.innerText = 0 + this.value;
        } else {
            output2.innerText = this.value;
        }
    }
}


//Listener
//Deletes a task by ID.
function deleteTask(id) {
    var url = restUrl + "tasks/delete/" + id;
    deleteCalled = true;
    if (confirm("Are you sure you want to delete this task? If yes, press 'Ok'")) {
        $.ajax(
            {
                url: url,
                type: "DELETE",
                success: function () {
                    alert("Task deleted!");
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
function updateAjax(name, description, startingDate, endingDate) {
    var url = restUrl + "tasks/update/" + currentToUpdate;
    $.ajax(
        {
            url: url,
            type: "PUT",
            dataType: "JSON",
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "name": name,
                "description": description,
                "startingDate": startingDate,
                "endingDate": endingDate
            }),
            success: function () {
                alert("Task updated!");
                window.location = 'http://localhost:8080/home';
            },
            error: function () {
                alert("Oops! Something went wrong!");
            }

        }
    );
}

//Listener
//Creates new task by sending JSON to corresponding endpoint.
function createAjax(name, description, startingDateAndTime, endingDateAndTime) {
    $.ajax(
        {
            url: restUrl + "tasks/new",
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
                alert("Oops! Something went wrong! Couldn't call: " + restUrl);
            }

        }
    );
}

//Listener
//For single task modal control
function getSingleTask(id) {
    if (deleteCalled === true) {
        deleteCalled = false;
        return;
    }

    var modal2 = document.getElementById("modalForm");
    if (modal2.style.display === "block") {
        return;
    }

    var taskName = "";
    var taskDescription = "";
    $.ajax(
        {
            url: restUrl + "tasks/" + id,
            type: "GET",
            dataType: "JSON",
            success: function (data) {

                taskName = data.name;
                taskDescription = data.description;
                document.getElementById("formDisplayHeader").innerHTML = "<h1 class='modalDisplayHeader'>" + taskName + "</h1>";
                document.getElementById("formDisplayBody").innerHTML = "<p class='modalDisplayBody'>" + taskDescription + "</p>";
            },
            error: function () {
                alert("No task found! Try again!");
            }
        }
    );

    var modal = document.getElementById("modalDisplay");
    modal.style.display = "block";
}

//Listener
//Controlling modal display when clicking on "update"
function updateModal(id) {
    var modal = document.getElementById("modalForm");
    modal.style.display = "block";
    currentToUpdate = id;
}

//Listener
//Controlling modal display when clicking on "create"
function createTaskModal() {
    var modal = document.getElementById("modalFormCreate");
    modal.style.display = "block";
}

//Listener
//Controlling modal display when pressing "x"
function onClickSpan(spanId) {
    var modal = document.getElementById(spanId);
    modal.style.display = "none";
}



