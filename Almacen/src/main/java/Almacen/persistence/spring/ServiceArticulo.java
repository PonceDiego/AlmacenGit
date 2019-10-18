package main.java.Almacen.persistence.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.Almacen.model.Articulo;

@Service
public class ServiceArticulo implements DaoInterface<Articulo>{
	
	@Autowired
	private RepositoryArticulo repo;
	
	public void save(Articulo entity) {
		repo.save(entity);
	}

	public List<Articulo> findAll() {
		return repo.findAll();
	}

	public Articulo getById(int id) {
		return repo.getOne(id);
	}
	
	
}
