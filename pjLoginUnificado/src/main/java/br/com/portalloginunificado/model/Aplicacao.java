package br.com.portalloginunificado.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "APLICACAO")
@SequenceGenerator(name = "seq_aplicacao", sequenceName = "seq_aplicacao", allocationSize = 1, initialValue = 1)
public class Aplicacao implements Serializable{

	private static final long serialVersionUID = -7771435642889660801L; 

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aplicacao")
	@Column(name = "ID_APLICACAO")
	private Long id_aplicacao;
	
	@Column(name = "APLICACAO", nullable = false, length = 30, unique = true)
	private String aplicacao;
	
	@Column(name = "URL", length = 500)
	private String url;
	
	@Column(name = "DEPARTAMENTO", length = 200)
	private String departamento;
	
	@Column(name = "DESENVOLVEDOR")
	private String desenvolvedor;
	
	@Column(name = "DT_CRIACAO", length = 20, nullable = false)
    private String dt_criacao;
	
	@Column(name = "DESCRICAO", columnDefinition = "text")
	private String descricao;
	
	@PrePersist
	public void prePersist() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now)); //01/02/2019 14:08:43		
		dt_criacao = dtf.format(now);
	}

	public Long getId_aplicacao() {
		return id_aplicacao;
	}

	public void setId_aplicacao(Long id_aplicacao) {
		this.id_aplicacao = id_aplicacao;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
