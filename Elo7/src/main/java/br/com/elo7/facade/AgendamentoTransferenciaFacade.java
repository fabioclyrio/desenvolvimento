package br.com.elo7.facade;

import br.com.elo7.exceptions.FacadeException;
import br.com.elo7.form.TransferenciaForm;

public interface AgendamentoTransferenciaFacade {

	void agendarTransferencia(TransferenciaForm transferenciaForm) throws FacadeException;
	
}
