package br.com.elo7.service;

import br.com.elo7.exceptions.AgendamentoServiceException;
import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.IConta;


public interface IAgendamentoTransferenciaService {

	public void agendarTransferencia(IConta conta, IAgendamentoTransferencia agendamentoTransferencia) throws AgendamentoServiceException;

}
