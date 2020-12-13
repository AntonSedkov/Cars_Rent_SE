package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.dao.impl.DaoCustomers;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Customers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JTextField surname, name, passport_series, passport_number;
    private JLabel labelS, labelN, labelPS, labelPN;
    private JButton make;
    private ConnectCreator connectCreator;
    private String login;
    private int id_u, id_o;

    public CustomerFrame(ConnectCreator connectCreator, String login, int id_u, int id_o) {
        this.connectCreator = connectCreator;
        this.login = login;
        this.id_u = id_u;
        this.id_o = id_o;
        setTitle("NewCustomerFrame");
        setSize(250, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {

        panel = new JPanel();
        surname = new JTextField("", 20);
        name = new JTextField("", 20);
        passport_series = new JTextField("", 2);
        passport_number = new JTextField("", 7);

        labelS = new JLabel("Surname");
        labelN = new JLabel("Name");
        labelPS = new JLabel("Passport Series");
        labelPN = new JLabel("Passport Number");

        make = new JButton("Continue order");

        panel.add(labelS);
        panel.add(surname);
        panel.add(labelN);
        panel.add(name);
        panel.add(labelPS);
        panel.add(passport_series);
        panel.add(labelPN);
        panel.add(passport_number);
        panel.add(make);

        add(panel);
    }

    public void action() {

        make.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DaoCustomers dc = new DaoCustomers(connectCreator);
                dc.insert(new Customers(id_u, id_o, surname.getText(), name.getText(), passport_series.getText(),
                        Integer.parseInt(passport_number.getText()), 1));

                JOptionPane.showMessageDialog(panel, "Ваш заказ принят в обработку", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                new UsersFrame(connectCreator, login);
                dispose();
            }
        });
    }
}