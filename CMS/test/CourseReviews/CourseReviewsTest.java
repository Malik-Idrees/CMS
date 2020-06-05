/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CourseReviews;

import cms.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class CourseReviewsTest {

    Connection con = MyConnection.getConnection();

    String testId = "11223377";
    String courseId = "CS-0000";
    String comment = "postCommentPassed";

    public CourseReviewsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // It makes sure Both adding and display comments works fine 
    @Test
    public void testPostComment() throws Exception {
        try {
            try {
                System.out.println("Method : Viewing and post comments ");
                con.setAutoCommit(false);
                Statement statement = con.createStatement();
                String query = "INSERT INTO `student` (`id`, `fname`, `lname`, `age`, `semester`, `password`, `major`, `minor`, `email`) VALUES\n"
                        + "('" + testId + "', 'Jamal', 'Irma', '22', '2', '1595477', 'CS', 'EE', 'quis.pede@DuisgravidaPraesent.edu'" + ")";
                statement.execute(query);
                String query2 = "INSERT INTO `course` (`id` , `location`, `start_at`, `end_at`, `semester`) VALUES ('" + courseId + "', 'Cambridge Room 9', '08:00:00', '13:00:00', 8)";
                statement.execute(query2);

                String sql = "insert into comments(studentid, courseid, comment) values (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(testId));
                pst.setString(2, courseId);
                pst.setString(3, comment);
                pst.execute();
                statement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                Statement statement = con.createStatement();
                //String testId = "11223377";
                //String courseId = "CS-0000";

                String searchQuery = "select fname,comment, from student,comments where student.id='" + testId + "' AND comments.courseid='" + courseId + "'";

                ResultSet resultSet = statement.executeQuery(searchQuery);
                if (resultSet.next()) {
                    assertEquals("Jamal", resultSet.getString("fname"));
                    assertEquals(comment, resultSet.getString("comment"));
                } else {
                    fail("Failed To View Comments");
                }
            } catch (Exception e) {

            }
        } finally {
            con.rollback();
        }
    }
}
