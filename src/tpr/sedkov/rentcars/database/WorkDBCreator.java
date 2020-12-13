package tpr.sedkov.rentcars.database;

import tpr.sedkov.rentcars.dao.impl.DaoCars;
import tpr.sedkov.rentcars.dao.impl.DaoCustomers;
import tpr.sedkov.rentcars.dao.impl.DaoOrders;
import tpr.sedkov.rentcars.dao.impl.DaoUsers;
import tpr.sedkov.rentcars.entity.Cars;
import tpr.sedkov.rentcars.entity.Customers;
import tpr.sedkov.rentcars.entity.Orders;
import tpr.sedkov.rentcars.entity.Users;

public class WorkDBCreator {

    public static void createDB(String url, String nameDB, String login, String password) {
        ConnectCreator connectCreator = new ConnectCreator(url, login, password);
        connectCreator.update("DROP DATABASE IF EXISTS " + nameDB);
        connectCreator.update("CREATE DATABASE " + nameDB);
        connectCreator.update("USE " + nameDB);
        connectCreator.update("""
                CREATE TABLE users(
                    id_user     INT UNSIGNED AUTO_INCREMENT,
                    login       VARCHAR(255) NOT NULL,
                    password    VARCHAR(255) NOT NULL,
                    role        ENUM ('1','2'),
                    user_status ENUM ('1','2'),
                    PRIMARY KEY (id_user))
                """);

        DaoUsers du = new DaoUsers(connectCreator);
        du.insert(new Users(1, "user", "user", 1, 1));
        du.insert(new Users(2, "admin", "admin", 2, 1));
        du.insert(new Users(3, "one", "one", 2, 2));
        du.update(new Users(3, "one", "one", 1, 1));
        du.insert(new Users(4, "two", "two", 2, 1));
        du.delete(new Users(4));
        du.insert(new Users(5, "cat", "cat", 1, 1));
        du.delete(new Users(5));

        connectCreator.update("""
                CREATE TABLE cars
                (
                    id_car        INT UNSIGNED AUTO_INCREMENT,
                    model         VARCHAR(255)      NOT NULL,
                    car_year      SMALLINT UNSIGNED NOT NULL,
                    mileage       INT UNSIGNED      NOT NULL,
                    price_for_day INT UNSIGNED      NOT NULL,
                    car_status    ENUM ('1','2')    NOT NULL,
                    PRIMARY KEY (id_car)
                )
                """);

        DaoCars da = new DaoCars(connectCreator);
        da.insert(new Cars(1, "model", 2010, 1000000, 50, 1));
        da.insert(new Cars(2, "type", 2000, 2222222, 100, 1));
        da.insert(new Cars(3, "byke", 2014, 335465, 200, 2));
        da.update(new Cars(3, "bykebig", 3000, 465454, 400, 1));
        da.insert(new Cars(4, "flow", 1990, 23532222, 600, 1));
        da.delete(new Cars(4));
        da.insert(new Cars(5, "jet", 1880, 9999000, 350, 1));
        da.delete(new Cars(5));
        da.update(new Cars(5, "jetbig", 3000, 465454, 400, 1));

        connectCreator.update("""
                CREATE TABLE orders
                (
                    id_order     INT UNSIGNED AUTO_INCREMENT,
                    id_user_fk   INT UNSIGNED                  NOT NULL,
                    id_car_fk    INT UNSIGNED                  NOT NULL,
                    days         SMALLINT UNSIGNED             NOT NULL,
                    sum          INT UNSIGNED                  NOT NULL,
                    order_status ENUM ('1','2', '3', '4', '5') NOT NULL,
                    admin_notes  TEXT,
                    PRIMARY KEY (id_order),
                    FOREIGN KEY (id_user_fk) REFERENCES users (id_user),
                    FOREIGN KEY (id_car_fk) REFERENCES cars (id_car)
                )
                """);

        DaoOrders dor = new DaoOrders(connectCreator);
        dor.insert(new Orders(1, 1, 1, 1, 1, "EEEETT"));
        dor.insert(new Orders(2, 2, 2, 2, 2, ""));
        dor.insert(new Orders(3, 3, 3, 3, 3, "hhhh"));
        dor.delete(new Orders(3));
        dor.insert(new Orders(4, 4, 4, 4, 4, "eryuuu"));
        dor.update(new Orders(4, 4, 4, 4, 4, 4, "olllk"));
        dor.insert(new Orders(5, 5, 5, 5, 5, "ioio"));

        connectCreator.update("""
                CREATE TABLE customers
                (
                    id_customer     INT UNSIGNED AUTO_INCREMENT,
                    id_user_fk      INT UNSIGNED   NOT NULL,
                    id_order_fk     INT UNSIGNED   NOT NULL,
                    surname         VARCHAR(255)   NOT NULL,
                    name            VARCHAR(255)   NOT NULL,
                    passport_series VARCHAR(2)     NOT NULL,
                    passport_number INT UNSIGNED   NOT NULL,
                    customer_status ENUM ('1','2') NOT NULL,
                    PRIMARY KEY (id_customer),
                    FOREIGN KEY (id_user_fk) REFERENCES users (id_user),
                    FOREIGN KEY (id_order_fk) REFERENCES orders (id_order)
                )
                """);

        DaoCustomers dc = new DaoCustomers(connectCreator);
        dc.insert(new Customers(1, 1, "user", "user", "us", 111, 1));
        dc.insert(new Customers(2, 2, "admin", "admin", "ad", 222, 1));
        dc.insert(new Customers(3, 3, "cat", "cat", "ca", 333, 2));
        dc.insert(new Customers(4, 4, "dog", "dog", "do", 444, 1));
        dc.delete(new Customers(4));
        dc.insert(new Customers(5, 5, "pig", "pig", "pi", 555, 2));
        dc.update(new Customers(5, 5, 5, "fog", "fog", "pi", 555, 1));

    }

    public static void deleteDB(String url, String nameDB, String login, String password) {
        ConnectCreator connectCreator = new ConnectCreator(url, login, password);
        connectCreator.update("DROP DATABASE IF EXISTS " + nameDB);
    }
}