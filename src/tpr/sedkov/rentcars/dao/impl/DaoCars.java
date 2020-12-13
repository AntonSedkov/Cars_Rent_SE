package tpr.sedkov.rentcars.dao.impl;

import tpr.sedkov.rentcars.dao.DaoInterface;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Cars;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoCars implements DaoInterface<Cars> {
    public static final String INSERT_CAR = "INSERT INTO cars (id_car, model, car_year, mileage, price_for_day, car_status) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_CAR = "UPDATE cars SET model = (?), car_year = (?), mileage = (?), price_for_day = (?), car_status = (?) WHERE id_car = (?)";
    public static final String DELETE_CAR = "UPDATE cars SET car_status = 2 WHERE id_car = (?)";

    private final ConnectCreator connectCreator;

    public DaoCars(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
    }

    @Override
    public void insert(Cars ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(INSERT_CAR);
            ps.setInt(1, ob.getIdCar());
            ps.setString(2, ob.getModel());
            ps.setInt(3, ob.getCarYear());
            ps.setInt(4, ob.getMileage());
            ps.setInt(5, ob.getPriceForDay());
            ps.setInt(6, ob.getCarStatus());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления в таблицу Cars");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void update(Cars ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(UPDATE_CAR);
            ps.setString(1, ob.getModel());
            ps.setInt(2, ob.getCarYear());
            ps.setInt(3, ob.getMileage());
            ps.setInt(4, ob.getPriceForDay());
            ps.setInt(5, ob.getCarStatus());
            ps.setInt(6, ob.getIdCar());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка редактирования в таблице Cars");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

    @Override
    public void delete(Cars ob) {
        PreparedStatement ps = null;
        try {
            ps = connectCreator.getConnection().prepareStatement(DELETE_CAR);
            ps.setInt(1, ob.getIdCar());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления в таблице Cars");
        } finally {
            connectCreator.closeStatement(ps);
        }
    }

}