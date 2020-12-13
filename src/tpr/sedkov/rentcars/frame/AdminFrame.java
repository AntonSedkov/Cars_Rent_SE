package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.database.ConnectCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel, p1, p2;
    private JButton tableUsers, tableCars, tableCustomers, tableOrders, tableConfirm, tablePay, tableGive, tableClose;
    private Table table;
    private JScrollPane scroll;
    private ConnectCreator connectCreator;
    private JMenuBar menu;
    private JMenu editTable, confirmTable;
    private JMenuItem tableU, tableCa, tableCu, tableO, tableCo, tableP, tableG, tableCl, exit;

    public AdminFrame(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
        setTitle("AdminFrame");
        setSize(600, 600);
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

        tableUsers = new JButton("tableUsers");
        tableCars = new JButton("tableCars");
        tableCustomers = new JButton("tableCustomers");
        tableOrders = new JButton("tableOrders");
        tableConfirm = new JButton("tableConfirm");
        tablePay = new JButton("tablePay");
        tableGive = new JButton("tableGive");
        tableClose = new JButton("tableClose");

        menu = new JMenuBar();

        table = new Table(connectCreator.query("SELECT orders.id_order, users.login, cars.model, " +
                "orders.sum, orders.order_status FROM orders " +
                "JOIN users ON orders.id_user_fk = users.id_user " +
                "JOIN cars ON orders.id_car_fk = cars.id_car " +
                "WHERE orders.order_status < 5"));
        scroll = new JScrollPane(table);

        editTable = new JMenu("EditTable");
        confirmTable = new JMenu("ConfirmTable");

        tableU = new JMenuItem("Users");
        tableCa = new JMenuItem("Cars");
        tableCu = new JMenuItem("Customers");
        tableO = new JMenuItem("Orders");
        tableCo = new JMenuItem("Confirm");
        tableP = new JMenuItem("Pay");
        tableG = new JMenuItem("Give");
        tableCl = new JMenuItem("Close");
        exit = new JMenuItem("Exit");

        menu.add(editTable);
        menu.add(confirmTable);
        menu.add(exit);

        editTable.add(tableU);
        editTable.add(tableCa);
        editTable.add(tableCu);
        editTable.add(tableO);
        confirmTable.add(tableCo);
        confirmTable.add(tableP);
        confirmTable.add(tableG);
        confirmTable.add(tableCl);

        setJMenuBar(menu);

        p1.add(tableUsers);
        p1.add(tableCars);
        p1.add(tableCustomers);
        p1.add(tableOrders);
        panel.add(p1);
        panel.add(scroll);
        p2.add(tableConfirm);
        p2.add(tablePay);
        p2.add(tableGive);
        p2.add(tableClose);
        panel.add(p2);

        add(panel);
    }

    public void action() {
        tableUsers.addActionListener(e -> {
            new EditTables(connectCreator, "Users");
            dispose();
        });

        tableCars.addActionListener(e -> {
            new EditTables(connectCreator, "Cars");
            dispose();
        });

        tableCustomers.addActionListener(e -> {
            new EditTables(connectCreator, "Customers");
            dispose();
        });

        tableOrders.addActionListener(e -> {
            new EditTables(connectCreator, "Orders");
            dispose();
        });

        tableConfirm.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Confirm");
            dispose();
        });

        tablePay.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Pay");
            dispose();
        });

        tableGive.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Give");
            dispose();
        });

        tableClose.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Close");
            dispose();
        });

        tableU.addActionListener(e -> {
            new EditTables(connectCreator, "Users");
            dispose();
        });

        tableCa.addActionListener(e -> {
            new EditTables(connectCreator, "Cars");
            dispose();
        });

        tableCu.addActionListener(e -> {
            new EditTables(connectCreator, "Customers");
            dispose();
        });

        tableO.addActionListener(e -> {
            new EditTables(connectCreator, "Orders");
            dispose();
        });

        tableCo.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Confirm");
            dispose();
        });

        tableP.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Pay");
            dispose();
        });

        tableG.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Give");
            dispose();
        });

        tableCl.addActionListener(e -> {
            new ConfirmTable(connectCreator, "Close");
            dispose();
        });

        exit.addActionListener(e -> {
            new LoginFrame(connectCreator);
            dispose();
        });
    }
}