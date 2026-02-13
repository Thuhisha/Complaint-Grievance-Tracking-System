package com.wipro.complaint.service;

import java.util.Date;
import java.util.List;

import com.wipro.complaint.bean.ComplaintBean;
import com.wipro.complaint.dao.ComplaintDAO;
import com.wipro.complaint.util.InvalidInputException;

public class Administrator {

    ComplaintDAO dao = new ComplaintDAO();

   
    public String addRecord(ComplaintBean bean) {

        try {
          
            if (bean == null ||
                bean.getComplainant() == null ||
                bean.getTitle() == null ||
                bean.getDepartment() == null ||
                bean.getComplaintDate() == null) {

                throw new InvalidInputException();
            }

            
            if (bean.getComplainant().length() < 2) {
                return "INVALID COMPLAINANT NAME";
            }

           
            if (bean.getTitle().length() < 2) {
                return "INVALID TITLE";
            }

            
            if (bean.getDepartment().length() < 2) {
                return "INVALID DEPARTMENT";
            }

           
            if (dao.recordExists(bean.getTitle(), bean.getComplaintDate())) {
                return "ALREADY EXISTS";
            }

            
            String complaintId = dao.generateComplaintID(
                    bean.getTitle(), bean.getComplaintDate());

           
            bean.setComplaintId(complaintId);

           
            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

   
    public ComplaintBean viewRecord(String title, Date complaintDate) {

        return dao.fetchRecord(title, complaintDate);
    }

   
    public List<ComplaintBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }
}
