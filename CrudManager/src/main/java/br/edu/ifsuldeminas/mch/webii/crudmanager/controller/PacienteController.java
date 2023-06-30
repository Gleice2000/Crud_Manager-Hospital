package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.PacienteRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Paciente;

@Controller
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@GetMapping("/pacientes")
	public String pacientes(Model model) {
		
		List<Paciente> pacientes = pacienteRepository.findAll();
		model.addAttribute("pacientes", pacientes);
		
		return "indexPaciente";
	}
	

	@GetMapping("")
	public String index(Model model) {
		
		List<Paciente> pacientes = pacienteRepository.findAll();
		model.addAttribute("pacientes", pacientes);
		
		return "indexPaciente";
	}
	
	
	
	@GetMapping("/pacientes/form")
	public String pacienteForm(@ModelAttribute("paciente") Paciente paciente) {
		return "paciente_form";
	}
	
	@PostMapping("/pacientes/new")
	public String pacienteNew(@ModelAttribute("paciente=") Paciente paciente) {
		
		pacienteRepository.save(paciente);
		return "redirect:/pacientes";
	}
	
	@GetMapping("/pacientes/update/{id}")
	public String pacienteUpdate(@PathVariable("id") Integer id, Model model) {
		
		Optional<Paciente> optPaciente = pacienteRepository.findById(id);
		
		if(!optPaciente.isPresent()) {
			//Gerar erro
		}
		
		Paciente paciente = optPaciente.get();
		
		model.addAttribute("paciente", paciente);
		
		return "paciente_form";
	}
	
	@GetMapping("/pacientes/delete/{id}")
	public String pacienteDelete(@PathVariable("id") Integer id, Model model) {
		
		Optional<Paciente> optPaciente = pacienteRepository.findById(id);
		
		if(!optPaciente.isPresent()) {
			//Gerar erro
		}
		
		Paciente paciente = optPaciente.get();
		
		pacienteRepository.delete(paciente);
		
		return "redirect:/pacientes";
	}
}
