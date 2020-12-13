package tpr.sedkov.rentcars.dao.impl;

import tpr.sedkov.rentcars.dao.DaoInterface;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Orders;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoOrders implements DaoInterface<Orders> {
    public static final String INSERT_ORDER = "INSERT INTO orders (id_order, id_user_fk, id_car_fk, days, sum, order_status, admin_notes) VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE_ORDER = "UPDATE orders SET id_user_fk = (?), id_car_fk = (?), days = (?), sum = (?), order_status = (?), admin_notes = (?) WHERE id_order = (?)";
    public static final String DELETE_ORDER = "UPDATE orders SET order_status = 5 WHERE id_order = (?)";

    private final ConnectCreator connectCreator;

    public DaoOrders(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
    }

    @Override
    public void insert(Orders ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(INSERT_ORDER);
            ps.setInt(1, ob.getIdOrder());
            ps.setInt(2, ob.getIdUserFk());
            ps.setInt(3, ob.getIdCarFk());
            ps.setInt(4, ob.getDays());
            ps.setInt(5, ob.getSum());
            ps.setInt(6, ob.getOrderStatus());
            ps.setString(7, ob.getAdminNotes());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления в таблицу Orders");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void update(Orders ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(UPDATE_ORDER);
            ps.setInt(1, ob.getIdUserFk());
            ps.setInt(2, ob.getIdCarFk());
            ps.setInt(3, ob.getDays());
            ps.setInt(4, ob.getSum());
            ps.setInt(5, ob.getOrderStatus());
            ps.setString(6, ob.getAdminNotes());
            ps.setInt(7, ob.getIdOrder());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка редактирования в таблице Orders");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void delete(Orders ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(DELETE_ORDER);
            ps.setInt(1, ob.getIdOrder());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления в таблице Orders");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

}