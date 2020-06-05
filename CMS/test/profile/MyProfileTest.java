/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile;

import cms.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class MyProfileTest {

    String testid = "100000";

    public MyProfileTest() {
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

    /**
     * Test of populateFriendList method, of class MyProfile.
     */
    // It verifies that the query returns the corrrect friends of a student
    @Test
    public void testPopulateFriendList() throws Exception {
        Connection con = MyConnection.getConnection();

        try {
            System.out.println("populateFriendList ... Test confirms it gives correct value of student friends");

            Statement statement = con.createStatement();
            con.setAutoCommit(false);
            statement.execute("INSERT INTO `student` (`id`, `fname`) VALUES (100000, 'Jamal')");
            statement.execute("INSERT INTO `student` (`id`, `fname`) VALUES (1000001, 'damal')");
            statement.execute("INSERT INTO friends (`studentid1`, `studentid2`) VALUES (100000, 1000001)");
            String query = "select id, fname from student where id in (select studentid2 from friends where studentid1=" + testid + ")";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) //go through each row that your query returns
            {

                String id = resultSet.getString("id");
                String fname = resultSet.getString("fname");
                assertEquals("1000001", id);
                assertEquals("damal", fname);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.rollback();
            con.close();
        }

    }

    /**
     * Test of myCourses method, of class MyProfile.
     */
    @Test
    //it checks if course info is correctly working
    public void testMyCourses() throws Exception {
        System.out.println("myCourses :");
        Connection con = MyConnection.getConnection();

        try {
            System.out.println("populateCourseList ... Test  confirms it gives correct values of courses for current student");

            Statement statement = con.createStatement();
            con.setAutoCommit(false);
            statement.execute("INSERT INTO `student` (`id`, `fname`) VALUES (1000003, 'george')");
            statement.execute("INSERT INTO `course` (`id`, `location`, `start_at`, `end_at`, `semester`) VALUES ('CS-00001', 'Cambridge Room 9', '08:00:00', '13:00:00', 8)");
            statement.execute("INSERT INTO `studenthastaken` (`studentid`, `courseid`) VALUES (1000003, 'CS-00001')");

            String query = "select * from course where id in (select courseid from studenthastaken where studentid= '1000003' )";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) //go through each row that your query returns
            {
                assertEquals("CS-00001", resultSet.getString("id"));
                assertEquals("Cambridge Room 9", resultSet.getString("location"));
                assertEquals("08:00:00", resultSet.getString("start_at"));
                assertEquals("13:00:00", resultSet.getString("end_at"));
                assertEquals("8", resultSet.getString("semester"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.rollback();
            con.close();
        }
    }

    @Test
    public void DropCourseButton() throws Exception {
        Connection con = MyConnection.getConnection();

        try {
            System.out.println("Drop course function ... Test confirms the course is dropped");

            Statement statement = con.createStatement();
            con.setAutoCommit(false);
            statement.execute("INSERT INTO `student` (`id`, `fname`) VALUES (1000007, 'george')");
            statement.execute("INSERT INTO `course` (`id`, `location`, `start_at`, `end_at`, `semester`) VALUES ('CS-00001', 'Cambridge Room 9', '08:00:00', '13:00:00', 8)");
            statement.execute("INSERT INTO `studenthastaken` (`studentid`, `courseid`) VALUES (1000007, 'CS-00002')");
            //After insertion we will run our drop course query to see if it removes the course from our courses
            String userId = "1000007";
            String courseId = "CS-00002";
            String query = "delete from studenthastaken where studentid=" + userId + " and courseid='" + courseId + "'";
            statement.execute(query);
            String query2 = "select * from course where id in (select courseid from studenthastaken where studentid=" + userId + ")";

            ResultSet resultSet = statement.executeQuery(query2);
            //assertTrue(resultSet.next());
            if (resultSet.next()) {
                fail("Drop course failed");
            } else {
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.rollback();
            con.close();
        }

    }
}
