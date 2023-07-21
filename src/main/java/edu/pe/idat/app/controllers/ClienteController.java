package edu.pe.idat.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.entity.Cliente;
import edu.pe.idat.app.models.service.IServiceApp;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IServiceApp iServiceApp;

	@GetMapping("/cliente/listar")
	public String listar(Model model,@Param("palabraClave")String palabraClave) {
		model.addAttribute("clientes", iServiceApp.listarClientes(palabraClave));
		model.addAttribute("palabraClave",palabraClave);
		return "clientes/listar";
	}

	@GetMapping("/cliente/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Cliente cliente = iServiceApp.buscarClienteId(id);
		if (cliente == null) {
			redirectAttributes.addFlashAttribute("error", "¡Cliente no existe!");
			return "redirect:/cliente/listar";
		}
		model.addAttribute("titulo", "Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
		model.addAttribute("cliente", cliente);
		return "clientes/detalle";
	}

	@GetMapping("/cliente/formulario")
	public String formulario(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "clientes/formulario";
	}

	@PostMapping("/cliente/formulario")
	public String registrar(@Valid Cliente cliente, BindingResult bindingResult, SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "clientes/formulario";
		}
		iServiceApp.guardarCliente(cliente);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("info", "¡Cliente registrado con éxito!");
		return "redirect:/cliente/listar";
	}

	@GetMapping("/cliente/formulario/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = iServiceApp.buscarClienteId(id);
		} else {
			return "redirect:/cliente/listar";
		}
		model.addAttribute("cliente", cliente);
		return "clientes/formulario";
	}

	@GetMapping("/cliente/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		iServiceApp.eliminarClienteId(id);
		redirectAttributes.addFlashAttribute("error", "¡Cliente eliminado!");
		return "redirect:/cliente/listar";
	}

}
