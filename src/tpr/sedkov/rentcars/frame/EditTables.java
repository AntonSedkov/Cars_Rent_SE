package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.dao.impl.DaoCars;
import tpr.sedkov.rentcars.dao.impl.DaoCustomers;
import tpr.sedkov.rentcars.dao.impl.DaoOrders;
import tpr.sedkov.rentcars.dao.impl.DaoUsers;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Cars;
import tpr.sedkov.rentcars.entity.Customers;
import tpr.sedkov.rentcars.entity.Orders;
import tpr.sedkov.rentcars.entity.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditTables extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JButton update, close;
    private Table table;
    private JScrollPane scroll;
    private ConnectCreator connectCreator;
    private String tableName;

    public EditTables(ConnectCreator connectCreator, String tableName) {
        this.connectCreator = connectCreator;
        this.tableName = tableName;
        setTitle(tableName + " Frame");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {
        panel = new JPanel();
        update = new JButton("Update");
        close = new JButton("Close");
        table = new Table(connectCreator.query("SELECT * FROM " + tableName));
        scroll = new JScrollPane(table);
        panel.add(update);
        panel.add(close);
        panel.add(scroll);
        add(panel);
    }

    public void action() {

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    switch (((String) tableName)) {
                        case "Users" -> {
                            DaoUsers du = new DaoUsers(connectCreator);
                            du.update(
                                    new Users(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                            String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                                            String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))));
                            updateTable();
                        }
                        case "Cars" -> {
                            DaoCars da = new DaoCars(connectCreator);
                            da.update(new Cars(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                    String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)))));
                            updateTable();
                        }
                        case "Orders" -> {
                            DaoOrders dor = new DaoOrders(connectCreator);
                            dor.update(
                                    new Orders(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 1))),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
                                            Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 5))),
                                            String.valueOf(table.getValueAt(table.getSelectedRow(), 6))));
                            updateTable();
                        }
                        case "Customers" -> {
                            DaoCustomers dc = new DaoCustomers(connectCreator);
                            dc.update(new Customers(
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 1))),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                    String.valueOf(table.getValueAt(table.getSelectedRow(), 3)),
                                    String.valueOf(table.getValueAt(table.getSelectedRow(), 4)),
                                    String.valueOf(table.getValueAt(table.getSelectedRow(), 5)),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 6))),
                                    Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 7)))));
                            updateTable();
                        }
                    }
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (((String) tableName)) {
                    case "Users" -> {
                        DaoUsers du = new DaoUsers(connectCreator);
                        du.update(new Users(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                                String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))));
                        updateTable();
                    }
                    case "Cars" -> {
                        DaoCars da = new DaoCars(connectCreator);
                        da.update(new Cars(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)))));
                        updateTable();
                    }
                    case "Orders" -> {
                        DaoOrders dor = new DaoOrders(connectCreator);
                        dor.update(new Orders(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 1))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
                                Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 5))),
                                String.valueOf(table.getValueAt(table.getSelectedRow(), 6))));
                        updateTable();
                    }
                    case "Customers" -> {
                        DaoCustomers dc = new DaoCustomers(connectCreator);
                        dc.update(
                                new Customers(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 1))),
                                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                        String.valueOf(table.getValueAt(table.getSelectedRow(), 3)),
                                        String.valueOf(table.getValueAt(table.getSelectedRow(), 4)),
                                        String.valueOf(table.getValueAt(table.getSelectedRow(), 5)),
                                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 6))),
                                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 7)))));
                        updateTable();
                    }
                }
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFrame(connectCreator);
                dispose();
            }
        });
    }

    public void updateTable() {
        panel.remove(scroll);
        table = new Table(connectCreator.query("SELECT * FROM " + tableName));
        scroll = new JScrollPane(table);
        panel.add(scroll);
        panel.updateUI();
    }
}