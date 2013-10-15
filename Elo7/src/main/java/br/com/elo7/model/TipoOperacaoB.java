package br.com.elo7.model;

import java.util.Date;

import br.com.elo7.interfaces.ITipoOperacaoTransferencia;
import br.com.elo7.utils.DataUtils;

public class TipoOperacaoB implements ITipoOperacaoTransferencia {

	@Override
	public Dinheiro calcularTaxaTransferencia(AgendamentoTransferencia transferencia) {
		Dinheiro valorTransferencia = transferencia.getValor();
		Date data30 = DataUtils.adicionarData(30);
		if(transferencia.getDataAgendamento().before(data30)) {  
			transferencia.getTaxas().setValorTaxa((Dinheiro.somarValor(new Dinheiro("10.0"), valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else {
			transferencia.getTaxas().setValorTaxa((Dinheiro.somarValor(new Dinheiro("8.0"), valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		}
	}

	
}
