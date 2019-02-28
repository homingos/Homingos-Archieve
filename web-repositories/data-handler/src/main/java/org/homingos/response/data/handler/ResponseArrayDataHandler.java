package org.homingos.response.data.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.homingos.data.handler.DataHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseArrayDataHandler extends DataHandler {
	private static final long serialVersionUID = 1L;
	private Map<String, String> version;
	private Map<String, String> status;
	// private List<String> responseCode;
	private List<Object> data;
	private boolean isError;

	public ResponseArrayDataHandler() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pointer", "10.0.1");
		map.put("name", "ultron");
		this.version = map;
	}

}
