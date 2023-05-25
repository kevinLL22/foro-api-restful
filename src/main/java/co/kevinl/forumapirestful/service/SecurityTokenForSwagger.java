package co.kevinl.forumapirestful.service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Operation(security = { @SecurityRequirement(name = "bearer-key") })
public @interface SecurityTokenForSwagger {

}

