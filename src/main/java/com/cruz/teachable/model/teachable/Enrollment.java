package com.cruz.teachable.model.teachable;

import java.time.LocalDateTime;

public record Enrollment(
		int user_id,
		LocalDateTime enrolled_at,
		LocalDateTime completed_at,
		int percent_complete,
		LocalDateTime expires_at) {

}