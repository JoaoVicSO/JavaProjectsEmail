package com.projeto.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.projeto.senac.model.Aluno;
import com.projeto.senac.repository.AlunoRepository;
import com.projeto.senac.service.ServiceAluno;

@Controller
public class AlunoController {

	@Autowired
	ServiceAluno serviceAluno;
	
	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping("/InserirAluno")
	public ModelAndView insertAluno() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("aluno", new Aluno());
		mv.setViewName("Aluno/InserirAluno");
		return mv;
	}

	@GetMapping("listarAlunos")
	public ModelAndView listarAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		mv.setViewName("Aluno/listarAlunos");
		return mv;
	}
	
	@PostMapping("/inserirAluno")
	public ModelAndView inserir(Aluno aluno) {
		ModelAndView mv = new ModelAndView();

		String out = serviceAluno.cadastrarAluno(aluno);
		if (out != null) {// Existe um aluno com o mesmo CPF

			mv.addObject("aluno", new Aluno());
			mv.addObject("msg", out);
			mv.setViewName("Aluno/InserirAluno");

		} else {
			mv.setViewName("redirect:/listarAlunos");
			
		}
		return mv;
	}	
}
