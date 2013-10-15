package br.com.elo7.model;

import br.com.elo7.interfaces.ITipoOperacaoTransferencia;

public class TipoOperacaoA implements ITipoOperacaoTransferencia {

	@Override
	public Dinheiro calcularTaxaTransferencia(AgendamentoTransferencia transferencia) {
		Dinheiro valorTransferencia = transferencia.getValor();
		Dinheiro valorPorcetagem = new Dinheiro(Dinheiro.aplicarPorcentagem("0.03", valorTransferencia));
		transferencia.getTaxas().setValorTaxa(Dinheiro.somarValor(new Dinheiro("2.00"), valorPorcetagem));
		return transferencia.getTaxas().getTaxa();
	}
}
