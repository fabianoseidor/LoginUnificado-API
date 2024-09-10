package br.com.portalloginunificado.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.portalloginunificado.model.Pessoa;
import br.com.portalloginunificado.model.UsersLogin;
import br.com.portalloginunificado.model.dto.AprovadoresDTO;
import br.com.portalloginunificado.model.dto.ResponsavelAtividadeDTO;
import br.com.portalloginunificado.model.dto.UsersDTO;
import br.com.portalloginunificado.repository.PessoalRepository;
import br.com.portalloginunificado.repository.UsersLoginRepository;
import br.com.portalloginunificado.util.ExceptionCustomizada;

@RestController
public class UsersController {

	@Autowired
	UsersLoginRepository usersLoginRepository;
	
	@Autowired
	PessoalRepository pessoalRepository; 
	
	
	
	@ResponseBody
	@PostMapping(value = "**/salvarUsers")
	public ResponseEntity<UsersLogin> salvarUsers(@RequestBody UsersLogin usersLogin) throws ExceptionCustomizada{
		
		if(usersLogin == null) {
			throw new ExceptionCustomizada("ERRO ao tentar cadastrar uma Aplicação. Valores vazios!");
		}
		
		if(usersLogin.getId_users() == null && usersLoginRepository.findPorLogin(usersLogin.getLogin() ) != null) {
			throw new ExceptionCustomizada("ERRO: Esta User já existe na Base de Dados!");
		}
		Pessoa p = pessoalRepository.findBayEmail( usersLogin.getPessoa().getEmail().toUpperCase() );
		if( p != null ) throw new ExceptionCustomizada("ERRO: Já existe este E-mail na Base de Dados!");

		if(usersLogin.getPessoa() == null) throw new ExceptionCustomizada("ERRO: Favor informar os dados da Pessoa do Login!");
/*		
		Pessoa pessoa = pessoalRepository.save(users.getPessoa());
		users.setPessoa(pessoa);
*/		
		if(usersLogin.getUserAplicativos() == null) throw new ExceptionCustomizada("ERRO: Favor informar as aplicaçõa(es) para o Login!");
		
		
		for( int i = 0; i < usersLogin.getUserAplicativos().size(); i++ )
			usersLogin.getUserAplicativos().get(i).setUsers(usersLogin);
		
		
		usersLogin = usersLoginRepository.save(usersLogin);

		for( int i =0; i < usersLogin.getUserAplicativos().size(); i++) {
			if( ( usersLogin.getUserAplicativos().get(i).getAplicacao().getId_aplicacao() == 2 ) ) {
				InetAddress ia;
				String node = "";
				try {
					ia = InetAddress.getLocalHost();
					node    = ia.getHostName();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				String urlAPIBaseBacktEndMudanca = "";
		        if(node.equals("PIBASTIANDEV"))  urlAPIBaseBacktEndMudanca = "http://localhost:8090/PortalMudanca/";
		        else  urlAPIBaseBacktEndMudanca = "http://10.154.20.134:8090/PortalMudanca/";
		        
		        if(  usersLogin.getUserAplicativos().get(i).getAprovador() ) {
		        
			        AprovadoresDTO aprovadore = new AprovadoresDTO();
			        aprovadore.setAprovador      ( usersLogin.getPessoa().getNome_pessoa()                             );
			        aprovadore.setLogin_aprovador( usersLogin.getLogin()                                               );
			        aprovadore.setEmail          ( usersLogin.getPessoa().getEmail()                                   );
			        aprovadore.setObs            ( "Aprovador cadastrodo pelo User: " + usersLogin.getLogin_cadastro() );
					
			        String url = urlAPIBaseBacktEndMudanca + "salvarAprovadores"; //2	
					ResponseEntity<AprovadoresDTO> aprovadoresRet = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(aprovadore), AprovadoresDTO.class);
					
					if( aprovadoresRet == null) {
						throw new ExceptionCustomizada("ERRO ao tentar cadastrar um Aprovador. Valores vazios!");
					}

		        }
		        
		        if(  usersLogin.getUserAplicativos().get(i).getExecutor() ) {
		        	
		        	ResponsavelAtividadeDTO respDTO = new ResponsavelAtividadeDTO();
		        	respDTO.setResponsavel_atividade      ( usersLogin.getPessoa().getNome_pessoa() );
		        	respDTO.setLogin_responsavel_atividade( usersLogin.getLogin()                   );
		        	respDTO.setEmail_responsavel_atividade( usersLogin.getPessoa().getEmail()       );
		        	String url = urlAPIBaseBacktEndMudanca + "SalvarResponsavelAtividade"; //2	
		        	ResponseEntity<ResponsavelAtividadeDTO> respRet = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(respDTO), ResponsavelAtividadeDTO.class);
		        	
					if( respRet == null) {
						throw new ExceptionCustomizada("ERRO ao tentar cadastrar Responsavel por uma Atividade. Valores vazios!");
					}

		        }
			}
			
		}
		
		return new ResponseEntity<UsersLogin>(usersLogin, HttpStatus.OK);		
	}
	
	@ResponseBody
	@GetMapping(value = "**/buscarLikeLogin/{login}")
	public ResponseEntity<List<UsersLogin>> buscarLikeLogin(@PathVariable("login") String login) { 
		
		List<UsersLogin> usersLogin = usersLoginRepository.findLikeLogin(login);
		
		return new ResponseEntity<List<UsersLogin>>(usersLogin,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/loginPassAPP/{login}/{passoword}")
	public ResponseEntity<UsersLogin> loginPassAPP(@PathVariable("login") String login, @PathVariable("passoword") String passoword) { 
		
		UsersLogin usersLogin = usersLoginRepository.findLoginPass(login.trim(), passoword.trim());
		
		return new ResponseEntity<UsersLogin>(usersLogin,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("**/updadePass/{login}/{pass}/{idPessoa}")
	public ResponseEntity<String> updadePass(@PathVariable("login")    String login   , 
			                                 @PathVariable("pass")     String pass    ,
			                                 @PathVariable("idPessoa") Long   idPessoa){

		usersLoginRepository.updatePassoword( login, pass );
		
		usersLoginRepository.updatePrimeiroAcesso( idPessoa );
		
	    return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarByLoginDTO/{login}")
	public ResponseEntity<UsersDTO> buscarByLoginDTO(@PathVariable("login") String login) { 
		
		UsersDTO users = usersLoginRepository.findPorLoginDTO(login.trim());
		
		return new ResponseEntity<UsersDTO>(users,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarByLogin/{login}")
	public ResponseEntity<UsersLogin> buscarByLogin(@PathVariable("login") String login) { 
		
		UsersLogin usersLogin = usersLoginRepository.findPorLogin(login.trim());
		
		return new ResponseEntity<UsersLogin>(usersLogin,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarByPessoa/{pessoa}")
	public ResponseEntity<UsersLogin> buscarByPessoa(@PathVariable("pessoa") Pessoa pessoa) { 
		
		UsersLogin usersLogin = usersLoginRepository.findByPessoa(pessoa);
		
		return new ResponseEntity<UsersLogin>(usersLogin,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarlistaLogin")
	public ResponseEntity<List<UsersLogin>> buscarlistaLogin( ) { 
		
		List<UsersLogin> usersLogin = usersLoginRepository.findLitaLogin();
		
		return new ResponseEntity<List<UsersLogin>>(usersLogin,HttpStatus.OK);
	}
	
}
