package br.com.elo7.service;

import java.util.List;

import br.com.elo7.exceptions.ServiceException;
import br.com.elo7.interfaces.IConta;
import br.com.elo7.model.AgendamentoTransferencia;

public interface IContaService {

	public void salvarConta(IConta conta) throws ServiceException;
	
	public void atualizarConta(IConta conta) throws ServiceException;
	
	public void salvarAgendamento(AgendamentoTransferencia agendamento) throws ServiceException;

	public IConta obterContaPorNumero(String numero) throws ServiceException;

	public List<IConta> listaTodasContas() throws ServiceException;
	
}
