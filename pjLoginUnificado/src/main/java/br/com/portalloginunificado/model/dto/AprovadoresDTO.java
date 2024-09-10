package br.com.portalloginunificado.model.dto;

public class AprovadoresDTO {
	private Long idAprovadores;
	private String aprovador;
	private String email;
	private String login_aprovador;
	private String obs;
	public Long getIdAprovadores() {
		return idAprovadores;
	}
	public void setIdAprovadores(Long idAprovadores) {
		this.idAprovadores = idAprovadores;
	}
	public String getAprovador() {
		return aprovador;
	}
	public void setAprovador(String aprovador) {
		this.aprovador = aprovador;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin_aprovador() {
		return login_aprovador;
	}
	public void setLogin_aprovador(String login_aprovador) {
		this.login_aprovador = login_aprovador;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	@Override
	public String toString() {
		return "AprovadoresDTO [idAprovadores=" + idAprovadores + ", aprovador=" + aprovador + ", email=" + email
				+ ", login_aprovador=" + login_aprovador + ", obs=" + obs + "]";
	}
	
	

}
