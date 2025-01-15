package io.driver.codrive.modules.record.service;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {
	public Integer calculateSuccessRate(int solvedDayCountByWeek) {
		return switch (solvedDayCountByWeek) {
			case 0 -> 0;
			case 1 -> 15;
			case 2 -> 30;
			case 3 -> 45;
			case 4 -> 60;
			case 5 -> 75;
			case 6 -> 90;
			default -> 100;
		};
	}
}
