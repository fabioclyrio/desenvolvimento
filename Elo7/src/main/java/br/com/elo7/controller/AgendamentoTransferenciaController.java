package br.com.elo7.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.elo7.exceptions.ControllerException;
import br.com.elo7.exceptions.FacadeException;
import br.com.elo7.facade.AgendamentoTransferenciaFacade;
import br.com.elo7.form.TransferenciaForm;
import br.com.elo7.service.IContaService;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoTransferenciaController {

	protected static Logger logger = Logger.getLogger(AgendamentoTransferenciaController.class);
	
	@Autowired
	AgendamentoTransferenciaFacade agendamento;

	@Autowired
	IContaService contaService;

	@RequestMapping("/layout")
	public String getPaginaParcial(HttpServletRequest request) {
		return "agendamento/layout";
	}

	@RequestMapping(value = "/salvarAgendamento", method = RequestMethod.POST)
	public @ResponseBody
	void agendarTransferencia(HttpServletResponse response,	@Valid @RequestBody TransferenciaForm transferenciaForm) throws ControllerException {
		try {
			 agendamento.agendarTransferencia(transferenciaForm); 
		} catch (FacadeException e) {
			logger.error("AgendamentoTransferenciaController: agendarTransferencia: " + e.getMessage());
			throw new ControllerException(AgendamentoTransferenciaController.class, "falha agendarTransferencia: " + e.getMessage());
		}
	}
	
}
