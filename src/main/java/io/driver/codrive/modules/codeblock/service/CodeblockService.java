package io.driver.codrive.modules.codeblock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.driver.codrive.modules.codeblock.domain.Codeblock;
import io.driver.codrive.modules.codeblock.domain.CodeblockRepository;
import io.driver.codrive.modules.record.domain.Record;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeblockService {
	private final CodeblockRepository codeblockRepository;

	@Transactional
	public void createCodeblock(List<Codeblock> codeblocks, Record record) {
		codeblocks.forEach(codeblock -> {
			codeblock.changeRecord(record);
			codeblockRepository.save(codeblock);
		});
	}

	@Transactional
	public void deleteCodeblock(List<Codeblock> codeblocks) {
		codeblockRepository.deleteAll(codeblocks);
	}
}
