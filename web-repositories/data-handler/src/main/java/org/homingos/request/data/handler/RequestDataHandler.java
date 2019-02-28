package org.homingos.request.data.handler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.json.JSONObject;
import org.homingos.data.handler.DataHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestDataHandler extends DataHandler {
	private static final long serialVersionUID = 1L;
	private Map<String, String> version;
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	public Object getValue(String key) {
		return data.get(key);
	}

	public RequestDataHandler() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pointer", "10.0.1");
		map.put("name", "ultron");
		this.version = map;

	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	public Long getValueLong(String key) {
		Object obj = data.get(key);
		if (obj == null) {
			return null;
		}
		Long value = null;
		try {
			if (obj instanceof String) {
				value = Long.valueOf((String) obj);
			} else {
				value = ((Number) obj).longValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	public Integer getValueInteger(String key) {
		Object obj = data.get(key);
		if (obj == null) {
			return null;
		}
		Integer value = null;
		try {
			if (obj instanceof String) {
				value = Integer.valueOf((String) obj);
			} else {
				value = ((Number) obj).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	public Double getValueDouble(String key) {
		Object obj = data.get(key);
		if (obj == null) {
			return null;
		}
		Double value = null;
		try {
			if (obj instanceof String) {
				value = Double.valueOf((String) obj);
			} else {
				value = ((Number) obj).doubleValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * 
	 * @author Aman Dubey
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@SuppressWarnings("rawtypes")
	public Set getValueSet(String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		Set values;
		try {
			values = (Set) objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)),
					new TypeReference<Set>() {
					});
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getValueList(String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		List values;
		try {
			values = (List) objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)),
					new TypeReference<List>() {
					});
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getValueMap(String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map values;
		try {
			values = (Map) objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)),
					new TypeReference<Map>() {
					});
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getValueObject(String key, Class clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Parses the string argument as a boolean. The {@code boolean} returned
	 * represents the value {@code true} if the string argument is not {@code null}
	 * and is equal, ignoring case, to the string {@code "true"}.
	 * <p>
	 * Example: {@code Boolean.parseBoolean("True")} returns {@code true}.<br>
	 * Example: {@code Boolean.parseBoolean("yes")} returns {@code false}.
	 * 
	 * @author Aman Dubey
	 * @param request
	 * @param key
	 * @return
	 */
	public boolean getValueBoolean(String key) {
		Object attributeStr = data.get(key);
		boolean flag = false;
		if (attributeStr != null) {
			flag = Boolean.parseBoolean((String) attributeStr);
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public List getValueListLong(String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Long> values;
		try {
			values = (List<Long>) objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)),
					new TypeReference<List<Long>>() {
					});
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getValueListObjectNode(String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ObjectNode> values;
		try {
			values = (List<ObjectNode>) objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)),
					new TypeReference<List<ObjectNode>>() {
					});
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Aman Dubey
	 * @param key
	 * @return
	 */
	public String getValueString(String key) {
		Object attributeStr = data.get(key);
		String value = null;
		if (attributeStr != null) {
			value = attributeStr.toString();
		}
		return value;
	}

	public ArrayNode getValueArrayNode(String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayNode values;
		try {
			values = (ArrayNode) objectMapper.readValue(objectMapper.writeValueAsString(data.get(key)),
					new TypeReference<ArrayNode>() {
					});
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getJsonObject(String json) {
		try {
			JSONObject values = new JSONObject(json);
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
