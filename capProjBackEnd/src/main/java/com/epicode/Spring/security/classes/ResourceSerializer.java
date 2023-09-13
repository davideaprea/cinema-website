package com.epicode.Spring.security.classes;

import java.io.IOException;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ResourceSerializer extends JsonSerializer<Resource>{

	@Override
	public void serialize(Resource value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		String url = value.getURI().toString();
		gen.writeString(url);
	}

}
