package br.com.elo7.exceptions;

public class SaldoInsuficienteException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public SaldoInsuficienteException(@SuppressWarnings("rawtypes") Class classe, String detailMessage) {
		super(classe, detailMessage);
	}
}
