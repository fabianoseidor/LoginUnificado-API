package br.com.portalloginunificado.util;

public class ExceptionCustomizada extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExceptionCustomizada(String msgErro) {
		super(msgErro);
	}
}
