package com.komyar.demonstration.swagger;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import static com.komyar.demonstration.enums.Constants.BEARER_AUTH;

@SecurityRequirement(name = BEARER_AUTH)
public interface SecuredRestController {
}
