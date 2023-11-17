package com.projeto.senac.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.senac.model.Aluno;
import com.projeto.senac.repository.AlunoRepository;

@Service
public class ServiceAluno {

	@Autowired
	private AlunoRepository alunoRepository;
	
	public String gerarMatricula(int id) {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int ano = cal.get(Calendar.YEAR);
		String txt = "SENAC";
		return (txt + ano + (id + 1 )); // SENAC2023x
		
	}// Fim Gerador de Matricula
	
	public String cadastrarAluno(Aluno aluno) {
		// Verificar se existe um aluno com o mesmo CPF
		Aluno alunoExistente = alunoRepository.findbyCpf(aluno.getCpf());
		if(alunoExistente == null) {
			Aluno aux = alunoRepository.findLastInsertedAluno();
			if(aux == null) { // Primeiro Aluno a ser gravado no BD
				aluno.setMatricula(this.gerarMatricula(0));
				
			}
			else { // Já possui aluno gravado no BD
				aluno.setMatricula(this.gerarMatricula(Integer.parseInt(aux.getId().toString())));
				
			}
			alunoRepository.save(aluno);
			
		}// if alunoExistence == null
		else { //Existe um aluno já com o mesmo cpf
			return "Já existe um Aluno cadastrado com o mesmo CPF!";
			
		}
		
		return null;
	}// Fim cadastrar Aluno
	
	
	public String alterarAluno(Aluno aluno, Long id) {
		// Verificar se existe um aluno com o mesmo CPF
		Aluno alunoExistente = alunoRepository.findbyCpf(aluno.getCpf());
			if((alunoExistente != null && alunoExistente.getId() == id) || alunoExistente == null) { 
				alunoRepository.save(aluno);
				
			}
			else { // Já possui aluno gravado no BD
				return "Já existe um Aluno cadastrado com o mesmo CPF!";
				
			}
			
			return null; // Ocorreu tudo bem e foi atualizado a base de dados	
			
		}// Fim cadastrar Aluno
		
	
}
