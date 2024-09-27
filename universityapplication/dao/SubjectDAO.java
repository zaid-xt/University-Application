package com.mycompany.universityapplication.dao;

import com.mycompany.universityapplication.connection.DBConnection;
import com.mycompany.universityapplication.domain.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SubjectDAO {

    private Connection con;
    private Statement stmt;

    public SubjectDAO() {
        try {
            this.con = DBConnection.createConnection();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void save(Subject subject) {
        String sql = String.format(
                "INSERT INTO Subject (Subject_Code, Subject_Name) VALUES ('%s', '%s')",
                subject.getSubjectCode(), subject.getSubjectName());

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Subject saved successfully.");
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + sqlException.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String code = rs.getString("Subject_Code");
                String name = rs.getString("Subject_Name");
                subjects.add(new Subject(code, name));
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + sqlException.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }

        return subjects;
    }
}
