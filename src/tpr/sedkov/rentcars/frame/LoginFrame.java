package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.database.ConnectCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JTextField login;
    private JPasswordField password;
    private JLabel labelLogin, labelPassword;
    private JButton entry, registration;
    private ConnectCreator connectCreator;

    public LoginFrame(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
        setTitle("LoginFrame");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    private void initComponents() {

        panel = new JPanel();
        login = new JTextField("user", 20);
        password = new JPasswordField("user", 20);
        labelLogin = new JLabel("Login");
        labelPassword = new JLabel("Password");

        entry = new JButton("Entry");
        registration = new JButton("Registration");

        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelPassword);
        panel.add(password);
        panel.add(entry);
        panel.add(registration);

        add(panel);
    }

    private void action() {

        entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = connectCreator.query("SELECT * FROM users WHERE login = '" + login.getText() + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(panel, "Пользователь существует", "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (rs.getString("password").equals(String.valueOf(password.getPassword()))) {
                            JOptionPane.showMessageDialog(panel, "Пароль совпал", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                            if (rs.getInt("role") == 1) {
                                new UsersFrame(connectCreator, login.getText());
                                dispose();
                            } else {
                                new AdminFrame(connectCreator);
                                dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Пароль не совпадает", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Пользователь не найден", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    System.out.println("Ошибка идентификации");
                }
            }
        });

        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame(connectCreator);
                dispose();
            }
        });
    }
}