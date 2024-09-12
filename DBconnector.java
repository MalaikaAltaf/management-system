import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
    public static Connection getCon() {
        try {
            String url = "jdbc:mysql://localhost:3306/ CarShowroom3";
            String user = "root"; // Replace with your username
            String password = "root1122"; // Replace with your password
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
