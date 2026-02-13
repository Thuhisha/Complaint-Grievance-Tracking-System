package com.wipro.complaint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.complaint.bean.ComplaintBean;
import com.wipro.complaint.util.DBUtil;

public class ComplaintDAO {

   
    public String createRecord(ComplaintBean bean) {

        String sql = "INSERT INTO COMPLAINT_TB VALUES (?,?,?,?,?,?,?)";
        Connection con = null;

        try {
            con = DBUtil.getDBConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bean.getComplaintId());
            ps.setString(2, bean.getComplainant());
            ps.setString(3, bean.getTitle());
            ps.setString(4, bean.getDepartment());
            ps.setDate(5, new java.sql.Date(bean.getComplaintDate().getTime()));
            ps.setString(6, bean.getStatus());
            ps.setString(7, bean.getRemarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return bean.getComplaintId();
            }

        } catch (Exception e) {
            return "FAIL";
        }

        return "FAIL";
    }

  
    public ComplaintBean fetchRecord(String title, Date complaintDate) {

        ComplaintBean bean = null;
        String sql = "SELECT * FROM COMPLAINT_TB WHERE title=? AND complaint_date=?";

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setDate(2, new java.sql.Date(complaintDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new ComplaintBean();
                bean.setComplaintId(rs.getString(1));
                bean.setComplainant(rs.getString(2));
                bean.setTitle(rs.getString(3));
                bean.setDepartment(rs.getString(4));
                bean.setComplaintDate(rs.getDate(5));
                bean.setStatus(rs.getString(6));
                bean.setRemarks(rs.getString(7));
            }

        } catch (Exception e) {
            return null;
        }

        return bean;
    }

   
    public String generateComplaintID(String title, Date complaintDate) {

        DateFormat f = new SimpleDateFormat("yyyyMMdd");
        String temp = f.format(complaintDate);

        String prefix = title.substring(0, 2).toUpperCase();

        int sequence = 1;

        String sql = "SELECT COUNT(*) FROM COMPLAINT_TB WHERE complaint_date=?";

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(complaintDate.getTime()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sequence = rs.getInt(1) + 1;
            }

        } catch (Exception e) {
            sequence = 1;
        }

        return temp + prefix + sequence;
    }

 
    public boolean recordExists(String title, Date complaintDate) {

        String sql = "SELECT 1 FROM COMPLAINT_TB WHERE title=? AND complaint_date=?";

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setDate(2, new java.sql.Date(complaintDate.getTime()));

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }

    
    public List<ComplaintBean> fetchAllRecords() {

        List<ComplaintBean> list = new ArrayList<>();
        String sql = "SELECT * FROM COMPLAINT_TB";

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ComplaintBean bean = new ComplaintBean();
                bean.setComplaintId(rs.getString(1));
                bean.setComplainant(rs.getString(2));
                bean.setTitle(rs.getString(3));
                bean.setDepartment(rs.getString(4));
                bean.setComplaintDate(rs.getDate(5));
                bean.setStatus(rs.getString(6));
                bean.setRemarks(rs.getString(7));

                list.add(bean);
            }

        } catch (SQLException e) {
            return list; // empty list
        }

        return list;
    }
}
