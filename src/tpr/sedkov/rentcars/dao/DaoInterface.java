package tpr.sedkov.rentcars.dao;

public interface DaoInterface<T> {

    public void insert(T ob);

    public void update(T ob);

    public void delete(T ob);

}