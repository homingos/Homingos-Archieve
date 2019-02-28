package org.homingos.response.data.handler;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.homingos.data.handler.DataHandler;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseDataHandler  extends DataHandler
{	
	private static final long serialVersionUID = 1L;
	private Map<String,String> version;
	private Map<String,String> status;	
	private Object data;
	private boolean isError;	
	
	public ResponseDataHandler()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "ultron");	
		map.put("version", "10.0.1");		
		this.version=map;		
	}
}
