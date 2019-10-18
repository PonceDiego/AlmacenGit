package main.java.Almacen.persistence.spring;

import java.util.List;

public interface DaoInterface<T> {
	void save(T entity);
	List<T> findAll();
	T getById(int id);
}
