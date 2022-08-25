package com.util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeSerializer implements JsonSerializer <LocalDateTime> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" uuuu 年 MM 月 d 日 HH 時 mm 分 ss 秒");

	@Override
	public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(formatter.format(localDateTime));
	}

}
