
package CourseReviews;

import cms.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class CourseReviews extends javax.swing.JInternalFrame {

    /**
     * Creates new form CourseReviews
     *
     * @throws java.sql.SQLException
     */
    String userId;

    public CourseReviews() throws SQLException {
        initComponents();
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }

    public CourseReviews(String userId) throws SQLException {
        initComponents();
        this.userId = userId;
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        populateCourseList();
        
    }

    public void populateCourseList() throws SQLException {

      
        Connection con = MyConnection.getConnection();
        DefaultListModel model = new DefaultListModel(); //create a new list model

        String query = "select id from course where semester in (select semester from student where id="+userId+")";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query); //run your query

        while (resultSet.next()) //go through each row that your query returns
        {
            
            String courseName = resultSet.getString("id");
            
            model.addElement(courseName); //add each item to the model
        }
        courseList.setModel(model);

        resultSet.close();
        statement.close();
    }

    public void fillCourseComments() throws SQLException {
        ArrayList<CourseCommentAndCommentor> comments = CourseComments();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Student", "Comment"});
        Object[] row = new Object[2];

        for (int i = 0; i < comments.size(); i++) {
            row[0] = comments.get(i).getCommentor();
            row[1] = comments.get(i).getComment();
            model.addRow(row);
        }
        courseComments.setModel(model);

    }

    public ArrayList<CourseCommentAndCommentor> CourseComments() throws SQLException {
        ArrayList<CourseCommentAndCommentor> commentList = new ArrayList<>();
        Connection con = null;
        try {
            con = MyConnection.getConnection();

            Statement st;
            ResultSet rs;
            st = con.createStatement();

            String courseId;
            //courseId = courseList.getModel().getElementAt(courseList.getSelectedIndex());
            courseId = (String) courseList.getSelectedValue();
            
            String searchQuery = "select fname,comment from student,comments where student.id=comments.studentid AND comments.courseid='" + courseId + "'";
            rs = st.executeQuery(searchQuery);
            CourseCommentAndCommentor comments;

            while (rs.next()) {
                comments = new CourseCommentAndCommentor(
                        rs.getString("fname"),
                        rs.getString("comment")
                );
                commentList.add(comments);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }

        return commentList;
    }

    public void postComment() throws SQLException {
        
        try {

            Connection con = MyConnection.getConnection();
            // String comment = commentSection.getText();
            //courseId = courseList.getModel().getElementAt(courseList.getSelectedIndex());
            String courseId = (String) courseList.getSelectedValue();
         
            String sql = "insert into comments(studentid, courseid, comment) values (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(userId));
            pst.setString(2, courseId);
            pst.setString(3, commentSection.getText());

            pst.execute();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Only one feedback is allowed per course");
        }

    }
    public ArrayList<GetAttendees> allStudents() throws SQLException {
        ArrayList<GetAttendees> studentList = new ArrayList<>();
        Connection con = null;
        String course = (String) courseList.getSelectedValue();
        try {
            con = MyConnection.getConnection();

            Statement st;
            ResultSet rs;
            st = con.createStatement();
            String searchQuery = "select * from student where id in (select studentid from studenthastaken where courseid='"+course+"')";
         
            rs = st.executeQuery(searchQuery);
            GetAttendees student;

            while (rs.next()) {
                student = new GetAttendees(
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

    // function to display data in jtable
    public void fillStudentTable() throws SQLException {
        ArrayList<GetAttendees> students = allStudents();
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
        attendeesTable.setModel(model);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        courseComments = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseList = new javax.swing.JList<>();
        commentSection = new javax.swing.JTextField();
        postComment = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        attendeesTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Comments");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Courses");

        courseComments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Comment"
            }
        ));
        jScrollPane5.setViewportView(courseComments);

        courseList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        courseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                courseListMousePressed(evt);
            }
        });
        courseList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                courseListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(courseList);

        commentSection.setText("Add Your Comment Here ...");

        postComment.setBackground(new java.awt.Color(102, 102, 255));
        postComment.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        postComment.setForeground(new java.awt.Color(255, 255, 255));
        postComment.setText("Comment");
        postComment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                postCommentMouseClicked(evt);
            }
        });
        postComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postCommentActionPerformed(evt);
            }
        });

        attendeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "Semester", "Email"
            }
        ));
        jScrollPane3.setViewportView(attendeesTable);

        jLabel3.setText("Course Attendees");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(postComment, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                                .addComponent(commentSection, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(223, 223, 223))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(commentSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postComment)
                        .addGap(5, 5, 5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
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

    private void postCommentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_postCommentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_postCommentMouseClicked

    private void postCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postCommentActionPerformed
        try {
            postComment();
            
            DefaultTableModel model = (DefaultTableModel) courseComments.getModel();
            model.setRowCount(0);
            fillCourseComments();
        } catch(ArrayIndexOutOfBoundsException a){
            JOptionPane.showMessageDialog(null,  "No Item Selected");
        }catch (SQLException ex) {
            Logger.getLogger(CourseReviews.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_postCommentActionPerformed

    private void courseListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_courseListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_courseListValueChanged

    private void courseListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseListMousePressed
        try {
            // TODO add your handling code here:
            fillCourseComments();
            fillStudentTable();
        } catch(ArrayIndexOutOfBoundsException a){
            JOptionPane.showMessageDialog(null,  "No Item Selected");
        }catch (SQLException ex) {
            Logger.getLogger(CourseReviews.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_courseListMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendeesTable;
    private javax.swing.JTextField commentSection;
    private javax.swing.JTable courseComments;
    private javax.swing.JList<String> courseList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton postComment;
    // End of variables declaration//GEN-END:variables
}
