package com.mycompany.universityapplication.gui;

import com.mycompany.universityapplication.dao.SubjectDAO;
import com.mycompany.universityapplication.domain.Subject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SubjectGui extends JFrame implements ActionListener {

    JPanel panelLeft, panelRight, panelBottom;
    JButton saveButton, cancelButton, fillButton, readButton;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
    JTextField codeField, nameField;
    JLabel codeLabel, nameLabel;
    SubjectDAO subjectDAO;

    public SubjectGui() {
        subjectDAO = new SubjectDAO();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setTitle("Add a subject");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Subject Code");
        tableModel.addColumn("Subject Name");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        readButton = new JButton("Read");
        fillButton = new JButton("Fill Combo");
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        codeField = new JTextField(10);
        nameField = new JTextField(10);
        codeLabel = new JLabel("Subject Code");
        nameLabel = new JLabel("Subject Name");

        panelRight = new JPanel(new GridLayout(1, 1));
        panelRight.add(scrollPane);

        panelLeft = new JPanel(new GridLayout(2, 1));
        panelLeft.add(codeLabel);
        panelLeft.add(codeField);
        panelLeft.add(nameLabel);
        panelLeft.add(nameField);

        panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBottom.add(saveButton);
        panelBottom.add(cancelButton);
        panelBottom.add(readButton);
        panelBottom.add(fillButton);

        this.setLayout(new BorderLayout());
        this.add(panelLeft, BorderLayout.WEST);
        this.add(panelRight, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);

        saveButton.addActionListener(this);
        readButton.addActionListener(this);
        cancelButton.addActionListener(this);
        fillButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveSubject();
        } else if (e.getSource() == readButton) {
            readSubjects();
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == fillButton) {
            fillComboBox();
        }
    }

    private void saveSubject() {
        String code = codeField.getText();
        String name = nameField.getText();

        if (code.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Subject subject = new Subject(code, name);
        try {
            subjectDAO.save(subject);
            JOptionPane.showMessageDialog(this, "Subject saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            readSubjects();
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error saving subject: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readSubjects() {
        tableModel.setRowCount(0);
        for (Subject subject : subjectDAO.getAll()) {
            tableModel.addRow(new Object[]{subject.getSubjectCode(), subject.getSubjectName()});
        }
    }

    private void clearFields() {
        codeField.setText("");
        nameField.setText("");
    }

    private void fillComboBox() {
        JOptionPane.showMessageDialog(this, "Fill Combo button clicked", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
