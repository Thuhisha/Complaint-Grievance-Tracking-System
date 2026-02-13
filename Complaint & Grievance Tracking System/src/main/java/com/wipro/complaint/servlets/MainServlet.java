package com.wipro.complaint.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;   // ADD THIS
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.complaint.bean.ComplaintBean;
import com.wipro.complaint.service.Administrator;

@WebServlet("/MainServlet")   // ⭐⭐⭐ ADD THIS LINE
public class MainServlet extends HttpServlet {


    Administrator admin = new Administrator();

  
    public String addRecord(HttpServletRequest request) {

        ComplaintBean bean = new ComplaintBean();

        bean.setComplainant(request.getParameter("complainant"));
        bean.setTitle(request.getParameter("title"));
        bean.setDepartment(request.getParameter("department"));
        bean.setStatus(request.getParameter("status"));
        bean.setRemarks(request.getParameter("remarks"));

        try {
            Date date = new Date(
                    Long.parseLong(request.getParameter("complaintDate")));
            bean.setComplaintDate(date);
        } catch (Exception e) {
            bean.setComplaintDate(null);
        }

        return admin.addRecord(bean);
    }


    public ComplaintBean viewRecord(HttpServletRequest request) {

        String title = request.getParameter("title");
        Date date = new Date(
                Long.parseLong(request.getParameter("complaintDate")));

        return admin.viewRecord(title, date);
    }

   
    public List<ComplaintBean> viewAllRecords(HttpServletRequest request) {

        return admin.viewAllRecords();
    }

 
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        
        if ("newRecord".equals(operation)) {

            String result = addRecord(request);

            if (result.equals("FAIL") || result.equals("INVALID INPUT")) {
                response.sendRedirect("error.html");
            } else {
                response.sendRedirect("success.html");
            }
        }

       
        else if ("viewRecord".equals(operation)) {

            ComplaintBean bean = viewRecord(request);

            if (bean == null) {
                request.setAttribute("message",
                        "No matching records exists! Please try again!");
            } else {
                request.setAttribute("bean", bean);
            }

            RequestDispatcher rd =
                    request.getRequestDispatcher("displayComplaint.jsp");
            rd.forward(request, response);
        }

       
        else if ("viewAllRecords".equals(operation)) {

            List<ComplaintBean> list = viewAllRecords(request);

            if (list.isEmpty()) {
                request.setAttribute("message",
                        "No records available!");
            } else {
                request.setAttribute("list", list);
            }

            RequestDispatcher rd =
                    request.getRequestDispatcher("displayAllComplaints.jsp");
            rd.forward(request, response);
        }
    }
}
