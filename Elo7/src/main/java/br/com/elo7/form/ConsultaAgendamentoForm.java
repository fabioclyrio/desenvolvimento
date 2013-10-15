package br.com.elo7.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ConsultaAgendamentoForm {

	@NotEmpty @Size(min=7, max=7)
	private String numeroConta;

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

}
