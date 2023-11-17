package com.projeto.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.senac.model.Professor;
import com.projeto.senac.repository.ProfessorRepository;

@Service
public class ServiceProfessor {
	
	@Autowired
	ProfessorRepository professorRepository;
	
	public String cadastrarProfessor(Professor professor) {
		
		// Verificar se existe um cpf na tabela professor
		Professor profExiste = professorRepository.findbyCpf(professor.getCpf());
		if(profExiste != null) {
			return "Já existe um professor cadastrado com o CPF Informado!";
			
		} else {
			professorRepository.save(professor);
			
		}
		return null;
		
	}
	
	public String alterarProfessor(Professor professor, Long id) {
		Professor professorExiste = professorRepository.findbyCpf(professor.getCpf());
			if((professorExiste != null && professorExiste.getId() == id) || professorExiste == null) {
				professorRepository.save(professor);
				
			} else {
				return "Já existe um professor cadastrado com o CPF Informado!";
				
			}
			return null;
			
	}

}
