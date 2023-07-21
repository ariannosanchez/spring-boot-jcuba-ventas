package edu.pe.idat.app.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.entity.Producto;
import edu.pe.idat.app.models.service.IServiceApp;

@Controller
@SessionAttributes("producto")
public class ProductoController {
	@Autowired
	private IServiceApp iServiceApp;
	
	@GetMapping("/producto/listar")
	public String listar(Model model,@Param("term")String term) {
		model.addAttribute("productos", iServiceApp.listarProducto(term));
		model.addAttribute("term",term);
		return "productos/listar";
	}
	
	@GetMapping("/producto/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Producto producto = iServiceApp.buscarProductoId(id);
		if (producto == null) {
			redirectAttributes.addFlashAttribute("error", "!Producto no existe¡");
			return "redirect:/producto/listar";
		}
		model.addAttribute("titulo", "Producto: " + producto.getNombre());
		model.addAttribute("producto", producto);
		return "productos/detalle";
	}
	
	@GetMapping("/producto/formulario")
	public String formulario(Model model) {
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		return "productos/formulario";
	}
	
	@PostMapping("/producto/formulario")
	public String registrar(@Valid Producto producto, BindingResult bindingResult, Model model, SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "productos/formulario";
		}
		
		iServiceApp.guardarProducto(producto);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("info", "!Producto registrado con éxito¡");
		return "redirect:/producto/listar";
	}
	
	
	@GetMapping("/producto/formulario/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Producto producto = null;
		if (id > 0) {
			producto = iServiceApp.buscarProductoId(id);
		}else {
			return "redirect:/producto/listar";
		}
		model.addAttribute("producto", producto);
		return "productos/formulario";
	}
	
	@GetMapping("/producto/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		iServiceApp.eliminarProductoId(id);
		return "redirect:/producto/listar";
	}
}
