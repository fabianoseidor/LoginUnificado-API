package br.com.portalloginunificado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalloginunificado.model.Pessoa;
import br.com.portalloginunificado.model.UsersLogin;
import br.com.portalloginunificado.model.dto.UsersDTO;

@Repository
@Transactional
public interface UsersLoginRepository extends JpaRepository<UsersLogin, Long>{

	@Query(value = "SELECT u.* FROM Users u where login = ?1", nativeQuery = true)
	UsersLogin findPorLogin(String login);
	
	@Query(value = "SELECT u.* FROM Users u where login = ?1 and passoword = ?2", nativeQuery = true)
	UsersLogin findLoginPass(String login, String passoword);

	@Modifying(flushAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE users SET passoword = ?2 WHERE login = ?1")
	void updatePassoword( String login, String pass );
	
	@Modifying(flushAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE pessoa SET primeiro_acesso = 0 WHERE id_pessoa = ?1")
	void updatePrimeiroAcesso( Long idPessoa );

	@Query(value = "SELECT u FROM UsersLogin u where id_users = ?1")
	UsersLogin findByIdUsers(Long id_users);
	
	@Query(value = "SELECT u FROM UsersLogin u where pessoa = ?1")
	UsersLogin findByPessoa(Pessoa pessoa);
	
	@Query(value = "SELECT u FROM UsersLogin u where login like '%?1%'")
	List<UsersLogin> findLikeLogin(String login);
	
	@Query(value = "SELECT u FROM UsersLogin u")
	List<UsersLogin> findLitaLogin( );

	@Query(value = "select new br.com.portalloginunificado.model.dto.UsersDTO( "
			     + "    id_users                                               "
			     + "  , login                                                  "
			     + "  , passoword                                              "
			     + "  , role )                                                 "
			     + " FROM UsersLogin WHERE LOGIN = ?1                          ")
	UsersDTO findPorLoginDTO(String login);
	

}



