package tpr.sedkov.rentcars.dao.impl;

import tpr.sedkov.rentcars.dao.DaoInterface;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Users;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoUsers implements DaoInterface<Users> {
    public static final String INSERT_USER = "INSERT INTO users (id_user, login, password, role, user_status) VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET login = (?), password = (?), role = (?), user_status = (?) WHERE id_user = (?)";
    public static final String DELETE_USER = "UPDATE users SET user_status = 2 WHERE id_user = (?)";

    private final ConnectCreator connectCreator;

    public DaoUsers(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
    }

    @Override
    public void insert(Users ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(INSERT_USER);
            ps.setInt(1, ob.getIdUser());
            ps.setString(2, ob.getLogin());
            ps.setString(3, ob.getPassword());
            ps.setInt(4, ob.getRole());
            ps.setInt(5, ob.getUserStatus());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления в таблицу Users");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void update(Users ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(UPDATE_USER);
            ps.setString(1, ob.getLogin());
            ps.setString(2, ob.getPassword());
            ps.setInt(3, ob.getRole());
            ps.setInt(4, ob.getUserStatus());
            ps.setInt(5, ob.getIdUser());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка редактирования в таблице Users");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void delete(Users ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(DELETE_USER);
            ps.setInt(1, ob.getIdUser());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления в таблице Users");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

}