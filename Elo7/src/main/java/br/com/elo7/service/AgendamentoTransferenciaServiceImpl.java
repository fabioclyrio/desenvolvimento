package br.com.elo7.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.elo7.exceptions.AgendamentoServiceException;
import br.com.elo7.exceptions.ServiceException;
import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.IConta;

@Service("agendamentoTransferenciaService")
@Transactional
public class AgendamentoTransferenciaServiceImpl implements IAgendamentoTransferenciaService {

	protected static Logger logger = Logger.getLogger(AgendamentoTransferenciaServiceImpl.class);
	
	@Autowired
	IContaService contaService;

	@Override
	public void agendarTransferencia(IConta conta, IAgendamentoTransferencia agendamentoTransferencia) throws AgendamentoServiceException {
		try {
			agendamentoTransferencia.aplicarTaxaTransferencia();
			conta.adicionarAgendamentoTransferencia(agendamentoTransferencia);
			contaService.atualizarConta(conta);
		} catch (ServiceException e) {
			logger.error("AgendamentoTransferenciaServiceImpl: aplicarTaxasTransferencia: " + e.getMessage());
			throw new AgendamentoServiceException(AgendamentoTransferenciaServiceImpl.class, "falha aplicarTaxasTransferencia: " + e.getMessage());
		}
	}

}
