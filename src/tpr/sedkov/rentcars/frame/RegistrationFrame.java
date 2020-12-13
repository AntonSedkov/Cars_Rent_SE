package tpr.sedkov.rentcars.frame;

import tpr.sedkov.rentcars.dao.impl.DaoUsers;
import tpr.sedkov.rentcars.database.ConnectCreator;
import tpr.sedkov.rentcars.entity.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JTextField login;
    private JPasswordField password, password2;
    private JLabel labelLogin, labelPassword, labelPassword2;
    private JButton registration;
    private ConnectCreator connectCreator;

    public RegistrationFrame(ConnectCreator connectCreator) {
        this.connectCreator = connectCreator;
        setTitle("RegistrationFrame");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    private void initComponents() {

        panel = new JPanel();
        login = new JTextField("", 15);
        password = new JPasswordField("", 15);
        password2 = new JPasswordField("", 15);

        labelLogin = new JLabel("New Login");
        labelPassword = new JLabel("New Password");
        labelPassword2 = new JLabel("Check Password");

        registration = new JButton("Registration");

        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelPassword);
        panel.add(password);
        panel.add(labelPassword2);
        panel.add(password2);
        panel.add(registration);

        add(panel);
    }

    private void action() {

        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResultSet rs = connectCreator.query("SELECT * FROM users WHERE login = '" + login.getText() + "'");
                try {
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(panel, "Пользователь с таким именем уже существует", "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Pattern pt = Pattern.compile("^(?=.*?\\d)(?=.*?[A-Z]).{3,10}$");
                        Matcher mc = pt.matcher(login.getText());
                        Matcher mc2 = pt.matcher(String.valueOf(password.getPassword()));
                        if (mc.find() && mc2.find() && String.valueOf(password.getPassword())
                                .equals(String.valueOf(password2.getPassword()))) {
                            DaoUsers du = new DaoUsers(connectCreator);
                            du.insert(new Users(login.getText(), String.valueOf(password.getPassword()), 1, 1));
                            JOptionPane.showMessageDialog(panel, "Пользователь успешно зарегистрирован", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new UsersFrame(connectCreator, login.getText());
                            dispose();
                        } else if (!String.valueOf(password.getPassword())
                                .equals(String.valueOf(password2.getPassword()))) {
                            JOptionPane.showMessageDialog(panel, "Пароли не совпадают", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(panel,
                                    "Имя и пароль должны содержать цифру и заглавную букву, количество символов от 3 до 10",
                                    "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException e1) {
                    System.out.println("Ошибка при регистрации");
                }
            }
        });
    }
}