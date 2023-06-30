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

import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.*;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.*;

@Controller
public class ConsultaController {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@GetMapping("/consultas")
	public String consultas(Model model) {
		
		List<Consulta> consultas = consultaRepository.findAll();
		model.addAttribute("consultas", consultas);
		
		return "indexConsultas";
	}
	
	@GetMapping("/consultas/form")
	public String consultaForm(Model model, @ModelAttribute("consulta") Consulta consulta) {
		List<Paciente> pacientes = pacienteRepository.findAll();
		model.addAttribute("pacientes",pacientes);
		return "consulta_form";
	}
	
	@PostMapping("/consultas/new")
	public String consultaNew(@ModelAttribute("consulta=") Consulta consulta) {
		
		consultaRepository.save(consulta);
		return "redirect:/consultas";
	}
	
	@GetMapping("/consultas/update/{id}")
	public String consultaUpdate(@PathVariable("id") Integer id, Model model) {
		
		Optional<Consulta> optConsulta = consultaRepository.findById(id);
		
		if(!optConsulta.isPresent()) {
			//Gerar erro
		}
		
		Consulta consulta = optConsulta.get();
		
		model.addAttribute("consulta", consulta);
		
		List<Paciente> pacientes = pacienteRepository.findAll();
		model.addAttribute("pacientes",pacientes);
		
		return "consulta_form";
	}
	
	@GetMapping("/consultas/delete/{id}")
	public String consultaDelete(@PathVariable("id") Integer id, Model model) {
		
		Optional<Consulta> optConsulta = consultaRepository.findById(id);
		
		if(!optConsulta.isPresent()) {
			//Gerar erro
		}
		
		Consulta consulta = optConsulta.get();
		
		consultaRepository.delete(consulta);
		
		return "redirect:/consultas";
	}
}
