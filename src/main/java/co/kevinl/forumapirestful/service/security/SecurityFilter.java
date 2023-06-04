package co.kevinl.forumapirestful.service.security;

import co.kevinl.forumapirestful.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("security filter start");
        //obtain token by request
        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if (authHeader != null){
            System.out.println("token not null");
            var token = authHeader.replace("Bearer ","");
            String[] subjectAndId = tokenService.getSubject(token);
            String subject = subjectAndId[0];
            System.out.println(subject);
            if (subject != null){
                System.out.println("subject from token not null");
                UserDetails user = userRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                //add subject
                System.out.println("abajo método para añadir id al request:" + subjectAndId[1]);
                request.setAttribute("idUser", subjectAndId[1]);
            }
        }
        filterChain.doFilter(request, response);
    }
}
