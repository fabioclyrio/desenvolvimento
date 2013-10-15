package br.com.elo7.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TransferenciaForm {

	@NotEmpty @Size(min=10, max=10)
	private String dataAgendamento;

	@NotEmpty @Size(min=4, max=21)
	private String valorTransferencia;

	@NotEmpty @Size(min=7, max=7)
	private String contaOrigem;

	@NotEmpty @Size(min=7, max=7)
	private String contaDestino;

	@NotEmpty @Size(min=1, max=1)
	private String tipoOperacao;

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getValorTransferencia() {
		return valorTransferencia;
	}

	public void setValorTransferencia(String valorTransferencia) {
		this.valorTransferencia = valorTransferencia.replace(".", "").replace(",", ".");
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

}
