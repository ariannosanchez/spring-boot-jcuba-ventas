package edu.pe.idat.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.pe.idat.app.models.entity.Empleado;

public interface IEmpleado extends CrudRepository<Empleado, Integer> {
	@Query("select p from Empleado p where"
			+ " CONCAT(p.id, p.nombre, p.apellidos, p.dni, p.email, p.edad, p.sexo)"
			+ " LIKE %?1%")
	public List<Empleado> findAll(String palabraClave);
}
