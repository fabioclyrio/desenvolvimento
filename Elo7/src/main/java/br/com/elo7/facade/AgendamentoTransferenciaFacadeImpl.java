package br.com.elo7.facade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.elo7.exceptions.AgendamentoServiceException;
import br.com.elo7.exceptions.FacadeException;
import br.com.elo7.exceptions.ServiceException;
import br.com.elo7.factory.TipoOperacaoFactory;
import br.com.elo7.form.TransferenciaForm;
import br.com.elo7.interfaces.IConta;
import br.com.elo7.interfaces.ITipoOperacaoTransferencia;
import br.com.elo7.model.AgendamentoTransferencia;
import br.com.elo7.model.Conta;
import br.com.elo7.model.Dinheiro;
import br.com.elo7.service.IAgendamentoTransferenciaService;
import br.com.elo7.service.IContaService;
import br.com.elo7.utils.DataUtils;

@Service("agendamentoTransferenciaFacade")
@Transactional
public class AgendamentoTransferenciaFacadeImpl implements AgendamentoTransferenciaFacade {

	protected static Logger logger = Logger.getLogger(AgendamentoTransferenciaFacadeImpl.class);
	
	@Autowired
	IAgendamentoTransferenciaService agendamento;

	@Autowired
	IContaService contaService;

	@Override
	public void agendarTransferencia(TransferenciaForm transferenciaForm) throws FacadeException {
		try {
			
			IConta contaOrigem = null;
			IConta contaDestino = null;
			
			contaOrigem = contaService.obterContaPorNumero(transferenciaForm.getContaOrigem());
			contaDestino = contaService.obterContaPorNumero(transferenciaForm.getContaDestino());
			
			if(contaOrigem != null && contaDestino != null){
				AgendamentoTransferencia transferencia = new AgendamentoTransferencia();
		
				transferencia.setOrigem((Conta)contaOrigem);
				transferencia.setDestino((Conta)contaDestino);
				transferencia.setDataAgendamento(DataUtils.formatarDataString(transferenciaForm.getDataAgendamento()));
				transferencia.setValor(new Dinheiro(transferenciaForm.getValorTransferencia()));
				
				ITipoOperacaoTransferencia tipoOperacao = TipoOperacaoFactory.getTipoOperacao(transferenciaForm.getTipoOperacao(), transferencia);
				transferencia.setOperacaoTransferencia(tipoOperacao);
		
				agendamento.agendarTransferencia(contaOrigem, transferencia);
			} else {
				logger.error("AgendamentoTransferenciaFacadeImpl: agendarTransferencia: Null: Origem - Destino ");
				throw new FacadeException(AgendamentoTransferenciaFacadeImpl.class, "falha agendarTransferencia: ");
			}
		
		} catch (ServiceException e) {
			logger.error("AgendamentoTransferenciaFacadeImpl: agendarTransferencia: ServiceException: " + e.getMessage());
			throw new FacadeException(AgendamentoTransferenciaFacadeImpl.class, "falha agendarTransferencia: " + e.getMessage());
		
		} catch (AgendamentoServiceException e) {
			logger.error("AgendamentoTransferenciaFacadeImpl: agendarTransferencia: AgendamentoServiceException: " + e.getMessage());
			throw new FacadeException(AgendamentoTransferenciaFacadeImpl.class, "falha agendarTransferencia: " + e.getMessage());
		}
	}
	

}
