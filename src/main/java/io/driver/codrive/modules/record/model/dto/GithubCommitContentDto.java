package io.driver.codrive.modules.record.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GithubCommitContentDto {
	private String message;
	private String content;
	private String sha;

	public static GithubCommitContentDto of(String message, String content, String sha) {
		return GithubCommitContentDto.builder()
			.message(message)
			.content(content)
			.sha(sha)
			.build();
	}
}
