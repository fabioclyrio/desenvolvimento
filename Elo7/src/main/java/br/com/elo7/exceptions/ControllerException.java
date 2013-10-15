package br.com.elo7.exceptions;

public class ControllerException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public ControllerException(@SuppressWarnings("rawtypes") Class classe, String detailMessage) {
		super(classe, detailMessage);
	}

}
