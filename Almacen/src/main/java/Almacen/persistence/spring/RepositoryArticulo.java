package main.java.Almacen.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.Almacen.model.Articulo;

@Repository
public interface RepositoryArticulo extends JpaRepository<Articulo, Integer>{

	//quyeries xtras;as lenguaje SQJ1
}
