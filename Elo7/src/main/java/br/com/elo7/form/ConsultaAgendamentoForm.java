package br.com.elo7.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ConsultaAgendamentoForm {

	@NotEmpty
	private String numeroConta;

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

}
