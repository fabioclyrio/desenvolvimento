package br.com.elo7.exceptions;

public class ValorInsuficienteException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public ValorInsuficienteException(@SuppressWarnings("rawtypes") Class classe, String detailMessage) {
		super(classe, detailMessage);
	}
}
