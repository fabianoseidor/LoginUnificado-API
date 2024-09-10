package br.com.portalloginunificado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalloginunificado.model.Pessoa;

@Repository
@Transactional
public interface PessoalRepository extends JpaRepository<Pessoa, Long>{
	
	@Query(value = "SELECT u FROM Pessoa u where id_pessoa = ?1")
	Pessoa findByIdPessoa(Long id_pessoa);
	
	@Query(value = "SELECT u FROM Pessoa u where nome_pessoa like '%?1%'")
	List<Pessoa> findParteNomePessoa(String nome_pessoa);

	@Query(value = "SELECT u FROM Pessoa u where nome_pessoa = '?1'")
	List<Pessoa> findBayNomePessoa(String nome_pessoa);

	@Query(value = "SELECT u FROM Pessoa u where email = '?1'")
	List<Pessoa> findBayPessoaEmail(String email);

	@Query(value = "SELECT u FROM Pessoa u where cpf = '?1'")
	List<Pessoa> findBayPessoaCPF(String cpf);
	
	@Query(value = "SELECT u FROM Pessoa u where UPPER(email) = ?1")
	Pessoa findBayEmail(String email);


}
