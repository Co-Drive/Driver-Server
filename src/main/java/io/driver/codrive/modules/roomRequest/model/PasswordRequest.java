package io.driver.codrive.modules.roomRequest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PasswordRequest(
	@Schema(description = "비밀번호", example = "비밀번호")
	@NotBlank
	String password
) {
}
