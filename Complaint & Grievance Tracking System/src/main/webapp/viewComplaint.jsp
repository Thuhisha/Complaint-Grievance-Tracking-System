<html>
<head>
    <title>View Complaint</title>
</head>
<body>
    <h2>View Complaint</h2>

    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="viewRecord">

        Title:
        <input type="text" name="title"><br><br>

        Complaint Date (milliseconds):
        <input type="text" name="complaintDate"><br><br>

        <input type="submit" value="View Complaint">
    </form>
</body>
</html>
