package io.driver.codrive.modules.record.service;

import org.springframework.stereotype.Service;

import io.driver.codrive.global.exception.IllegalArgumentApplicationException;
import io.driver.codrive.modules.codeblock.service.CodeblockService;
import io.driver.codrive.modules.mappings.recordcategorymapping.service.RecordCategoryMappingService;
import io.driver.codrive.modules.record.domain.Record;
import io.driver.codrive.modules.record.domain.RecordStatus;
import io.driver.codrive.modules.record.model.request.AbstractRecordCreateRequest;
import io.driver.codrive.modules.user.domain.User;
import io.driver.codrive.modules.user.service.UserService;

@Service
public class RecordTempService extends AbstractRecordCreateService {
	private static final int TEMP_RECORD_LIMIT = 3;
	private final RecordService recordService;

	public RecordTempService(UserService userService,
		CodeblockService codeblockService,
		RecordCategoryMappingService recordCategoryMappingService, RecordService recordService) {
		super(userService, codeblockService, recordCategoryMappingService, recordService);
		this.recordService = recordService;
	}

	@Override
	protected void preprocess(User user, AbstractRecordCreateRequest request) {
		if (request.getTempRecordId() == null) {
			checkTempRecordLimit(user);
		}
	}

	private void checkTempRecordLimit(User user) {
		Long tempRecordsCount = recordService.getRecordsCountByUserAndRecordStatus(user, RecordStatus.TEMP);
		if (tempRecordsCount >= TEMP_RECORD_LIMIT) {
			throw new IllegalArgumentApplicationException("임시 저장 최대 개수를 초과했습니다.");
		}
	}

	@Override
	protected void postprocess(User user, Record record) {} //필요한 경우 재정의하여 사용
}
