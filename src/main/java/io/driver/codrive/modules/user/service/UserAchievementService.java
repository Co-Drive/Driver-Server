package io.driver.codrive.modules.user.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.driver.codrive.global.util.AuthUtils;
import io.driver.codrive.modules.record.service.RecordService;
import io.driver.codrive.modules.user.domain.User;
import io.driver.codrive.modules.user.model.response.UserAchievementResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAchievementService {
	private final UserService userService;
	private final RecordService recordService;

	@Transactional
	public UserAchievementResponse getAchievement() {
		User user = userService.getUserById(AuthUtils.getCurrentUserId());
		int goal = user.getGoal();
		int todayCount = recordService.getTodayRecordCount(user);
		int successRate = user.getSuccessRate();
		int weeklyCount = recordService.getRecordsCountByWeek(user, LocalDate.now());
		int weeklyCountDifference = weeklyCount - getLastWeeklyCount(user);
		return UserAchievementResponse.of(goal, todayCount, successRate, weeklyCount, weeklyCountDifference);
	}

	@Transactional
	protected int getLastWeeklyCount(User user) {
		LocalDate lastWeekDate = LocalDate.now().minusWeeks(1);
		return recordService.getRecordsCountByWeek(user, lastWeekDate);
	}
}
