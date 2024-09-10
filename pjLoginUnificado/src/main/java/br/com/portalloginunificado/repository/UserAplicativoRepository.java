package br.com.portalloginunificado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalloginunificado.model.Aplicacao;
import br.com.portalloginunificado.model.UserAplicativo;
import br.com.portalloginunificado.model.UsersLogin;

@Repository
@Transactional
public interface UserAplicativoRepository extends JpaRepository<UserAplicativo, Long>{

	@Query(value = "SELECT u FROM UserAplicativo u where aplicacao = ?1")
	List<UserAplicativo> findByAplicacao(Aplicacao aplicacao);
	
	@Query(value = "SELECT u FROM UserAplicativo u where id_user_aplicativo = ?1")
	List<UserAplicativo> findByIdUserAplicativo(Long id_user_aplicativo);
	
	@Query(value = "SELECT u FROM UserAplicativo u where users = ?1")
	List<UserAplicativo> findByUsers(UsersLogin usersLogin);
}
