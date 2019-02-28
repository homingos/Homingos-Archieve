package org.homingos.response.data.handler.util;

import org.homingos.response.data.handler.ResponseArrayDataHandler;
import org.homingos.response.data.handler.ResponseDataHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseDataUtil 
{
	/**
	 * @author Aman Dubey
	 * @param outputData
	 * @return
	 */
	public static ResponseDataHandler getResponse(Object outputData)
	{
		ResponseDataHandler response = new ResponseDataHandler();
		Map<String, String> responseCode = new HashMap<String, String>();
		responseCode.put("code", "200");
		responseCode.put("value", "SUCCESS");
		response.setStatus(responseCode);
		response.setData(outputData);		
		return response;
	}
	
	public static ResponseDataHandler getResponse(Object outputData, boolean isError)
	{
		ResponseDataHandler response = new ResponseDataHandler();
		Map<String, String> responseCode = new HashMap<String, String>();
		responseCode.put("code", "200");
		responseCode.put("value", "SUCCESS");
		response.setStatus(responseCode);
		response.setData(outputData);		
		response.setError(isError);
		return response;
	}

	public static ResponseDataHandler getResponse(Object outputData, int code, String comment)
	{
		ResponseDataHandler response = new ResponseDataHandler();
		Map<String, String> responseCode = new HashMap<String, String>();
		responseCode.put("code", String.valueOf(code));
		responseCode.put("value", comment);
		response.setStatus(responseCode);
		response.setData(outputData);		
		return response;
	}
	
	public static ResponseDataHandler getResponse(Object outputData, int code, String comment, boolean isError)
	{
		ResponseDataHandler response = new ResponseDataHandler();
		Map<String, String> responseCode = new HashMap<String, String>();
		responseCode.put("code", String.valueOf(code));
		responseCode.put("value", comment);
		response.setStatus(responseCode);
		response.setData(outputData);
		response.setError(isError);		
		return response;
	}

	public static ResponseArrayDataHandler getArrayResponse(List<Object> outputData, int code, String comment)
	{
		ResponseArrayDataHandler response = new ResponseArrayDataHandler();
		Map<String, String> responseCode = new HashMap<String, String>();
		responseCode.put("code", String.valueOf(code));
		responseCode.put("value", comment);
		response.setStatus(responseCode);
		response.setData(outputData);
		response.setError(false);
		// printResponse(outputData, response);
		return response;
	}

	/**
	 * @author Aman Dubey
	 * @param outputData
	 * @return
	 */
	public static ResponseDataHandler getResponse(Throwable throwable) {
		ResponseDataHandler response = new ResponseDataHandler();
		Map<String, String> responseCode = new HashMap<String, String>();
		responseCode.put("code", "404");
		responseCode.put("value", "ERROR");
		response.setStatus(responseCode);
		response.setData(throwable.getMessage());
		response.setError(true);
		// printResponse(outputData, response);
		return response;
	}
	
}
