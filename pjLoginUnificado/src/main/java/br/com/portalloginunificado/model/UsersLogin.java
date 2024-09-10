package br.com.portalloginunificado.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.portalloginunificado.enums.UserRole;

@Entity
@Table(name = "USERS")
@SequenceGenerator( name = "seq_user", sequenceName = "seq_user", allocationSize = 1, initialValue = 1 )
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id_users")
public class UsersLogin implements Serializable{

	private static final long serialVersionUID = -4395798630208505815L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	@Column(name = "ID_USERS")
	private Long id_users;
	
	@NotEmpty(message = "Informe o Login")
	@Column( name = "LOGIN", length = 20, nullable = false )
	private String login;
	
	@NotEmpty(message = "Informe informe a Senha")
//	@Min(value = 6, message = "A Senha deve ser pelo menos 6")
//	@Max(value = 20, message = "A nota deve ser pelo menos 20")
    @Column( name = "PASSOWORD", length = 100, nullable = false )
	private String passoword;
	
	@Column( name = "ROLE", length = 20/*, nullable = false*/ )
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column( name = "LOGIN_CADASTRO", length = 20/*, nullable = false*/ )
	private String login_cadastro;
	
	@Column(name = "DT_CRIACAO", length = 20, nullable = false)
    private String dt_criacao;
	
	@OneToOne(targetEntity = Pessoa.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "user_pessoa_fk"))	
	private Pessoa pessoa;
	
	@OneToMany(mappedBy = "usersLogin", orphanRemoval = true, cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<UserAplicativo> userAplicativos = new ArrayList<UserAplicativo>();
	
	
	@PrePersist
	public void prePersist() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now)); //01/02/2019 14:08:43		
		dt_criacao = dtf.format(now);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public String getLogin_cadastro() {
		return login_cadastro;
	}

	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<UserAplicativo> getUserAplicativos() {
		return userAplicativos;
	}


	public void setUserAplicativos(List<UserAplicativo> userAplicativos) {
		this.userAplicativos = userAplicativos;
	}


	public Long getId_users() {
		return id_users;
	}


	public void setId_users(Long id_users) {
		this.id_users = id_users;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassoword() {
		return passoword;
	}

	public void setPassoword(String passoword) {
		this.passoword = passoword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

} 
