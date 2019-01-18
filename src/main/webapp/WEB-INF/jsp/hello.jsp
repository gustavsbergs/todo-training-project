<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style type="text/css">
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
            retrieveTask();
        });
    </script>
    <title>Simple table</title>
</head>
<body>
<table id="tasks">
    <thead>
    <tr>
        <th>Task</th>
        <th>Description</th>
        <th>Start</th>
        <th>End</th>
    </tr>
    </thead>
    <tbody id="tbody">
    </tbody>

</table>
<script type="text/javascript">

    function retrieveTask() {
        $.ajax(
            {
                url: "http://localhost:8080/tasks",
                type: "GET",
                dataType: "JSON",
                success: function (data) {
                    $(data).each(
                        function () {
                            $("tbody").append(
                                "<tr><td>" + this.name
                                + "</td><td>"
                                + this.description
                                + "</td><td>"
                                + this.startingDate
                                + "</td><td>"
                                + this.endingDate
                                + "</td></tr>"
                            )
                        }
                    );
                },
                error: function (data) {
                    alert("Oops!");
                }
            }
        );
    }

</script>
</body>
</html>