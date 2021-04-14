package hu.bme.vik.tbs.szakdolgozat.CsotthonApp.security;

import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String jwtAudience;
    private String jwtIssuer;
    private String jwtSecret;
    private String jwtType;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String jwtAudience, String jwtIssuer, String jwtSecret, String jwtType) {
        this.jwtAudience = jwtAudience;
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.jwtType = jwtType;
        this.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/login");
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl)authentication.getPrincipal();
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        String token = Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .setHeaderParam("typ", jwtType)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}