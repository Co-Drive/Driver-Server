package io.driver.codrive.global.model;


import static io.driver.codrive.modules.mappings.roomusermapping.domain.QRoomUserMapping.*;
import static io.driver.codrive.modules.room.domain.QRoom.*;

import java.util.Comparator;

import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

import io.driver.codrive.global.exception.IllegalArgumentApplicationException;
import io.driver.codrive.modules.room.domain.Room;

public enum SortType {
	NEW, DICT, OLD;

	private static final String TIME_PROPERTIES = "createdAt";
	private static final String DICTIONARY_PROPERTIES = "title";
	private static final String NOT_SUPPORTED_EXCEPTION_MESSAGE = "지원하지 않는 정렬 방식입니다.";

	public static Sort getRoomSort(SortType sortType) {
		if (sortType == NEW) {
			return Sort.by(Sort.Direction.DESC, TIME_PROPERTIES);
		} else if (sortType == DICT) {
			return Sort.by(Sort.Direction.ASC, DICTIONARY_PROPERTIES);
		} else {
			throw new IllegalArgumentApplicationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
		}
	}

	public static Comparator<Room> getJoinedRoomComparator(SortType sortType) {
		if (sortType == SortType.NEW) {
			return Comparator.comparing(Room::getCreatedAt).reversed();
		} else if (sortType == SortType.DICT) {
			return Comparator.comparing(Room::getTitle);
		} else {
			throw new IllegalArgumentApplicationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
		}
	}

	public static Sort getRoomRequestSort(SortType sortType) {
		if (sortType == NEW) {
			return Sort.by(Sort.Direction.DESC, TIME_PROPERTIES);
		} else if (sortType == OLD) {
			return Sort.by(Sort.Direction.ASC, TIME_PROPERTIES);
		} else {
			throw new IllegalArgumentApplicationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
		}
	}

	public OrderSpecifier createRoomUserOrderSpecifier(SortType sortType) {
		if (sortType == NEW) {
			return new OrderSpecifier<>(Order.DESC, roomUserMapping.createdAt);
		} else if (sortType == DICT) {
			return new OrderSpecifier<>(Order.ASC, room.title);
		} else {
			throw new IllegalArgumentApplicationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
		}
    }

	public OrderSpecifier createRoomOrderSpecifier(SortType sortType) {
		if (sortType == NEW) {
			return new OrderSpecifier<>(Order.DESC, room.createdAt);
		} else if (sortType == DICT) {
			return new OrderSpecifier<>(Order.ASC, room.title);
		} else {
			throw new IllegalArgumentApplicationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
		}
    }
}