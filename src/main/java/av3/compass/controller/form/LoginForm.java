package av3.compass.controller.form;

import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@NotNull
	private String email;
	@NotNull
	private String senha;
	
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
