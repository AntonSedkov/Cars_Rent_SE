package tpr.sedkov.rentcars.database;

import java.sql.*;

public class ConnectCreator {
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    private Connection connection;

    public ConnectCreator(String url, String login, String password) {
        try {
            Class.forName(DRIVER_NAME);
            try {
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e) {
                System.out.println("Ошибка подключения к БД");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка загрузки драйверов");
        }
    }

    public void update(String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка в методе Update");
        } finally {
            closeStatement(statement);
        }
    }

    public ResultSet query(String sql) {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка в методе Query");
        }
        return rs;
    }

    public void showTable(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            while (rs.next()) {
                System.out.println();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка в методе, формирующем таблицу");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения с БД");
        }
    }

    public void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии statement БД");
        }
    }

    public void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии ResultSet БД");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}