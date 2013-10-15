package br.com.elo7.factory;

import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.ITipoOperacaoTransferencia;
import br.com.elo7.model.AgendamentoTransferencia;
import br.com.elo7.model.TipoOperacaoA;
import br.com.elo7.model.TipoOperacaoB;
import br.com.elo7.model.TipoOperacaoC;
import br.com.elo7.model.TipoOperacaoD;

public class TipoOperacaoFactory {

	public static ITipoOperacaoTransferencia getTipoOperacao(String tipo, AgendamentoTransferencia transferencia){
		if("A".equals(tipo)){
			transferencia.setTipoOperacao(IAgendamentoTransferencia.TipoOperacao.A);
			return new TipoOperacaoA();
		} else
		if("B".equals(tipo)){
			transferencia.setTipoOperacao(IAgendamentoTransferencia.TipoOperacao.B);
			return new TipoOperacaoB();
		} else
		if("C".equals(tipo)){
			transferencia.setTipoOperacao(IAgendamentoTransferencia.TipoOperacao.C);
			return new TipoOperacaoC();
		} else
		if("D".equals(tipo)){
			transferencia.setTipoOperacao(IAgendamentoTransferencia.TipoOperacao.D);
			return new TipoOperacaoD();
		} else {
			return null;
		}
	}
	
}
