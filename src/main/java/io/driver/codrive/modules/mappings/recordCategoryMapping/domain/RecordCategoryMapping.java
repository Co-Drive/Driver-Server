package io.driver.codrive.modules.mappings.recordCategoryMapping.domain;

import io.driver.codrive.modules.category.domain.Category;
import io.driver.codrive.modules.global.BaseEntity;
import io.driver.codrive.modules.record.domain.Record;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordCategoryMapping extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordCategoryMappingId;

	@ManyToOne
	@JoinColumn(name = "record_id")
	private Record record;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public static RecordCategoryMapping toEntity(Record record, Category category) {
		return RecordCategoryMapping.builder()
			.record(record)
			.category(category)
			.build();
	}

	public String getCategoryName() {
		return category.getName();
	}

}
