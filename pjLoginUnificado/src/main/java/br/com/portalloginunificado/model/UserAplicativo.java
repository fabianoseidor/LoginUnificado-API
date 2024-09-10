package br.com.portalloginunificado.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "USER_APLICATIVO")
@SequenceGenerator(name = "seq_user_aplicativo", sequenceName = "seq_user_aplicativo", allocationSize = 1, initialValue = 1)
public class UserAplicativo implements Serializable{

	private static final long serialVersionUID = -3136664057017268896L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_user_aplicativo" )
	@Column(name = "ID_USER_APLICATIVO")
	private Long id_user_aplicativo;
	
	@Column( name = "ID_PERFIL" )
	private Long   id_perfil;
	
	@Column( name = "ADMIN" )
	private Boolean admin = Boolean.FALSE;
	
	@Column( name = "APROVADOR" )
	private Boolean aprovador = Boolean.FALSE;;
	
	@Column( name = "EXECUTOR" )
	private Boolean executor = Boolean.FALSE;;
	
	@Column( name = "OWNER")
	private Boolean owner = Boolean.FALSE;;
	
	@Column( name = "DT_CRIACAO", length = 20, nullable = false )
    private String dt_criacao;
	
	@ManyToOne(targetEntity = UsersLogin.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_users", referencedColumnName = "id_users", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_USER_USERAPLICATIVO"))
	private UsersLogin usersLogin;
		
	@ManyToOne(targetEntity = Aplicacao.class)
	@JoinColumn( name = "id_aplicacao", referencedColumnName = "id_aplicacao", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_APLICACAO_USERAPLICATIVO") )
	private Aplicacao aplicacao;
	
	@PrePersist
	public void prePersist() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now)); //01/02/2019 14:08:43		
		dt_criacao = dtf.format(now);
	}

	public UsersLogin getUsers() {
		return usersLogin;
	}

	public Long getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(Long id_perfil) {
		this.id_perfil = id_perfil;
	}

	public void setUsers(UsersLogin usersLogin) {
		this.usersLogin = usersLogin;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Long getId_user_aplicativo() {
		return id_user_aplicativo;
	}

	public void setId_user_aplicativo(Long id_user_aplicativo) {
		this.id_user_aplicativo = id_user_aplicativo;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getAprovador() {
		return aprovador;
	}

	public void setAprovador(Boolean aprovador) {
		this.aprovador = aprovador;
	}

	public Boolean getExecutor() {
		return executor;
	}

	public void setExecutor(Boolean executor) {
		this.executor = executor;
	}

	public Boolean getOwner() {
		return owner;
	}

	public void setOwner(Boolean owner) {
		this.owner = owner;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	
}
