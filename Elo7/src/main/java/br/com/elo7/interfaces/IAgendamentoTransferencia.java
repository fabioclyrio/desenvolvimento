package br.com.elo7.interfaces;

import br.com.elo7.exceptions.SaldoInsuficienteException;
import br.com.elo7.exceptions.ValorInsuficienteException;


public interface IAgendamentoTransferencia {

	enum TipoOperacao {
		A, B, C, D
	}
	
	public void aplicarTaxaTransferencia() throws ValorInsuficienteException, SaldoInsuficienteException;

	public void setTipoOperacao(TipoOperacao tipoOperacao);
	
}
