package edu.pe.idat.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pe.idat.app.models.dao.ICliente;
import edu.pe.idat.app.models.dao.IEmpleado;
import edu.pe.idat.app.models.dao.IProducto;
import edu.pe.idat.app.models.dao.IVenta;
import edu.pe.idat.app.models.entity.Cliente;
import edu.pe.idat.app.models.entity.Empleado;
import edu.pe.idat.app.models.entity.Producto;
import edu.pe.idat.app.models.entity.Venta;

@Service
public class ServiceApp implements IServiceApp {

	@Autowired
	private ICliente iCliente;
	
	@Autowired
	private IProducto iProducto;
	
	@Autowired
	private IEmpleado iEmpleado;
	
	@Autowired
	private IVenta iVenta;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes(String palabraClave) {
		if(palabraClave != null) {
			return iCliente.findAll(palabraClave);
		}
		return (List<Cliente>) iCliente.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarClienteId(Integer id) {
		return iCliente.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void guardarCliente(Cliente cliente) {
		iCliente.save(cliente);
	}

	@Override
	@Transactional
	public void eliminarClienteId(Integer id) {
		iCliente.deleteById(id);
	}

	@Override
	public List<Producto> buscarProductoPorNombre(String nombre) {
		return iProducto.buscarPorNombre(nombre);
	}

	/*EMPLEADOS*/
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> listarEmpleados(String palabraClave) {
		if(palabraClave != null) {
			return iEmpleado.findAll(palabraClave);
		}
		return (List<Empleado>) iEmpleado.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado buscarEmpleadoId(Integer id) {
		return iEmpleado.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void guardarEmpleado(Empleado empleado) {
		iEmpleado.save(empleado);
	}

	@Override
	@Transactional
	public void eliminarEmpleadoId(Integer id) {
		iEmpleado.deleteById(id);
	}
	
	/*PRODUCTOS*/

	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarProducto( String term) {
		if(term != null) {
			return iProducto.buscarPorNombre(term);
		}
		return (List<Producto>) iProducto.findAll();
	}

	@Override
	@Transactional
	public Producto buscarProductoId(Integer id) {
		return iProducto.findById(id).orElse(null);
	}

	@Override
	public void guardarProducto(Producto producto) {
		iProducto.save(producto);
	}

	@Override
	public void eliminarProductoId(Integer id) {
		iProducto.deleteById(id);
	}

	/*VENTA*/
	@Override
	@Transactional
	public void guardarVenta(Venta venta) {
		iVenta.save(venta);
	}

	@Override
	@Transactional
	public void eliminarVenta(Integer id) {
		iVenta.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Venta buscarVentaId(Integer id) {
		return iVenta.findById(id).orElse(null);
	}

}
