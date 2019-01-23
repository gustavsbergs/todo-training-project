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
                        var row = document.createElement("TR");
                        row.setAttribute("class", "tr")
                        var nameTd = document.createElement("TD");
                        nameTd.setAttribute("class", "td");
                        var name = document.createTextNode(this.name);
                        nameTd.appendChild(name);
                        row.appendChild(nameTd);

                        var descriptionTd = document.createElement("TD");
                        descriptionTd.setAttribute("class", "td");
                        var description = document.createTextNode(this.description);
                        descriptionTd.appendChild(description);
                        row.appendChild(descriptionTd);

                        var sDateTd = document.createElement("TD");
                        sDateTd.setAttribute("class", "td");
                        var sDate = document.createTextNode(this.startingDate);
                        sDateTd.appendChild(sDate);
                        row.appendChild(sDateTd);

                        var eDateTd = document.createElement("TD");
                        eDateTd.setAttribute("class", "td");
                        var eDate = document.createTextNode(this.endingDate);
                        eDateTd.appendChild(eDate);
                        row.appendChild(eDateTd);

                        var delButtonTd = document.createElement("TD");
                        delButtonTd.setAttribute("class", "td");
                        var deleteCurrent = document.createElement("BUTTON");
                        var deleteButtonName = document.createTextNode("DELETE");
                        deleteCurrent.setAttribute("id", this.id);
                        deleteCurrent.setAttribute("class", "tableDeleteButton");
                        deleteCurrent.setAttribute("onclick","deleteTask("+deleteCurrent.getAttribute("id")+")");

                        deleteCurrent.appendChild(deleteButtonName);
                        delButtonTd.appendChild(deleteCurrent);
                        row.appendChild(delButtonTd);

                        var updateButtonTd = document.createElement("TD");
                        updateButtonTd.setAttribute("class", "td");
                        var updateButton = document.createElement("BUTTON");
                        var updateButtonName = document.createTextNode("UPDATE");
                        updateButton.setAttribute("id", this.id);
                        updateButton.setAttribute("class", "tableUpdateButton")
                        updateButton.setAttribute("onclick", "updateModal("+updateButton.getAttribute("id")+")");
                        updateButton.appendChild(updateButtonName);
                        updateButtonTd.appendChild(updateButton);
                        row.appendChild(updateButtonTd);

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

//Updates a task with new name, description and date/time parameters
function updateTask(name, description, startingDate, endingDate){
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

//Creates a new task
function createTask(name, description, startDateAndTime, endDateAndTime) {
    $.ajax(
        {
            url: "http://localhost:8080/tasks/new",
            type: "POST",
            dataType: "JSON",
            headers: {
                'Content-Type':'application/json'
            },
            data: JSON.stringify({"name": name, "description": description, "startingDate" : startDateAndTime, "endingDate" : endDateAndTime}),
            success: function () {
                alert("Task created!");
                window.location='http://localhost:8080/home';
            },
            error: function(){
                alert("Oops! Something went wrong!");
            }

        }
    );
}

//For task update modal control
function updateModal(id){
    var modal = document.getElementById("modalForm");
    modal.style.display = "block";
    currentToUpdate = id;
}

//For task creation modal control
function createTaskModal(){
    var modal = document.getElementById("modalFormCreate");
    modal.style.display= "block";
}

//For task update modal closing span
function onClickSpan(){
    var modal = document.getElementById("modalForm");
    modal.style.display = "none";

}

//For task creation modal closing span
function onClickCreateSpan() {
    var modalCreate = document.getElementById("modalFormCreate");
    modalCreate.style.display = "none";

}



