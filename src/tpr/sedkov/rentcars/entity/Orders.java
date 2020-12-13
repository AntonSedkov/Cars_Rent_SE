package tpr.sedkov.rentcars.entity;

import java.util.StringJoiner;

public class Orders {
    private int idOrder;
    private int idUserFk;
    private int idCarFk;
    private int days;
    private int sum;
    private int orderStatus;
    private String adminNotes;

    public Orders(int idOrder, int idUserFk, int idCarFk, int days, int sum, int orderStatus, String adminNotes) {
        this.idOrder = idOrder;
        this.idUserFk = idUserFk;
        this.idCarFk = idCarFk;
        this.days = days;
        this.sum = sum;
        this.orderStatus = orderStatus;
        this.adminNotes = adminNotes;
    }

    public Orders(int idUserFk, int idCarFk, int days, int sum, int orderStatus, String adminNotes) {
        this.idUserFk = idUserFk;
        this.idCarFk = idCarFk;
        this.days = days;
        this.sum = sum;
        this.orderStatus = orderStatus;
        this.adminNotes = adminNotes;
    }

    public Orders(int idOrder) {
        this.idOrder = idOrder;
    }

    public Orders() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUserFk() {
        return idUserFk;
    }

    public void setIdUserFk(int idUserFk) {
        this.idUserFk = idUserFk;
    }

    public int getIdCarFk() {
        return idCarFk;
    }

    public void setIdCarFk(int idCarFk) {
        this.idCarFk = idCarFk;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAdminNotes() {
        return adminNotes;
    }

    public void setAdminNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        if (idOrder != orders.idOrder) return false;
        if (idUserFk != orders.idUserFk) return false;
        if (idCarFk != orders.idCarFk) return false;
        if (days != orders.days) return false;
        if (sum != orders.sum) return false;
        if (orderStatus != orders.orderStatus) return false;
        return adminNotes != null ? adminNotes.equals(orders.adminNotes) : orders.adminNotes == null;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + idUserFk;
        result = 31 * result + idCarFk;
        result = 31 * result + days;
        result = 31 * result + sum;
        result = 31 * result + orderStatus;
        result = 31 * result + (adminNotes != null ? adminNotes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Orders.class.getSimpleName() + "[", "]")
                .add("idOrder=" + idOrder)
                .add("idUserFk=" + idUserFk)
                .add("idCarFk=" + idCarFk)
                .add("days=" + days)
                .add("sum=" + sum)
                .add("orderStatus=" + orderStatus)
                .add("adminNotes='" + adminNotes + "'")
                .toString();
    }

}