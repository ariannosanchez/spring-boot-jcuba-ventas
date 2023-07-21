package edu.pe.idat.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.entity.Cliente;
import edu.pe.idat.app.models.entity.Producto;
import edu.pe.idat.app.models.entity.Venta;
import edu.pe.idat.app.models.entity.VentaProducto;
import edu.pe.idat.app.models.service.IServiceApp;

@Controller
@RequestMapping("venta")
@SessionAttributes("venta")
public class VentaController {

	@Autowired
	private IServiceApp iServiceApp;
	
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
		Venta venta = iServiceApp.buscarVentaId(id);
		if (venta == null) {
			redirectAttributes.addFlashAttribute("error", "!Venta no existe!");
			return "redirect:/listar";
		}
		model.addAttribute("venta", venta);
		return "ventas/detalle";
	}
	
	@GetMapping("/formulario/{clientes_id}")
	public String formulario(@PathVariable Integer clientes_id, Model model) {
		Cliente cliente = iServiceApp.buscarClienteId(clientes_id);
		if(cliente == null) {
			return "redirect:/listar";
		}
		Venta venta = new Venta();
		venta.setCliente(cliente);
		model.addAttribute("titulo", "Registrar Venta");
		model.addAttribute("venta", venta);
		return "ventas/formulario";
	}

	@GetMapping(value = "/cargar-productos/{term}", produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return iServiceApp.buscarProductoPorNombre(term);
	}
	
	@PostMapping("/formulario")
	public String guardar(@Valid Venta venta, BindingResult bindingResult,
			@RequestParam(name = "producto_id[]", required = false) Integer[] producto_id,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, 
			Model model, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("titulo", "Registrar Venta");
			return "ventas/formulario";
		}
		
		if (producto_id == null || producto_id.length <= 0) {
			model.addAttribute("titulo", "Registrar Venta");
			model.addAttribute("error", "¡Debe seleccionar al menos un producto!");
			return "ventas/formulario";
		}
		
		for(int i = 0; i < producto_id.length; i++) {
			Producto producto = iServiceApp.buscarProductoId(producto_id[i]);
			VentaProducto ventaProducto = new VentaProducto();
			ventaProducto.setProducto(producto);
			ventaProducto.setCantidad(cantidad[i]);
			venta.setItem(ventaProducto);
		}
		
		iServiceApp.guardarVenta(venta);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("info", "¡Venta registrada con éxito!");
		return "redirect:/cliente/detalle/" + venta.getCliente().getId();
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		iServiceApp.eliminarVenta(id);
		redirectAttributes.addFlashAttribute("warning", "¡Venta Eliminado!");
		return "redirect:/cliente/listar";
	}
	
}
