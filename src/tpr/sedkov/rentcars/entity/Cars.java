package tpr.sedkov.rentcars.entity;

public class Cars {

    private int idCar;
    private String model;
    private int carYear;
    private int mileage;
    private int priceForDay;
    private int carStatus;

    public Cars(int idCar, String model, int carYear, int mileage, int priceForDay, int carStatus) {
        this.idCar = idCar;
        this.model = model;
        this.carYear = carYear;
        this.mileage = mileage;
        this.priceForDay = priceForDay;
        this.carStatus = carStatus;
    }

    public Cars(int idCar) {
        this.idCar = idCar;
    }

    public Cars() {
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPriceForDay() {
        return priceForDay;
    }

    public void setPriceForDay(int priceForDay) {
        this.priceForDay = priceForDay;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idCar;
        result = prime * result + mileage;
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + carYear;
        result = prime * result + priceForDay;
        result = prime * result + carStatus;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cars other = (Cars) obj;
        if (idCar != other.idCar) return false;
        if (mileage != other.mileage) return false;
        if (model == null) {
            if (other.model != null) return false;
        } else if (!model.equals(other.model))
            return false;
        if (carYear != other.carYear) return false;
        if (priceForDay != other.priceForDay) return false;
        return carStatus == other.carStatus;
    }

    @Override
    public String toString() {
        return "Cars [id_a=" + idCar + ", model=" + model + ", model_year=" + carYear + ", kilometrage=" + mileage
                + ", price_for_1_day=" + priceForDay + ", status_a=" + carStatus + "]";
    }

}