package av3.compass.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import av3.compass.modelo.User;
import av3.compass.repository.UserRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;
	
	
	
	public AutenticacaoTokenFilter(TokenService tokenService,  UserRepository userRepository) {		
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		
		boolean valido = tokenService.isValid(token);
		if(valido) {
			
			autenticar(token);
		}else {
			
		}
		
		
		filterChain.doFilter(request, response);
		
	}

	
	
	private void autenticar(String token) {
		Long userId= tokenService.getUserId(token);
		User user = userRepository.findById(userId).get();
		
		UsernamePasswordAuthenticationToken aut = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(aut);
		
	}

	private String getToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token==null || token.isEmpty() ||!token.startsWith("Bearer ")){
			return null;
		}
				
		return token.substring(7,token.length());
	}

}
