package io.driver.codrive.modules.record.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import io.driver.codrive.modules.codeblock.service.CodeblockService;
import io.driver.codrive.modules.mappings.recordcategorymapping.service.RecordCategoryMappingService;
import io.driver.codrive.modules.record.domain.Record;
import io.driver.codrive.modules.record.model.request.AbstractRecordCreateRequest;
import io.driver.codrive.modules.record.service.github.GithubCommitService;
import io.driver.codrive.modules.user.domain.User;
import io.driver.codrive.modules.user.service.UserService;

@Service
public class RecordSaveService extends AbstractRecordCreateService {
	private final GithubCommitService githubCommitService;
	private final RecordService recordService;

	public RecordSaveService(UserService userService, CodeblockService codeblockService,
		RecordCategoryMappingService recordCategoryMappingService,
		RecordService recordService, GithubCommitService githubCommitService) {
		super(userService, codeblockService, recordCategoryMappingService, recordService);
		this.githubCommitService = githubCommitService;
		this.recordService = recordService;
	}

	@Override
	protected void preprocess(User user, AbstractRecordCreateRequest request) {} //empty method

	@Override
	protected void postprocess(User user, Record record) throws IOException {
		int successRate = recordService.getSuccessRate(user);
		user.saveRecord(record, successRate);
		record.changeRecordNum(user.getSolvedCount());
		githubCommitService.commitRecordToGithub(record);
	}
}
