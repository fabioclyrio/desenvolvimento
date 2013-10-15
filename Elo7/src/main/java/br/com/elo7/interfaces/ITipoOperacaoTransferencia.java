package br.com.elo7.interfaces;

import br.com.elo7.model.AgendamentoTransferencia;
import br.com.elo7.model.Dinheiro;

public interface ITipoOperacaoTransferencia {

	Dinheiro calcularTaxaTransferencia(AgendamentoTransferencia transferencia);
	
}
