package br.com.elo7.model;

import br.com.elo7.interfaces.ITipoOperacaoTransferencia;

public class TipoOperacaoD implements ITipoOperacaoTransferencia {

	@Override
	public Dinheiro calcularTaxaTransferencia(AgendamentoTransferencia transferencia) {
		Dinheiro valor25Mil = new Dinheiro("25.000");
		Dinheiro valor120Mil = new Dinheiro("120.000");
		
		if(transferencia.getValor().comparar(valor25Mil) == -1){
			TipoOperacaoA operacaoA = new TipoOperacaoA();
			return operacaoA.calcularTaxaTransferencia(transferencia);
		} else if(transferencia.getValor().comparar(valor25Mil) == 1 && transferencia.getValor().comparar(valor120Mil) == -1){
			TipoOperacaoC operacaoC = new TipoOperacaoC();
			return operacaoC.calcularTaxaTransferencia(transferencia);
		} else {
			TipoOperacaoD operacaoD = new TipoOperacaoD();
			return operacaoD.calcularTaxaTransferencia(transferencia);
		}
		
	}

	
}
