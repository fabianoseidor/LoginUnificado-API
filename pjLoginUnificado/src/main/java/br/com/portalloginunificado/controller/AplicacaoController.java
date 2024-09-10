package br.com.portalloginunificado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalloginunificado.model.Aplicacao;
import br.com.portalloginunificado.repository.AplicacaoRepository;
import br.com.portalloginunificado.util.ExceptionCustomizada;


@RestController
public class AplicacaoController {

	@Autowired
	AplicacaoRepository aplicacaoRepository;
	
	
	@ResponseBody
	@PostMapping(value = "**/salvarAplicacao")
	public ResponseEntity<Aplicacao> salvarAplicacao(@RequestBody Aplicacao aplicacao) throws ExceptionCustomizada{
		
		if(aplicacao == null) {
			throw new ExceptionCustomizada("ERRO ao tentar cadastrar uma Aplicação. Valores vazios!");
		}
		
		if(aplicacao.getId_aplicacao() == null && aplicacaoRepository.findAplicacao(aplicacao.getAplicacao() ) != null) {
			throw new ExceptionCustomizada("ERRO: Esta Aplicação já existe na Base de Dados!");
		}
		
		aplicacao = aplicacaoRepository.save(aplicacao);
		
		return new ResponseEntity<Aplicacao>(aplicacao, HttpStatus.OK);		
	}

	
	@ResponseBody
	@PostMapping(value = "**/deleteAplicacao")
	public ResponseEntity<String> deleteAplicacao(@RequestBody Aplicacao aplicacao) throws ExceptionCustomizada{
		aplicacaoRepository.deleteById(aplicacao.getId_aplicacao());
		
		return new ResponseEntity<String>("Aplicação removido com sucesso!", HttpStatus.OK);		
	}

	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteAplicacaoPorId/{id}")
	public ResponseEntity<String> deleteAplicacaoPorId(@PathVariable("id") Long id) { 
		
		aplicacaoRepository.deleteById(id);
		
		return new ResponseEntity<String>("Aplicação Removido",HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/obterAplicacao/{id}")
	public ResponseEntity<Aplicacao> obterAplicacao(@PathVariable("id") Long id) throws ExceptionCustomizada { 
		
		Aplicacao aplicacao = aplicacaoRepository.findById(id).orElse(null);
		
		if (aplicacao == null) {
			throw new ExceptionCustomizada("Não encontrou o Aplicação com código: " + id);
		}
		
		return new ResponseEntity<Aplicacao>(aplicacao,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/buscarAplicacaoParteDoNome/{nomeAplicacao}")
	public ResponseEntity<List<Aplicacao>> buscarAplicacaoParteDoNome(@PathVariable("nomeAplicacao") String nomeAplicacao) { 
		
		List<Aplicacao> aplicacao = aplicacaoRepository.findByAplicacao(nomeAplicacao.toUpperCase());
		
		return new ResponseEntity<List<Aplicacao>>(aplicacao,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/buscarAplicacaoProdNome/{nomeAplicacao}")
	public ResponseEntity<Aplicacao> buscarAplicacaoProdNome(@PathVariable("nomeAplicacao") String nomeAplicacao) { 
		
		Aplicacao aplicacao = aplicacaoRepository.findAplicacao(nomeAplicacao.toUpperCase());
		
		return new ResponseEntity<Aplicacao>(aplicacao,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listarAplicacao")
	public ResponseEntity<List<Aplicacao>> listarAplicacao( ) { 
		
		List<Aplicacao> aplicacao = aplicacaoRepository.listaAplicacao();
		
		return new ResponseEntity<List<Aplicacao>>(aplicacao,HttpStatus.OK);
	}

}
