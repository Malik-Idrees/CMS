/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import cms.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Timeline extends javax.swing.JInternalFrame {

    String[] courses;
    String userid;

    public Timeline() {

        initComponents();
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        System.out.print("Here");
    }

    public Timeline(String id) throws SQLException {
        initComponents();
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        this.userid = id;
        fillFriendsTable();
        fillCourseTable();
        fillStudentTable();

    }

    //returns a Array/list of friends(names) along with their course name
    public ArrayList<GetFriendsCourse> ListFriends() {
        ArrayList<GetFriendsCourse> usersList = new ArrayList<GetFriendsCourse>();

        try {
            Connection con = MyConnection.getConnection();

            Statement st;
            ResultSet rs;
            st = con.createStatement();
            String searchQuery = "select fname,lname,studenthastaken.courseid from student,studenthastaken where student.id in (select studentid2 from friends where studentid1=" + userid + ") AND student.id=studenthastaken.studentid;";
            rs = st.executeQuery(searchQuery);
            GetFriendsCourse friend;

            while (rs.next()) {

                friend = new GetFriendsCourse(
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("courseid")
                );
                usersList.add(friend);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return usersList;
    }

    // function to display data in friends course table in GUI
    //it fills the table with friends name and their courses
    public void fillFriendsTable() {
        ArrayList<GetFriendsCourse> users = ListFriends();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Fname", "Lname", "Course"});
        Object[] row = new Object[3];

        for (int i = 0; i < users.size(); i++) {
            row[0] = users.get(i).getFname();
            row[1] = users.get(i).getLname();
            row[2] = users.get(i).getCourse();

            model.addRow(row);
        }
        CourseFriendTable.setModel(model);

    }

    //It returns a list/Array of all the courses that student are allowed to take in a particular semester
    //if i am in 1st semester then it will have all courses of 1st semester that university offers
    public ArrayList<GetAllCourses> allCourses() throws SQLException {
        ArrayList<GetAllCourses> courseList = new ArrayList<>();
        Connection con = null;
        try {
            con = MyConnection.getConnection();

            Statement st;
            ResultSet rs;
            st = con.createStatement();
            String searchQuery = "select * from course where semester in (select semester from student where id=" + userid + ")";
            rs = st.executeQuery(searchQuery);
            GetAllCourses courses;

            while (rs.next()) {
                courses = new GetAllCourses(
                        rs.getString("id"),
                        rs.getString("location"),
                        rs.getTime("start_at"),
                        rs.getTime("end_at"),
                        rs.getInt("semester")
                );
                courseList.add(courses);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }

        return courseList;
    }

    //It returns a list/Array of specific courses based on search query
    public ArrayList<GetAllCourses> allCourses(String searchedWord) throws SQLException {
        ArrayList<GetAllCourses> courseList = new ArrayList<>();
        Connection con = null;
        try {
            con = MyConnection.getConnection();

            Statement st;
            ResultSet rs;
            st = con.createStatement();
            String searchQuery = "select * from course where semester in (select semester from student where id=" + userid + ") and id like '%" + searchedWord + "%'";
            rs = st.executeQuery(searchQuery);
            GetAllCourses courses;

            while (rs.next()) {
                courses = new GetAllCourses(
                        rs.getString("id"),
                        rs.getString("location"),
                        rs.getTime("start_at"),
                        rs.getTime("end_at"),
                        rs.getInt("semester")
                );
                courseList.add(courses);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }

        return courseList;
    }

    // function to fill the all courses table
    public void fillCourseTable() throws SQLException {
        ArrayList<GetAllCourses> courses = allCourses();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Location", "Start Time", "End Time", "Semester"});
        Object[] row = new Object[5];

        for (int i = 0; i < courses.size(); i++) {
            row[0] = courses.get(i).getId();
            row[1] = courses.get(i).getLocation();
            row[2] = courses.get(i).getStart();
            row[3] = courses.get(i).getEnd();
            row[4] = courses.get(i).getSemester();

            model.addRow(row);
        }
        allCoursesTable.setModel(model);

    }

//It returns an Array/list of all students registered in our system
    public ArrayList<GetAllStudents> allStudents() throws SQLException {
        ArrayList<GetAllStudents> studentList = new ArrayList<>();
        Connection con = null;
        try {
            con = MyConnection.getConnection();

            Statement st;
            ResultSet rs;
            st = con.createStatement();
            String searchQuery = "select * from student";
            rs = st.executeQuery(searchQuery);
            GetAllStudents student;

            while (rs.next()) {
                student = new GetAllStudents(
                        rs.getString("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("semester"),
                        rs.getString("Email")
                );
                studentList.add(student);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }

        return studentList;
    }

    // function to display data all registered students table
    public void fillStudentTable() throws SQLException {
        ArrayList<GetAllStudents> students = allStudents();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "First Name", "Last Name", "Semester", "Email"});
        Object[] row = new Object[5];

        for (int i = 0; i < students.size(); i++) {
            row[0] = students.get(i).getId();
            row[1] = students.get(i).getFname();
            row[2] = students.get(i).getLname();
            row[3] = students.get(i).getSemester();
            row[4] = students.get(i).getEmail();

            model.addRow(row);
        }
        allStudentsTable.setModel(model);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        allCoursesTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CourseFriendTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        allStudentsTable = new javax.swing.JTable();
        addFriendButton = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Join Course");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        allCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(allCoursesTable);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Available Courses");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Friends' Courses");

        jScrollPane1.setName(""); // NOI18N

        CourseFriendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Course", "Friend Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CourseFriendTable);
        if (CourseFriendTable.getColumnModel().getColumnCount() > 0) {
            CourseFriendTable.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Registered Students");

        allStudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(allStudentsTable);

        addFriendButton.setBackground(new java.awt.Color(102, 102, 255));
        addFriendButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        addFriendButton.setForeground(new java.awt.Color(255, 255, 255));
        addFriendButton.setText("Add Friend");
        addFriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFriendButtonActionPerformed(evt);
            }
        });

        searchBar.setToolTipText("Course ID");

        searchButton.setBackground(new java.awt.Color(102, 102, 255));
        searchButton.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(105, 105, 105))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addFriendButton)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(searchButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addFriendButton)
                .addGap(250, 250, 250))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//It adds the selected course into student course list 
