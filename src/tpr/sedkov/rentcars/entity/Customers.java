package tpr.sedkov.rentcars.entity;

import java.util.StringJoiner;

public class Customers {

    private int idCustomer;
    private int idUserFk;
    private int idOrderFk;
    private String surname;
    private String name;
    private String passportSeries;
    private int passportNumber;
    private int customerStatus;

    public Customers(int idCustomer, int idUserFk, int idOrderFk, String surname, String name, String passportSeries,
                     int passportNumber, int customerStatus) {
        this.idCustomer = idCustomer;
        this.idUserFk = idUserFk;
        this.idOrderFk = idOrderFk;
        this.surname = surname;
        this.name = name;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.customerStatus = customerStatus;
    }

    public Customers(int idUserFk, int idOrderFk, String surname, String name, String passportSeries, int passportNumber,
                     int customerStatus) {
        this.idUserFk = idUserFk;
        this.idOrderFk = idOrderFk;
        this.surname = surname;
        this.name = name;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.customerStatus = customerStatus;
    }

    public Customers(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Customers() {
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdUserFk() {
        return idUserFk;
    }

    public void setIdUserFk(int idUserFk) {
        this.idUserFk = idUserFk;
    }

    public int getIdOrderFk() {
        return idOrderFk;
    }

    public void setIdOrderFk(int idOrderFk) {
        this.idOrderFk = idOrderFk;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        if (idCustomer != customers.idCustomer) return false;
        if (idUserFk != customers.idUserFk) return false;
        if (idOrderFk != customers.idOrderFk) return false;
        if (passportNumber != customers.passportNumber) return false;
        if (customerStatus != customers.customerStatus) return false;
        if (surname != null ? !surname.equals(customers.surname) : customers.surname != null) return false;
        if (name != null ? !name.equals(customers.name) : customers.name != null) return false;
        return passportSeries != null ? passportSeries.equals(customers.passportSeries) : customers.passportSeries == null;
    }

    @Override
    public int hashCode() {
        int result = idCustomer;
        result = 31 * result + idUserFk;
        result = 31 * result + idOrderFk;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + passportNumber;
        result = 31 * result + customerStatus;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customers.class.getSimpleName() + "[", "]")
                .add("idCustomer=" + idCustomer)
                .add("idUserFk=" + idUserFk)
                .add("idOrderFk=" + idOrderFk)
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .add("passportSeries='" + passportSeries + "'")
                .add("passportNumber=" + passportNumber)
                .add("customerStatus=" + customerStatus)
                .toString();
    }

}