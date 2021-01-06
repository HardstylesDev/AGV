package agv.utils;


import agv.status.Status;
import agv.status.StatusManager;

import java.sql.*;

public class Database {
    private final String host = "185.104.29.78"; // The IP-address of the database host.
    private final String database = "tbijen_examenorders"; // The name of the database.
    private final String user = "tbijen_examenorders"; // The name of the database user.
    private final String pass = "admin"; // The password of the database user.


    private Connection con;

    /**
     * Connect met de database
     */
    public void connect() {
        try {
            StatusManager.setStatus(Status.StatusType.GEEN_DASHBOARD_CONNECTIE, false);

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s:3306/%s?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", host, database),
                    user,
                    pass
            );
            //System.out.println(con.get);
        } catch (Exception e) {
            StatusManager.setStatus(Status.StatusType.GEEN_DASHBOARD_CONNECTIE, true);
            System.out.println("error caught here, fix");
            e.printStackTrace();
            //System.exit(0);
        }
    }

    public ResultSet get(String sql) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("error caught here, fix 2");
            StatusManager.setStatus(Status.StatusType.GEEN_DASHBOARD_CONNECTIE, true);

            System.out.println(e.getMessage());
            // System.exit(0);
        }
        return null;
    }

    public int query(String sql) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("error caught here, fix 3");
            StatusManager.setStatus(Status.StatusType.GEEN_DASHBOARD_CONNECTIE, true);

            // System.exit(0);
        }
        return 0;
    }

    public void disconnect() {
        try {
            con.close();
            con = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            StatusManager.setStatus(Status.StatusType.GEEN_DASHBOARD_CONNECTIE, true);

            System.exit(0);
        }
    }
}
