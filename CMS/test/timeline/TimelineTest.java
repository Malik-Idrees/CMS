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
public class TimelineTest {

    public TimelineTest() {
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
//the method verifies that the friend are added properly from all registered student list

    @Test
    public void testListFriends() throws SQLException {
        Connection con = MyConnection.getConnection();

        try {
            System.out.println("Friends List ... It Ensures Friend's List is correctly displayed");
            Statement statement = con.createStatement();
            con.setAutoCommit(false);
            statement.execute("INSERT INTO `student` (`id`, `fname`) VALUES (1000099, 'Jamal')");
            statement.execute("INSERT INTO `student` (`id`, `fname`) VALUES (1000001, 'Alice')");
            Integer userId = 1000099;
            Integer user2Id = 1000001;
            String query = "insert into friends(studentid1, studentid2) values('" + userId + "', '" + user2Id + "')";
            statement.execute(query);
            String query2 = "select id, fname from student where id in (select studentid2 from friends where studentid1=" + userId + ")";

            ResultSet resultSet = statement.executeQuery(query2);

            while (resultSet.next()) //go through each row that your query returns
            {

                String id = resultSet.getString("id");
                String fname = resultSet.getString("fname");
                assertEquals("1000001", id);
                assertEquals("Alice", fname);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            con.close();
        }
    }

    //
    @Test
    public void testGetMajorMinor() throws Exception {
        System.out.println("Method:getMajorMinor ... returns major and minor of current student");

        String userId = "1000099";
        Timeline instance = new Timeline(userId);

        Connection con = MyConnection.getConnection();

        try {
            Statement statement = con.createStatement();
            con.setAutoCommit(false);
            statement.execute("INSERT INTO `student` (`id`, `fname`, `lname`, `age`, `semester`, `password`, `major`, `minor`, `email`) VALUES\n"
                    + "('" + userId + "', 'Jamal', 'Irma', 22, 2, 1595477, 'CS', 'EE', 'quis.pede@DuisgravidaPraesent.edu')");

            String[] expected = {"CS", "EE"};
            String majorMinorQuery = "select major,minor from student where id=" + userId;
            String[] result = new String[2];
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery(majorMinorQuery);
            if (rs1.next()) {
                result[0] = rs1.getString("major");
                result[1] = rs1.getString("minor");
            } else {
                fail("Failed to connect to database");
            }
            assertArrayEquals(expected, result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            con.close();
        }
    }

}
