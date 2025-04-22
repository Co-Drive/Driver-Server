package io.driver.codrive.modules.mappings.roomlanguagemapping.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomLanguageMappingRepository extends JpaRepository<RoomLanguageMapping, Long> {
}
