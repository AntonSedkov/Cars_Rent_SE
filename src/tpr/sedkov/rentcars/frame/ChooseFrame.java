package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.dao.impl.DaoOrders;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Orders;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ChooseFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private Table table;
    private JScrollPane scroll;
    private JLabel choose, number;
    private JTextField num;
    private JButton order, exit;
    private ConnectCreator connectCreator;
    private String login;

    public ChooseFrame(ConnectCreator connectCreator, String login) {
        this.connectCreator = connectCreator;
        this.login = login;
        setTitle(login + " ChooseFrame");
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
        table = new Table(connectCreator.query("SELECT * FROM cars WHERE car_status = 1"));
        scroll = new JScrollPane(table);
        choose = new JLabel("Choose your Car!");
        number = new JLabel("Enter number of days to rent car!");
        num = new JTextField("", 5);
        order = new JButton("Confirm the order");
        exit = new JButton("Return to UserFrame");

        panel.add(choose);
        panel.add(exit);
        panel.add(scroll);
        panel.add(number);
        panel.add(num);
        panel.add(order);

        add(panel);
    }

    public void action() {

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsersFrame(connectCreator, login);
                dispose();
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(panel, "Сумма заказа "
                        + Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))
                        * Integer.parseInt(num.getText())
                        + " $", "Message", JOptionPane.INFORMATION_MESSAGE);

                DaoOrders dor = new DaoOrders(connectCreator);
                dor.insert(new Orders(
                        getId(connectCreator.query("SELECT users.id_user FROM users WHERE users.login = '" + login + "'")),
                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                        Integer.parseInt(num.getText()),
                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))
                                * Integer.parseInt(num.getText()),
                        1, " "));
                new CustomerFrame(connectCreator, login,
                        getId(connectCreator.query("SELECT users.id_user FROM users WHERE users.login = '" + login + "'")),
                        Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                dispose();
            }
        });
    }

    public Integer getId(ResultSet rs) {
        int a = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                System.out.println();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    a = Integer.parseInt(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка в методе для получения ID");
        }
        return a;
    }
}