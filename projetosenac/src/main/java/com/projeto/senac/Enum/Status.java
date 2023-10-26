package com.projeto.senac.Enum;

public enum Status {
	
	ATIVO("Ativo"),
	INVATIVO("Inativo"),
	TRANCADO("Trancado"),
	CANCELADO("Cancelado");
	
	private String status;
	private Status(String status) {
		this.status = status;
		
	}

}
