package br.com.elo7.model;

import java.util.Date;

import br.com.elo7.interfaces.ITipoOperacaoTransferencia;
import br.com.elo7.utils.DataUtils;

public class TipoOperacaoC implements ITipoOperacaoTransferencia {

	@Override
	public Dinheiro calcularTaxaTransferencia(AgendamentoTransferencia transferencia) {
		Dinheiro valorTransferencia = transferencia.getValor();
		
		Date data30 = DataUtils.adicionarData(30);
		Date data25 = DataUtils.adicionarData(25);
		Date data20 = DataUtils.adicionarData(20);
		Date data15 = DataUtils.adicionarData(15);
		Date data10 = DataUtils.adicionarData(10);
		Date data5 = DataUtils.adicionarData(5);
		
		Date dataAgendamento = transferencia.getDataAgendamento();
		
		if(dataAgendamento.after(data30)) {  
			transferencia.getTaxas().setValorTaxa((Dinheiro.aplicarPorcentagem("0.012", valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else if(dataAgendamento.after(data25) && dataAgendamento.before(data25)){
			transferencia.getTaxas().setValorTaxa((Dinheiro.aplicarPorcentagem("0.021", valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else if(dataAgendamento.after(data20) && dataAgendamento.before(data25)){
			transferencia.getTaxas().setValorTaxa((Dinheiro.aplicarPorcentagem("0.043", valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else if(dataAgendamento.after(data15) && dataAgendamento.before(data20)){
			transferencia.getTaxas().setValorTaxa((Dinheiro.aplicarPorcentagem("0.054", valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else if(dataAgendamento.after(data10) && dataAgendamento.before(data15)){
			transferencia.getTaxas().setValorTaxa((Dinheiro.aplicarPorcentagem("0.067", valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else if(dataAgendamento.after(data5) && dataAgendamento.before(data10)){
			transferencia.getTaxas().setValorTaxa((Dinheiro.aplicarPorcentagem("0.083", valorTransferencia)));
			return transferencia.getTaxas().getTaxa();
		} else {
			return transferencia.getTaxas().getTaxa();
		}
	}

	
}
