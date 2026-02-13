<%@ page import="com.wipro.complaint.bean.ComplaintBean" %>

<html>
<head>
    <title>Complaint Details</title>
</head>
<body>

<%
    ComplaintBean bean = (ComplaintBean) request.getAttribute("bean");
    String message = (String) request.getAttribute("message");

    if (bean == null) {
%>
        <h3>No matching records exists! Please try again!</h3>
<%
    } else {
%>
        <h2>Complaint Details</h2>
        Complaint ID: <%= bean.getComplaintId() %><br>
        Complainant: <%= bean.getComplainant() %><br>
        Title: <%= bean.getTitle() %><br>
        Department: <%= bean.getDepartment() %><br>
        Date: <%= bean.getComplaintDate() %><br>
        Status: <%= bean.getStatus() %><br>
        Remarks: <%= bean.getRemarks() %><br>
<%
    }
%>

</body>
</html>
