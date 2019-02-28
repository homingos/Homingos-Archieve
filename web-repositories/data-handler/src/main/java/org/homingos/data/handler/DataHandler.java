package org.homingos.data.handler;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataHandler implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
	}
}