package br.com.elo7.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.elo7.exceptions.ServiceException;
import br.com.elo7.model.Conta;
import br.com.elo7.model.Dinheiro;
import br.com.elo7.service.IContaService;

public class DbUtils {

	protected static Logger logger = Logger.getLogger(DbUtils.class);
	
	private DbUtils(){
		
	}
	
	public static void verificaBancoInicializado(HttpServletRequest request, IContaService contaService) {
		if(request.getSession().getAttribute("hsqldbInicializado") == null) {
			try {
				inserirValoresPadrao(request, contaService);
			} catch (ServiceException e) {
				logger.error("DbUtils: verificaBancoInicializado: " + e.getMessage());
			}
		}
	}
	
	private static void inserirValoresPadrao(HttpServletRequest request, IContaService contaService) throws ServiceException {
		
		// inicializa as contas para o exemplo
		Conta contaA = new Conta();
		Conta contaB = new Conta();
		Conta contaC = new Conta();
		Conta contaD = new Conta();

		// numero das contas
		contaA.setNumeroDaConta("12345-1");
		contaB.setNumeroDaConta("12345-2");
		contaC.setNumeroDaConta("12345-3");
		contaD.setNumeroDaConta("12345-4");

		// saldo inicial
		contaA.depositarValor(new Dinheiro("1200.00"));
		contaB.depositarValor(new Dinheiro("2400.00"));
		contaC.depositarValor(new Dinheiro("3600.00"));
		contaD.depositarValor(new Dinheiro("4800.00"));

		// persiste os dados de exemplo
		try {
			if(contaService.listaTodasContas().isEmpty()){
				contaService.salvarConta(contaA);
				contaService.salvarConta(contaB);
				contaService.salvarConta(contaC);
				contaService.salvarConta(contaD);
			}
			request.getSession().setAttribute("hsqldbInicializado", true);
		} catch (ServiceException e) {
			logger.error("DbUtils: inserirValoresPadrao: " + e.getMessage());
		}

	}

}
