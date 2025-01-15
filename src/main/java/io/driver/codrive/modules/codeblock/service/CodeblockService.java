package io.driver.codrive.modules.codeblock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.driver.codrive.modules.codeblock.domain.Codeblock;
import io.driver.codrive.modules.codeblock.domain.CodeblockRepository;
import io.driver.codrive.modules.codeblock.model.request.CodeblockCreateRequest;
import io.driver.codrive.modules.codeblock.model.request.CodeblockModifyRequest;
import io.driver.codrive.modules.record.domain.Record;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeblockService {
	private final CodeblockRepository codeblockRepository;

	public void createCodeblocks(List<CodeblockCreateRequest> requests, Record record) {
		if (requests != null && !requests.isEmpty()) {
			List<Codeblock> codeblocks = CodeblockCreateRequest.of(requests, record);
			saveCodeblocks(codeblocks, record);
		}
	}

	public void updateCodeblocks(Record record, List<CodeblockModifyRequest> requests) {
		deleteCodeblocks(record.getCodeblocks(), record);
		List<Codeblock> newCodeblocks = CodeblockModifyRequest.of(requests, record);
		saveCodeblocks(newCodeblocks, record);
	}

	private void saveCodeblocks(List<Codeblock> codeblocks, Record record) {
		codeblockRepository.saveAll(codeblocks);
		record.changeCodeblocks(codeblocks);
	}

	private void deleteCodeblocks(List<Codeblock> codeblocks, Record record) {
		codeblockRepository.deleteAll(codeblocks);
		record.deleteCodeblocks(codeblocks);
	}
}
