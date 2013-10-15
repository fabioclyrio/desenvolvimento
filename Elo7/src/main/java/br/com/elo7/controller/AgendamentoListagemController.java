package br.com.elo7.controller;

import java.io.IOException;
import java.util.List;

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
import br.com.elo7.exceptions.ServiceException;
import br.com.elo7.form.ConsultaAgendamentoForm;
import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.IConta;
import br.com.elo7.service.IContaService;

@Controller
@RequestMapping("/agendamentolistagem")
public class AgendamentoListagemController {

	protected static Logger logger = Logger.getLogger(AgendamentoListagemController.class);

	@Autowired
	IContaService contaService;

	@RequestMapping("/layout")
	public String getPaginaParcial(HttpServletRequest request) {
		return "agendamentolistagem/layout";
	}

	@RequestMapping(value = "/listaAgendamentos", method = RequestMethod.POST)
	public @ResponseBody
	List<IAgendamentoTransferencia> getListaAgendamentos(HttpServletResponse response, @Valid @RequestBody ConsultaAgendamentoForm consultaAgendamentoForm)
			throws ControllerException, IOException {
		try {
			IConta conta = contaService.obterContaPorNumero(consultaAgendamentoForm.getNumeroConta());
			if (!conta.verificarAgendamentosTransferencias().isEmpty()) {
				return conta.verificarAgendamentosTransferencias();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "agendamentos não encontrado!");
				return null;
			}
		} catch (ServiceException e) {
			logger.error("AgendamentoTransferenciaController: agendarTransferencia: " + e.getMessage());
			throw new ControllerException(AgendamentoListagemController.class, "falha agendarTransferencia: " + e.getMessage());
		}
	}
}
