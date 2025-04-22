package io.driver.codrive.global.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import io.driver.codrive.global.exception.UnauthorizedApplicationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthUtils {
	public Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal().toString().equals("anonymousUser")) {
			throw new UnauthorizedApplicationException("로그인이 필요합니다.");
		}
		return Long.valueOf(authentication.getPrincipal().toString());
	}
}
