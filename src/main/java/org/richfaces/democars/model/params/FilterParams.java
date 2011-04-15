package org.richfaces.democars.model.params;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FilterParams {
    private Map<String, Object> parameters;
         
    public FilterParams() {
	parameters = new HashMap<String, Object>();
    }

    public Object getParam(String key) {
	return parameters.get(key);
    }
    
    public void setParam(String key, Object value) {
	parameters.put(key, value);
    }
    
    public void removeParam(String key) {
	parameters.remove(key);
    }
    
    public Set<String> getKeys() {
	if (parameters.isEmpty()) {
	    return Collections.emptySet();
	}
	return parameters.keySet();
    } 
}
