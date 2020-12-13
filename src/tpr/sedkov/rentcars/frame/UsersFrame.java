package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.database.ConnectCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel, p1, p2;
    private Table table;
    private JScrollPane scroll;
    private JLabel hello, orders;
    private JButton choose, pay, exit;
    private ConnectCreator connectCreator;
    private String login;

    public UsersFrame(ConnectCreator connectCreator, String login) {
        this.connectCreator = connectCreator;
        this.login = login;
        setTitle(login + " Frame");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {
        panel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();

        table = new Table(
                connectCreator.query("SELECT orders.id_order, users.id_user, users.login, cars.model, orders.sum, " +
                        "orders.order_status FROM orders " +
                        "JOIN users ON orders.id_user_fk = users.id_user " +
                        "JOIN cars ON orders.id_car_fk = cars.id_car "
                        + "WHERE orders.order_status < 5 AND users.login = '"
                        + login + "'"));

        scroll = new JScrollPane(table);

        hello = new JLabel("Hello, " + login + "!");
        orders = new JLabel("Your Orders!");

        choose = new JButton("Choose Cars");
        pay = new JButton("Pay");
        exit = new JButton("Exit");

        p1.add(hello);
        p1.add(choose);
        p1.add(exit);
        panel.add(p1);
        p2.add(orders);
        p2.add(pay);
        panel.add(p2);
        panel.add(scroll);

        add(panel);
    }

    public void action() {

        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectCreator.update("UPDATE orders SET order_status = 3 WHERE order_status = 2 AND id_order = "
                        + Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                updateTable();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(connectCreator);
                dispose();
            }
        });

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooseFrame(connectCreator, login);
                dispose();
            }
        });

    }

    public void updateTable() {
        panel.remove(scroll);
        table = new Table(connectCreator.query("SELECT orders.id_order, users.login, cars.model, orders.sum, " +
                "orders.order_status FROM orders " +
                "JOIN users ON orders.id_user_fk = users.id_user " +
                "JOIN cars ON orders.id_car_fk = cars.id_car " +
                "WHERE orders.order_status < 5 AND users.login = '" + login + "'"));
        scroll = new JScrollPane(table);
        panel.add(scroll);
        panel.updateUI();
    }
}