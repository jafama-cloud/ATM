import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The `DatabaseConnector` class provides methods to establish and close connections to a MySQL database.
 */
public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/atm_database";       // The URL of the MySQL database.
    private static final String USER = "root";      // The username for database authentication.
    private static final String PASSWORD = "";      // The password for database authentication.

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A `Connection` object representing the connection to the database.
     * @throws SQLException If a SQL exception occurs during the connection process.
     */
    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver.
            return DriverManager.getConnection(URL, USER, PASSWORD); // Create and return a connection.
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e); // Throw an exception if the driver is not found.
        }
    }

    /**
     * Closes the specified database connection.
     *
     * @param connection The `Connection` object to be closed.
     * @throws SQLException If a SQL exception occurs during the closing process.
     */
    public static void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close(); // Close the connection if it is not already closed.
        }
    }
}
