package io.driver.codrive.modules.record.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.driver.codrive.modules.codeblock.service.CodeblockService;
import io.driver.codrive.modules.mappings.recordCategoryMapping.service.RecordCategoryMappingService;
import io.driver.codrive.modules.record.domain.Record;
import io.driver.codrive.modules.record.model.request.AbstractRecordCreateRequest;
import io.driver.codrive.modules.record.model.response.RecordCreateResponse;
import io.driver.codrive.modules.user.domain.User;
import io.driver.codrive.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class AbstractRecordCreateService {
	private final UserService userService;
	private final CodeblockService codeblockService;
	private final RecordCategoryMappingService recordCategoryMappingService;
	private final RecordService recordService;

	@Transactional
	public RecordCreateResponse createRecord(Long userId, AbstractRecordCreateRequest request) throws IOException {
		User user = userService.getUserById(userId);
		preprocess(user, request);
		Record record = createRecordAndMappings(user, request);
		postprocess(user, record);
		return RecordCreateResponse.of(record);
	}

	protected abstract void preprocess(User user, AbstractRecordCreateRequest request);

	protected abstract void postprocess(User user, Record record) throws IOException;

	protected Record createRecordAndMappings(User user, AbstractRecordCreateRequest request) {
		Record record = createRecord(request.toRecord(user), request.getTempRecordId());
		codeblockService.createCodeblocks(request.getCodeblocks(), record);
		recordCategoryMappingService.createRecordCategoryMapping(request.getTags(), record);
		return record;
	}

	private Record createRecord(Record record, Long tempRecordId) {
		Record createdRecord = recordService.saveRecord(record);
		deleteTempRecord(tempRecordId);
		return createdRecord;
	}

	private void deleteTempRecord(Long tempRecordId) {
		if (tempRecordId != null) {
			recordService.deleteRecordById(tempRecordId);
		}
	}
}
