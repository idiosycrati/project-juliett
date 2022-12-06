package com.juliett.core.model.enums;

import java.util.Arrays;

public enum Status {
	TERMINATED("terminated"), ACTIVE("active"), EXPIRED("expired"), NULL("null");

	private final String title;

	Status(String title) {
		this.title = title;
	}

	public static Status findString(String status) {
		switch (status) {
		case "terminated":
			return TERMINATED;
		case "active":
			return ACTIVE;
		case "expired":
			return EXPIRED;

		default:
			return NULL;
		}
	}

	public String getTitle() {
		return title;
	}

}
