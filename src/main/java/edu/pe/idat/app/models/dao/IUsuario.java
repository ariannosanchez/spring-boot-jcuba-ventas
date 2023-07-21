package edu.pe.idat.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import edu.pe.idat.app.models.entity.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer> {

	public Usuario findByUsuario(String usuario);
}
