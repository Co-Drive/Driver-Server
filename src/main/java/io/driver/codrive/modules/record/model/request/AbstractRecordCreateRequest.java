package io.driver.codrive.modules.record.model.request;

import java.util.List;

import io.driver.codrive.modules.codeblock.model.request.CodeblockCreateRequest;

import io.driver.codrive.modules.record.domain.Record;
import io.driver.codrive.modules.user.domain.User;
import lombok.Getter;

@Getter
public abstract class AbstractRecordCreateRequest {
	Long tempRecordId;
	String title;
	int level;
	List<String> tags;
	String platform;
	String problemUrl;
	List<CodeblockCreateRequest> codeblocks;

	public abstract Record toRecord(User user);
}
