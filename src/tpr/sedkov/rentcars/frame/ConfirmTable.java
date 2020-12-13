package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.database.ConnectCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmTable extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JButton update, reject, exit;
    private Table table;
    private JScrollPane scroll;
    private ConnectCreator connectCreator;
    private String x;

    public ConfirmTable(ConnectCreator connectCreator, String x) {
        this.connectCreator = connectCreator;
        this.x = x;
        setTitle(x + " Frame");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {
        panel = new JPanel();
        update = new JButton(x);
        exit = new JButton("Exit");
        reject = new JButton("No to " + x);

        panel.add(update);
        panel.add(reject);
        panel.add(exit);

        formTable();

        scroll = new JScrollPane(table);
        panel.add(scroll);
        add(panel);

    }

    public void action() {

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (((String) x)) {
                    case "Confirm" -> {
                        connectCreator.update("UPDATE orders SET order_status = 2 WHERE id_order = "
                                + Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        updateTable();
                    }
                    case "Pay" -> {
                        connectCreator.update("UPDATE orders SET order_status = 3 WHERE id_order = "
                                + Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        updateTable();
                    }
                    case "Give" -> {
                        connectCreator.update("UPDATE orders SET order_status = 4 WHERE id_order = "
                                + Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        updateTable();
                    }
                    case "Close" -> {
                        connectCreator.update("UPDATE orders SET order_status = 5, admin_notes = '"
                                + table.getValueAt(table.getSelectedRow(), 6) + "' WHERE id_order = "
                                + Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        updateTable();
                    }
                }
            }
        });

        reject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (((String) x)) {
                    case "Confirm" -> {
                        if (String.valueOf(table.getValueAt(table.getSelectedRow(), 6)) != null) {
                            connectCreator.update("UPDATE orders SET order_status = 5, admin_notes = '"
                                    + table.getValueAt(table.getSelectedRow(), 6) + "' WHERE id_order = "
                                    + Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        } else {
                            JOptionPane.showMessageDialog(panel, "Введите причину отказа", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        updateTable();
                    }
                    case "Pay" -> {
                        if (String.valueOf(table.getValueAt(table.getSelectedRow(), 6)) != null) {
                            connectCreator.update("UPDATE orders SET order_status = 5, admin_notes = '"
                                    + table.getValueAt(table.getSelectedRow(), 6) + "' WHERE id_order = "
                                    + Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        } else {
                            JOptionPane.showMessageDialog(panel, "Введите причину отмены заказа", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        updateTable();
                    }
                    case "Give" -> {
                        if (String.valueOf(table.getValueAt(table.getSelectedRow(), 6)) != null) {
                            connectCreator.update("UPDATE orders SET order_status = 5, admin_notes = '"
                                    + table.getValueAt(table.getSelectedRow(), 6) + "' WHERE id_order = "
                                    + Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        } else {
                            JOptionPane.showMessageDialog(panel, "Введите причину отмены заказа", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        updateTable();
                    }
                    case "Close" -> {
                        connectCreator.update("UPDATE orders SET order_status = 5, admin_notes = '"
                                + table.getValueAt(table.getSelectedRow(), 6) + "' WHERE id_order = "
                                + Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                        updateTable();
                    }
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFrame(connectCreator);
                dispose();
            }
        });

    }

    public void updateTable() {
        panel.remove(scroll);
        formTable();
        scroll = new JScrollPane(table);
        panel.add(scroll);
        panel.updateUI();
    }

    public void formTable() {
        switch (((String) x)) {
            case "Confirm" -> table = new Table(connectCreator.query("SELECT orders.id_order, users.login, cars.model, orders.days, "
                    + "orders.sum, orders.order_status, orders.admin_notes FROM orders " +
                    "JOIN users ON orders.id_user_fk = users.id_user " +
                    "JOIN cars ON orders.id_car_fk = cars.id_car "
                    + "WHERE orders.order_status = 1"));
            case "Pay" -> table = new Table(connectCreator.query("SELECT orders.id_order, users.login, cars.model, orders.days, "
                    + "orders.sum, orders.order_status, orders.admin_notes FROM orders " +
                    "JOIN users ON orders.id_user_fk = users.id_user " +
                    "JOIN cars ON orders.id_car_fk = cars.id_car "
                    + "WHERE orders.order_status = 2"));
            case "Give" -> table = new Table(connectCreator.query("SELECT orders.id_order, users.login, cars.model, orders.days, "
                    + "orders.sum, orders.order_status, orders.admin_notes FROM orders " +
                    "JOIN users ON orders.id_user_fk = users.id_user " +
                    "JOIN cars ON orders.id_car_fk = cars.id_car "
                    + "WHERE orders.order_status = 3"));
            case "Close" -> table = new Table(connectCreator.query("SELECT orders.id_order, users.login, cars.model, orders.days, "
                    + "orders.sum, orders.order_status, orders.admin_notes FROM orders " +
                    "JOIN users ON orders.id_user_fk = users.id_user " +
                    "JOIN cars ON orders.id_car_fk = cars.id_car "
                    + "WHERE orders.order_status = 4"));
        }
    }
}