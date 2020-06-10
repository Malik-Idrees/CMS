/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountInfo;

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
public class AccountInfoTest {

    Connection con = MyConnection.getConnection();
    String testid = "100009";

    public AccountInfoTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        try {

            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            String query = "INSERT INTO `student` (`id`, `fname`, `lname`, `age`, `semester`, `password`, `major`, `minor`, `email`) VALUES\n"
                    + "('" + testid + "', 'Jamal', 'Irma', '22', '2', '1595477', 'CS', 'EE', 'quis.pede@DuisgravidaPraesent.edu'" + ")";
            statement.execute(query);

            statement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
        con.close();
    }

    //The Test confirms that the user info is updated properly
    @Test
    public void testUpdateAccountInfo() throws SQLException {
        try {
            System.out.println(" ...Update Account Info : The Test confirms that the values are updated properly");
            Statement statement = con.createStatement();
            String sql = "update student set fname= 'test', lname = 'passed', age = '99', semester = '2', Email= 'testpassed@gmail.com', major='CS', minor='EE' WHERE id= '" + testid + "'";
            statement.execute(sql);
            String sql1 = "select * from student where id='" + testid + "'";
            ResultSet resultSet = statement.executeQuery(sql1);
            resultSet.next();
            assertEquals("test", resultSet.getString("fname"));
            assertEquals("passed", resultSet.getString("lname"));
            assertEquals("99", resultSet.getString("age"));
            assertEquals("2", resultSet.getString("semester"));
            assertEquals("testpassed@gmail.com", resultSet.getString("Email"));
        } finally {
            con.rollback();
        }
    }

}
