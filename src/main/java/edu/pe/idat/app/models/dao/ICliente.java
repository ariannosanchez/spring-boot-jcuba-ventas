package edu.pe.idat.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.pe.idat.app.models.entity.Cliente;

public interface ICliente extends CrudRepository<Cliente, Integer> {
	@Query("select p from Cliente p where"
			+ " CONCAT(p.id, p.nombre, p.apellidos, p.email)"
			+ " LIKE %?1%")
	public List<Cliente> findAll(String palabraClave);
}