//i.e  that course will be added to your course list

    private void addCourse(String courseName) {
        Connection con = MyConnection.getConnection();

        String query = "insert into studenthastaken(studentid, courseid) values(" + userid + ", '" + courseName + "')";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Congratulation Course Added !!");
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You already have this course");

        }
    }
//It returns the major and minor of current student signed in

    String[] getMajorMinor(Integer userid) throws SQLException {

        Connection con = MyConnection.getConnection();
        String majorMinorQuery = "select major,minor from student where id=" + userid;
        String[] arr = new String[2];
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery(majorMinorQuery);
        while (rs1.next()) {
            arr[0] = rs1.getString("major");
            arr[1] = rs1.getString("minor");

        }
        return arr;

    }
//This Method adds the course to your list course list only if its allowed in your major and minor
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String major = null;
        String minor = null;

        try {
            int inum = Integer.parseInt(this.userid);
            major = getMajorMinor(inum)[0];
            minor = getMajorMinor(inum)[1];

            Connection con = MyConnection.getConnection();
            DefaultTableModel model = (DefaultTableModel) allCoursesTable.getModel();

            int row = allCoursesTable.getSelectedRow();
            String courseId = allCoursesTable.getModel().getValueAt(row, 0).toString();

            boolean matched = false;
            try {

                //getting all the courses of our major and minor fields
                String query = "select DISTINCT courses from coursetype where coursetype.dept = '" + major + "' OR '" + minor + "'";

                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);

                rs.last();                  // Place the record pointer onto the last row
                int counter = rs.getRow(); // Get the row number (there's your count)
                rs.first();                 // Place the record pointer onto the first row for the while loop
                courses = new String[counter]; // Declare and Initialize your array
                counter = 0;
                courses[counter] = rs.getString("courses");

                while (rs.next()) {
                    counter++;
                    courses[counter] = rs.getString("courses");

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                JOptionPane.showMessageDialog(null, "You are not allowed to join this course");
            };

            for (String element : courses) {

//checking if the course we want to add is in our major or minor subjects if it is It is added
                if (element.equals(courseId)) {
                    matched = true;
                    System.out.print(matched);
                    String a = element;
                    addCourse(a);
                    break;
                } else {
                    matched = false;

                }

            }
            if (matched == false) {
                JOptionPane.showMessageDialog(null, "Course not present in your  major or minor");
            }

        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } catch (SQLException ex) {
            Logger.getLogger(Timeline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //This method adds the selected student from all students list to your friends list
    private void addFriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFriendButtonActionPerformed
        // TODO add your handling code here:
        Connection con = MyConnection.getConnection();

        try {
            int row = allStudentsTable.getSelectedRow();
            String user2Id = allStudentsTable.getModel().getValueAt(row, 0).toString();

            if (!user2Id.equals(userid)) {

                String query = "insert into friends(studentid1, studentid2) values(" + userid + ", '" + user2Id + "')";
                PreparedStatement pst;
                try {
                    pst = con.prepareStatement(query);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Congratulation !!");
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Can not add yourself as friend.");
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        }
    }//GEN-LAST:event_addFriendButtonActionPerformed

    //It refills the courses table with desired search words
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        String searchedWord = searchBar.getText();
//        DefaultTableModel model = (DefaultTableModel) allStudentsTable.getModel();
//        model.setRowCount(0);
        ArrayList<GetAllCourses> courses = null;
        try {
            courses = allCourses(searchedWord);
        } catch (SQLException ex) {
            Logger.getLogger(Timeline.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Location", "Start Time", "End Time", "Semester"});
        Object[] row = new Object[5];

        for (int i = 0; i < courses.size(); i++) {
            row[0] = courses.get(i).getId();
            row[1] = courses.get(i).getLocation();
            row[2] = courses.get(i).getStart();
            row[3] = courses.get(i).getEnd();
            row[4] = courses.get(i).getSemester();

            model.addRow(row);
        }
        allCoursesTable.setModel(model);

    }//GEN-LAST:event_searchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CourseFriendTable;
    private javax.swing.JButton addFriendButton;
    private javax.swing.JTable allCoursesTable;
    private javax.swing.JTable allStudentsTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
