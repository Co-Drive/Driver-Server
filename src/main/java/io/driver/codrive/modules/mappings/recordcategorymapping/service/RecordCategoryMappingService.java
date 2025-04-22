package io.driver.codrive.modules.mappings.recordcategorymapping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.driver.codrive.modules.mappings.recordcategorymapping.domain.RecordCategoryMapping;
import io.driver.codrive.modules.mappings.recordcategorymapping.domain.RecordCategoryMappingRepository;
import io.driver.codrive.modules.record.domain.Record;
import io.driver.codrive.modules.category.domain.Category;
import io.driver.codrive.modules.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordCategoryMappingService {
	private final CategoryService categoryService;
	private final RecordCategoryMappingRepository recordCategoryMappingRepository;

	public void createRecordCategoryMapping(List<String> tags, Record record) {
		if (tags != null && !tags.isEmpty()) {
			List<RecordCategoryMapping> mappings = getRecordCategoryMappingsByTags(tags, record);
			recordCategoryMappingRepository.saveAll(mappings);
			record.changeCategories(mappings);
		}
	}

	public void updateTags(Record record, List<String> tags) {
		if (!record.compareTags(tags)) {
			deleteRecordCategoryMapping(record.getRecordCategoryMappings(), record);
			createRecordCategoryMapping(tags, record);
		}
	}

	private void deleteRecordCategoryMapping(List<RecordCategoryMapping> mappings, Record record) {
		recordCategoryMappingRepository.deleteAll(mappings);
		record.deleteCategories(mappings);
	}

	private List<RecordCategoryMapping> getRecordCategoryMappingsByTags(List<String> tags, Record record) {
		List<RecordCategoryMapping> recordCategoryMappings = new ArrayList<>();
		tags.forEach(request -> {
			Category category = categoryService.getCategoryByName(request);
			recordCategoryMappings.add(RecordCategoryMapping.toRecordCategoryMapping(record, category));
		});
		return recordCategoryMappings;
	}
}