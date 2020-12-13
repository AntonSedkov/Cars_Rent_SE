package tpr.sedkov.rentcars.dao.impl;

import tpr.sedkov.rentcars.dao.DaoInterface;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Customers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoCustomers implements DaoInterface<Customers> {
    public static final String INSERT_CUSTOMER = "INSERT INTO customers (id_customer, id_user_fk, id_order_fk, surname, name, passport_series, passport_number, customer_status) VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATE_CUSTOMER = "UPDATE customers SET id_user_fk = (?), id_order_fk = (?),  surname = (?), name = (?), passport_series = (?), passport_number = (?), customer_status = (?) WHERE id_customer = (?)";
    public static final String DELETE_CUSTOMER = "UPDATE customers SET customer_status = 2 WHERE id_customer = (?)";

    private final ConnectCreator connectCreator;

    public DaoCustomers(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
    }

    @Override
    public void insert(Customers ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(INSERT_CUSTOMER);
            ps.setInt(1, ob.getIdCustomer());
            ps.setInt(2, ob.getIdUserFk());
            ps.setInt(3, ob.getIdOrderFk());
            ps.setString(4, ob.getSurname());
            ps.setString(5, ob.getName());
            ps.setString(6, ob.getPassportSeries());
            ps.setInt(7, ob.getPassportNumber());
            ps.setInt(8, ob.getCustomerStatus());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления в таблицу Customers");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void update(Customers ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(UPDATE_CUSTOMER);
            ps.setInt(1, ob.getIdUserFk());
            ps.setInt(2, ob.getIdOrderFk());
            ps.setString(3, ob.getSurname());
            ps.setString(4, ob.getName());
            ps.setString(5, ob.getPassportSeries());
            ps.setInt(6, ob.getPassportNumber());
            ps.setInt(7, ob.getCustomerStatus());
            ps.setInt(8, ob.getIdCustomer());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка редактирования в таблице Customers");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void delete(Customers ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(DELETE_CUSTOMER);
            ps.setInt(1, ob.getIdCustomer());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления в таблице Customers");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

}