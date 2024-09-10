package br.com.portalloginunificado.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "PESSOA")
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = -5062804212430073949L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	@Column(name = "ID_PESSOA")
	private Long id_pessoa;
	
	@NotEmpty(message = "Informe informe o nome da Pessoa")
	@Column(name = "NOME_PESSOA", length = 50 ,nullable = false)
	private String nome_pessoa;
	
	@NotEmpty(message = "Informe informe o E-mail")
	@Column(name = "EMAIL", unique=true, length = 50 ,nullable = false)
	private String email;
	
	@Column(name = "FOTOUSER", unique=true, length = 50 ,columnDefinition = "TEXT")
	private String fotouser;
	
	@Column(name = "PRIMEIRO_ACESSO")
	private Boolean primeiroAcesso;	
	
	@NotEmpty(message = "Informe informe o CPF")
	@Column(name = "CPF", length = 14 )
	private String cpf;
	
	@Column(name = "LOGRADOURO", length = 200)
	private String logradouro;
	
	@Column(name = "COMPLEMENTO", length = 200)
	private String complemento;
	
	@Column(name = "NUMERO", length = 10)
	private String numero;
	
	@Column(name = "BAIRRO", length = 200)
	private String bairro;
	
	@Column(name = "CIDADE", length = 200)
	private String cidade;
	
	@Column(name = "CEP", length = 200 )
	private String cep;
	
	@PrePersist
	public void prePersist() {
		
		primeiroAcesso = true;
	}
	
	public String getFotouser() {
		return fotouser;
	}


	public void setFotouser(String fotouser) {
		this.fotouser = fotouser;
	}


	public Boolean getPrimeiroAcesso() {
		return primeiroAcesso;
	}


	public void setPrimeiroAcesso(Boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}


	public Long getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getNome_pessoa() {
		return nome_pessoa;
	}

	public void setNome_pessoa(String nome_pessoa) {
		this.nome_pessoa = nome_pessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}	
	
}
