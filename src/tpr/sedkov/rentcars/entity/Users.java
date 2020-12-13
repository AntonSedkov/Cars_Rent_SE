package tpr.sedkov.rentcars.entity;

import java.util.StringJoiner;

public class Users {

    private int idUser;
    private String login;
    private String password;
    private int role;
    private int userStatus;

    public Users(int idUser, String login, String password, int role, int userStatus) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public Users(String login, String password, int role, int userStatus) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public Users(int idUser) {
        this.idUser = idUser;
    }

    public Users() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        if (idUser != users.idUser) return false;
        if (role != users.role) return false;
        if (userStatus != users.userStatus) return false;
        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        return password != null ? password.equals(users.password) : users.password == null;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + role;
        result = 31 * result + userStatus;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Users.class.getSimpleName() + "[", "]")
                .add("idUser=" + idUser)
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .add("userStatus=" + userStatus)
                .toString();
    }

}