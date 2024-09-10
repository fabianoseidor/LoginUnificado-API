package br.com.portalloginunificado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalloginunificado.model.Pessoa;
import br.com.portalloginunificado.repository.PessoalRepository;
import br.com.portalloginunificado.util.ExceptionCustomizada;

@RestController
public class PessoaController {

	@Autowired
	PessoalRepository pessoalRepository;
		
	@ResponseBody
	@PostMapping(value = "**/salvarPessoa")
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) throws ExceptionCustomizada{
		
		if(pessoa == null) {
			throw new ExceptionCustomizada("ERRO ao tentar cadastrar uma Pessoa. Valores vazios!");
		}

		pessoa = pessoalRepository.save(pessoa);
		
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);		
	}
	
	@ResponseBody
	@GetMapping(value = "**/obterPessoaId/{id}")
	public ResponseEntity<Pessoa> obterPessoaId(@PathVariable("id") Long id) throws ExceptionCustomizada { 
		
		Pessoa pessoa = pessoalRepository.findByIdPessoa(id);
		
		if (pessoa == null) {
			throw new ExceptionCustomizada("Não encontrou o Aplicação com código: " + id);
		}
		
		return new ResponseEntity<Pessoa>(pessoa,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/obterPessoaParteNome/{nomePessoa}")
	public ResponseEntity<List<Pessoa>> obterPessoaParteNome(@PathVariable("nomePessoa") String nomePessoa) throws ExceptionCustomizada { 
		
		List<Pessoa> pessoa = pessoalRepository.findParteNomePessoa(nomePessoa);
		
		if (pessoa == null) {
			throw new ExceptionCustomizada("Não encontrou a Pessoa pelo Nome: " + nomePessoa);
		}
		
		return new ResponseEntity<List<Pessoa>>(pessoa,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/obterfindNomePessoa/{nomePessoa}")
	public ResponseEntity<List<Pessoa>> obterfindNomePessoa(@PathVariable("nomePessoa") String nomePessoa) throws ExceptionCustomizada { 
		
		List<Pessoa> pessoa = pessoalRepository.findBayNomePessoa(nomePessoa);
		
		if (pessoa == null) {
			throw new ExceptionCustomizada("Não encontrou a Pessoa pelo Nome: " + nomePessoa);
		}		
		return new ResponseEntity<List<Pessoa>>(pessoa,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/obterfindPessoaEmail/{emailPessoa}")
	public ResponseEntity<List<Pessoa>> obterfindPessoaEmail(@PathVariable("emailPessoa") String emailPessoa) throws ExceptionCustomizada { 
		
		List<Pessoa> pessoa = pessoalRepository.findBayPessoaEmail(emailPessoa);
		
		if (pessoa == null) {
			throw new ExceptionCustomizada("Não encontrou a Pessoa pelo E-mail: " + emailPessoa);
		}		
		return new ResponseEntity<List<Pessoa>>(pessoa,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/obterfindPessoaCPF/{cpfPessoa}")
	public ResponseEntity<List<Pessoa>> obterfindPessoaCPF(@PathVariable("cpfPessoa") String cpfPessoa) throws ExceptionCustomizada { 
		
		List<Pessoa> pessoa = pessoalRepository.findBayPessoaCPF(cpfPessoa);
		
		if (pessoa == null) {
			throw new ExceptionCustomizada("Não encontrou a Pessoa pelo E-mail: " + cpfPessoa);
		}
		
		return new ResponseEntity<List<Pessoa>>(pessoa,HttpStatus.OK);
	}

}
