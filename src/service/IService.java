package service;

public interface IService < T, E>{
    T[] getAll();
    T findById(E id);

    boolean save(T t);

    boolean delete(E id);

    boolean add(T t);
}
