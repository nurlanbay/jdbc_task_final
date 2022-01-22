package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url="jdbc:postgresql://localhost:5432/postgres";
    private static final String user="postgres";
    private static final String password="admin";
    public Connection connection(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
