package main.java.Almacen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import main.java.Almacen.model.Articulo;
import main.java.Almacen.persistence.spring.DaoInterface;

@RestController
public class ArticuloController {
	
	@Autowired
	private DaoInterface<Articulo> repo;
	
	@GetMapping(value = "/ArticuloTest")
	public ResponseEntity<Articulo> insertarArticulo() {
		System.out.println("init");
		Articulo arti = repo.getById(4);
		System.out.println("paso get");
		return new ResponseEntity<Articulo>(arti, HttpStatus.I_AM_A_TEAPOT);
		
	}
	
	@GetMapping(value = "/ArticuloPagina")
	public ModelAndView pagina() {
		return new ModelAndView("listaArticulosAdmin");
	}
}
