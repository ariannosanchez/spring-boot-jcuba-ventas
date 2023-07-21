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

import edu.pe.idat.app.models.dao.IEmpleado;
import edu.pe.idat.app.models.entity.Empleado;
import edu.pe.idat.app.models.service.IServiceApp;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {
	@Autowired
	private IServiceApp iServiceApp;
	
	@GetMapping("/empleado/listar")
	public String listar(Model model,@Param("palabraClave")String palabraClave) {
		model.addAttribute("empleados", iServiceApp.listarEmpleados(palabraClave));
		model.addAttribute("palabraClave",palabraClave);
		return "empleados/listar";
	}
	
	@GetMapping("/empleado/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Empleado empleado = iServiceApp.buscarEmpleadoId(id);
		if (empleado == null) {
			redirectAttributes.addFlashAttribute("error", "¡Empleado no existe!");
			return "redirect:/empleado/listar";
		}
		model.addAttribute("titulo", "Empleado: " + empleado.getNombre() + " " + empleado.getApellidos());
		model.addAttribute("empleado", empleado);
		return "empleados/detalle";
	}
	
	@GetMapping("/empleado/formulario")
	public String formulario(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		return "empleados/formulario";
	}
	
	@PostMapping("/empleado/formulario")
	public String registrar(@Valid Empleado empleado, BindingResult bindingResult, SessionStatus sessionStatus,@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "empleados/formulario";
		}
		
		if (!file.isEmpty()) {
			Path carpeta = Paths.get("src//main//resources//static//images");
			String raiz = carpeta.toFile().getAbsolutePath();
			Path rutaFinal = Paths.get(raiz + "//" + file.getOriginalFilename());
			try {
				Files.write(rutaFinal, file.getBytes());
				empleado.setFoto(file.getOriginalFilename());
				redirectAttributes.addFlashAttribute("info", "!Imagen subida exitosamente¡");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		iServiceApp.guardarEmpleado(empleado);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("info", "¡Empleado registrado con éxito!");
		return "redirect:/empleado/listar";
	}
	
	@GetMapping("/empleado/formulario/{id}")
	public String editar(@PathVariable Integer id, Model model ) {
		Empleado empleado = null;
		if (id > 0) {
			empleado = iServiceApp.buscarEmpleadoId(id);
		}else {
			return "redirect:/empleado/listar";
		}
		model.addAttribute("empleado", empleado);
		return "empleados/formulario";
	}
	
	@GetMapping("/empleado/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		iServiceApp.eliminarEmpleadoId(id);
		redirectAttributes.addFlashAttribute("warning", "¡Empleado eliminado exitosamente!");
		return "redirect:/empleado/listar";
	}
}
