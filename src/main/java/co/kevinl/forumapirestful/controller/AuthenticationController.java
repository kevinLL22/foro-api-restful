package co.kevinl.forumapirestful.controller;

import co.kevinl.forumapirestful.dto.user.DataAuthenticateUser;
import co.kevinl.forumapirestful.dto.user.DataReturnJwtToken;
import co.kevinl.forumapirestful.model.UserEntity;
import co.kevinl.forumapirestful.service.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DataReturnJwtToken> authenticateUser(@RequestBody @Valid DataAuthenticateUser dataAuthenticateUser){

        Authentication autToken = new UsernamePasswordAuthenticationToken(dataAuthenticateUser.email(),
                dataAuthenticateUser.password());
        var userAuthenticated = authenticationManager.authenticate(autToken);
        var JWTtoken = tokenService.generateToken((UserEntity) userAuthenticated.getPrincipal()); //creating token for user
        return ResponseEntity.ok(new DataReturnJwtToken(JWTtoken) );
    }
}
