package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.database.WorkDBCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JTextField url, login, nameDB;
    private JPasswordField password;
    private JLabel labelUrl, labelLogin, labelPassword, labelNameDB;
    private JButton create, delete, connect;
    private ConnectCreator connectCreator;

    public StartFrame() {
        setTitle("StartFrame");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    private void initComponents() {
        panel = new JPanel();
        url = new JTextField("jdbc:mysql://localhost:3306/cars_rent?serverTimezone=Europe/Moscow&useSSL=false", 46);        //127.0.0.1
        login = new JTextField("root", 18);
        nameDB = new JTextField("cars_rent", 40);
        password = new JPasswordField("7h3_d4t4b4sE", 18);
        labelNameDB = new JLabel("NameDB");
        labelUrl = new JLabel("URL");
        labelLogin = new JLabel("Login");
        labelPassword = new JLabel("Password");

        create = new JButton("Create");
        delete = new JButton("Delete");
        connect = new JButton("Connect");

        panel.add(labelUrl);
        panel.add(url);
        panel.add(labelNameDB);
        panel.add(nameDB);
        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelPassword);
        panel.add(password);
        panel.add(create);
        panel.add(delete);
        panel.add(connect);

        add(panel);
    }

    private void action() {

        create.addActionListener(e -> {
            WorkDBCreator.createDB(url.getText(), nameDB.getText(), login.getText(),
                    String.valueOf(password.getPassword()));

            JOptionPane.showMessageDialog(panel,
                    "Database installing successfully",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        });

        delete.addActionListener(e -> {
            WorkDBCreator.deleteDB(url.getText(), nameDB.getText(), login.getText(),
                    String.valueOf(password.getPassword()));

            JOptionPane.showMessageDialog(panel,
                    "Database drop successfully",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        });

        connect.addActionListener(e -> {
            connectCreator = new ConnectCreator(url.getText(), login.getText(), String.valueOf(password.getPassword()));
            new LoginFrame(connectCreator);
            dispose();
        });
    }
}