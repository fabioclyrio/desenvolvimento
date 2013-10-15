package br.com.elo7.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.elo7.service.IContaService;
import br.com.elo7.utils.DbUtils;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	IContaService contaService;
	
    @RequestMapping
    public String getIndexPage(HttpServletRequest request) {
    	DbUtils.verificaBancoInicializado(request, contaService);
        return "index";
    }
    
}
