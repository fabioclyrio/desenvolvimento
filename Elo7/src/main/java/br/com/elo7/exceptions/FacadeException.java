package br.com.elo7.exceptions;

public class FacadeException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public FacadeException(@SuppressWarnings("rawtypes") Class classe, String detailMessage) {
		super(classe, detailMessage);
	}

}
