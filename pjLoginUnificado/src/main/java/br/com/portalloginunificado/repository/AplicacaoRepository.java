package br.com.portalloginunificado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalloginunificado.model.Aplicacao;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long>{

	@Query(value = "SELECT u FROM Aplicacao u where aplicacao like '%?1%'")
	List<Aplicacao> findByAplicacao(String aplicacao);
	
	@Query(value = "SELECT u FROM Aplicacao u where id_aplicacao = ?1")
	List<Aplicacao> findById_aplicacao(Long id_aplicacao);
	
	@Query(value = "SELECT u FROM Aplicacao u where url like '%?1%'")
	List<Aplicacao> findByUrl(String url);
	
	@Query(value = "SELECT u FROM Aplicacao u where aplicacao = '%?1%'")
	Aplicacao findAplicacao(String aplicacao);

	@Query(value = "SELECT u FROM Aplicacao u")
	List<Aplicacao> listaAplicacao();

}
