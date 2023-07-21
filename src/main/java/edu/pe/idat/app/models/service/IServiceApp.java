package edu.pe.idat.app.models.service;

import java.util.List;

import edu.pe.idat.app.models.entity.Cliente;
import edu.pe.idat.app.models.entity.Empleado;
import edu.pe.idat.app.models.entity.Producto;
import edu.pe.idat.app.models.entity.Venta;

public interface IServiceApp {
	
	public List<Cliente> listarClientes(String palabraClave);

	public Cliente buscarClienteId(Integer id);
	
	public void guardarCliente(Cliente cliente);
		
	public void eliminarClienteId(Integer id);

	public List<Producto> buscarProductoPorNombre(String nombre);
	
	/*EMPLEADOS*/
	
	public List<Empleado> listarEmpleados(String palabraClave);
	
	public Empleado buscarEmpleadoId(Integer id);
	
	public void guardarEmpleado(Empleado empleado);
	
	public void eliminarEmpleadoId(Integer id);
	
	/*PRODUCTOS*/
	
	public List<Producto> listarProducto(String term);
	
	public Producto buscarProductoId(Integer id);
	
	public void guardarProducto(Producto producto);
	
	public void eliminarProductoId(Integer id);
	
	/*VENTA*/
	
	public void guardarVenta(Venta venta);
	
	public void eliminarVenta(Integer id);
	
	public Venta buscarVentaId(Integer id);
	
}
