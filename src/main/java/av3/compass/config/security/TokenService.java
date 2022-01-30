package av3.compass.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import av3.compass.modelo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
@Service
public class TokenService {
	
	@Value("${compass.jwt.expiration}")
	private String expiration;
	
	@Value("${compass.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		Date issuedDate = new Date();
		Date expirationDate = new Date(issuedDate.getTime() + Long.valueOf(expiration));
		
		return 	 Jwts.builder()
				.setIssuer("API estados avaliação 3 compass")
				.setSubject(user.getId().toString())
				.setIssuedAt(issuedDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getUserId(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();	
		System.out.println( Long.valueOf(body.getSubject()));
		return Long.valueOf(body.getSubject());
	}

}
