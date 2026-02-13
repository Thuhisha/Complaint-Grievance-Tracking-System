<%@ page import="java.util.List" %>
<%@ page import="com.wipro.complaint.bean.ComplaintBean" %>

<html>
<head>
    <title>All Complaints</title>
</head>
<body>

<%
    List<ComplaintBean> list =
        (List<ComplaintBean>) request.getAttribute("list");

    if (list == null || list.isEmpty()) {
%>
        <h3>No records available!</h3>
<%
    } else {
%>
        <h2>All Complaints</h2>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Complainant</th>
                <th>Title</th>
                <th>Department</th>
                <th>Date</th>
                <th>Status</th>
                <th>Remarks</th>
            </tr>

            <%
                for (ComplaintBean bean : list) {
            %>
            <tr>
                <td><%= bean.getComplaintId() %></td>
                <td><%= bean.getComplainant() %></td>
                <td><%= bean.getTitle() %></td>
                <td><%= bean.getDepartment() %></td>
                <td><%= bean.getComplaintDate() %></td>
                <td><%= bean.getStatus() %></td>
                <td><%= bean.getRemarks() %></td>
            </tr>
            <%
                }
            %>
        </table>
<%
    }
%>

</body>
</html>
