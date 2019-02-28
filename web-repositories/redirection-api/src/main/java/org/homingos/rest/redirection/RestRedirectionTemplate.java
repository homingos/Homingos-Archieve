package org.homingos.rest.redirection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.homingos.request.data.handler.RequestDataHandler;
import org.homingos.response.data.handler.ResponseDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RestRedirectionTemplate {
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	@Autowired
	private RestTemplate restTemplate;

	private RestRedirectionTemplate() {
	}

	public static RestRedirectionTemplate getInstance() {
		return (RestRedirectionTemplate) context.getBean("restRedirectionTemplate");
	}

	/**
	 * @author Aman Dubey
	 * @param requestParams
	 * @return
	 */
	private RequestDataHandler getRequestDataHandler(Map<String, Object> requestParams) {
		RequestDataHandler request = new RequestDataHandler();
		if (request != null) {
			request.setData(requestParams);
		}
		return request;
	}

	/**
	 * @author Aman Dubey
	 * @param url
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	private ResponseDataHandler call(String url, Map<String, Object> requestParams) throws Exception {
		RequestDataHandler request = getRequestDataHandler(requestParams);
		ResponseDataHandler response = restTemplate.postForObject(url, request, ResponseDataHandler.class);

		/*
		 * if (response.isError()) { throw new Exception((String) response.getData()); }
		 */

		return response;
	}

	private ResponseDataHandler call(String url, Map<String, Object> requestParams, HttpHeaders headers)
			throws Exception {

		RequestDataHandler request = getRequestDataHandler(requestParams);
		HttpEntity entity = new HttpEntity(request, headers);
		// ResponseDataHandler response = restTemplate.postForObject(url,
		// request,ResponseDataHandler.class);
		ResponseEntity<ResponseDataHandler> responses = restTemplate.exchange(url, HttpMethod.GET, entity,
				ResponseDataHandler.class, requestParams);
		ResponseDataHandler response = responses.getBody();
		response.setData(responses.getHeaders());
		/*
		 * if (response.isError()) { throw new Exception((String) response.getData()); }
		 */

		return response;
	}

	/**
	 * @author Aman Dubey
	 * @param url
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<? extends Object> postForList(String url, Map<String, Object> requestParams) throws Exception {
		try {
			ResponseDataHandler response = call(url, requestParams);
			ObjectMapper objectMapper = new ObjectMapper();
			List list = (List) objectMapper.readValue(objectMapper.writeValueAsString(response.getData()),
					new TypeReference<List>() {
					});
			// log.debug("List elements are :: " + list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @author Aman Dubey
	 * @param url
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public Map postForMap(String url, Map<String, Object> requestParams) throws Exception {
		try {
			ResponseDataHandler response = call(url, requestParams);
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = (Map) objectMapper.readValue(objectMapper.writeValueAsString(response.getData()),
					new TypeReference<Map>() {
					});
			// log.debug("Map elements are :: " + map);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @author Aman Dubey
	 * @param url
	 * @param requestParams
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object postForObject(String url, Map<String, Object> requestParams, Class clazz) throws Exception {
		try {
			ResponseDataHandler response = call(url, requestParams);
			ObjectMapper objectMapper = new ObjectMapper();
			Object result = objectMapper.readValue(objectMapper.writeValueAsString(response.getData()), clazz);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseDataHandler postForResponse(String url, Map<String, Object> requestParams) throws Exception {
		try {
			ResponseDataHandler response = call(url, requestParams);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public ResponseDataHandler postForHeaderResponse(String url, Map<String, Object> requestParams, HttpHeaders headers)
			throws Exception {
		try {
			ResponseDataHandler response = call(url, requestParams, headers);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
