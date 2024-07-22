package io.driver.codrive.modules.auth.model;

import io.driver.codrive.modules.user.domain.User;
import lombok.Builder;

@Builder
public record LoginResponse(
	Long userId,
	String nickname,
	String accessToken
) {
	public static LoginResponse of(User user, String accessToken) {
		return LoginResponse.builder()
			.userId(user.getUserId())
			.nickname(user.getNickname())
			.accessToken(accessToken)
			.build();
	}
}
