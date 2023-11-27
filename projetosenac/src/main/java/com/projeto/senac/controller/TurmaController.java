package com.projeto.senac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.senac.Enum.Status;
import com.projeto.senac.model.Aluno;
import com.projeto.senac.model.Professor;
import com.projeto.senac.model.Turma;
import com.projeto.senac.repository.AlunoRepository;
import com.projeto.senac.repository.ProfessorRepository;
import com.projeto.senac.repository.TurmaRepository;
import com.projeto.senac.service.ServiceTurma;

@Controller
public class TurmaController {
	
	@Autowired
	ProfessorRepository professorRepository;	
	
	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	ServiceTurma serviceTurma;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	// Cadastrar Professor
	
	@GetMapping("/carregarTurma")
	public ModelAndView carregarTurma() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("professores", professorRepository.findAllOrderedById());
		mv.addObject("turma", new Turma());
		mv.setViewName("Turma/inserirTurma");
		return mv;
		
	}// fim carregarTurma
	
	@PostMapping("/inserirTurma")
	public ModelAndView carregarTurma(Turma turma) {
		ModelAndView mv = new ModelAndView();
		String out = serviceTurma.cadastrarTurma(turma);
		if(out!=null) {
			mv.addObject("professores", professorRepository.findAllOrderedById());
			mv.addObject("turma", new Turma());
			mv.setViewName("Turma/inserirTurma");
			
		} // fim if
		
		else {
			List<Aluno> alunos = alunoRepository.findByTurnoAndCursoAndStatus(turma.getTurno(), turma.getCurso(), Status.ATIVO);
			Professor professor = professorRepository.findById(turma.getProfessor().getId()).get();
			turma.setProfessor(professor);
			mv.addObject("alunos", alunos);
			turma.setAlunos(alunos);
			mv.addObject("turma", turma);
			mv.setViewName("Aluno/inserirAlunosTurma");
			
		}// fim else
		
		return mv;
		
	}// fim post
	
	// Read
	
	@GetMapping("/listarTurmas")
	public ModelAndView listarTurma() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("turmas", turmaRepository.findAllOrderedById());
		mv.setViewName("Turma/listarTurmas");
		return mv;
		
	}// fim listarTurma
	
	
	
	@PostMapping("/inserirAlunosTurma")
	public ModelAndView inserirAlunosTurma(Turma turma) {
		ModelAndView mv = new ModelAndView();
		turmaRepository.save(turma);
		mv.setViewName("redirect:/Home");
		return mv;
		
	}

}
