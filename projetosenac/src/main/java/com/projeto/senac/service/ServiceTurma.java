package com.projeto.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.senac.model.Turma;
import com.projeto.senac.repository.TurmaRepository;

@Service
public class ServiceTurma {
	
	@Autowired
	TurmaRepository turmaRepository;
	
	public String cadastrarTurma(Turma turma) {
		Turma objTurma = turmaRepository.findByCodTurma(turma.getCodTurma());
		
		if(objTurma!=null) {
			return "Já existe uma turma cadastra com esse código!!";
			
		}
		else {
			return null;
					
		}
		
	}// fim cadastrarTurma

}
