<html>
<head>
    <title>Add Complaint</title>
</head>
<body>
    <h2>Add Complaint</h2>

    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="newRecord">

        Complainant:
        <input type="text" name="complainant"><br><br>

        Title:
        <input type="text" name="title"><br><br>

        Department:
        <input type="text" name="department"><br><br>

        Complaint Date (milliseconds):
        <input type="text" name="complaintDate"><br><br>

        Status:
        <input type="text" name="status"><br><br>

        Remarks:
        <input type="text" name="remarks"><br><br>

        <input type="submit" value="Add Complaint">
    </form>
</body>
</html>
