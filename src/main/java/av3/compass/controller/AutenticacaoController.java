package av3.compass.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import av3.compass.config.security.TokenService;
import av3.compass.controller.dto.TokenDto;
import av3.compass.controller.form.LoginForm;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager  authMng; 
	
	@Autowired
	private TokenService tokenService;
	

	@PostMapping
	public 	ResponseEntity<TokenDto> autenticar (@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dados = form.converter();
		
		try {
			Authentication authenticate = authMng.authenticate(dados); 
			String token = tokenService.gerarToken(authenticate);
			
			
			return  ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}



	}
}	

