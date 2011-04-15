package org.richfaces.democars.model.expression;

import org.richfaces.democars.model.params.EntityParams;
import org.richfaces.democars.model.params.FilterParams;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;

import java.util.Set;

/**
 * Provides filter expression evaluation
 * 
 * @author S. Fink
 */
public class FilterExpression extends DefaultExpression {
    private FilterParams filterParams;

    public FilterExpression(EntityParams entityParams) {
	super(entityParams);
	this.filterParams = new FilterParams();
    }

    /**
     * Builds JPQL expression for filtering data. Cleans up {@code filterParams}
     * map from null values and empty strings. If {@code filterParams} map
     * become empty, returns built query - in this case empty string. Otherwise
     * builds like expression for string parameters and comparison expression
     * for numeric ones.
     * 
     * @return string representation of JPQL statement
     */
    public String evaluate() {	
	JpqlExpressionBuilder builder = new JpqlExpressionBuilder(entityParams);
	FilterParams filterParams = cleanUpParams();
	
	Set<String> keys = filterParams.getKeys();
	if (keys.isEmpty()) {
	    return builder.getExpression();
	}

	int counter = 0;
	builder.addWhere();
	for (String key : keys) {
	    counter++;
	    if (filterParams.getParam(key) instanceof String) {
		builder.buildLikeExpression(key, filterParams.getParam(key));
		if (builder.isRangeOk(keys.size(), counter)) {
		    builder.addAnd();
		}
	    } else if (filterParams.getParam(key) instanceof Number) {
		builder.buildComparisonExpression(key, filterParams.getParam(key), "<=");
		if (builder.isRangeOk(keys.size(), counter)) {
		    builder.addAnd();
		}
	    }
	}
	return builder.getExpression();
    }

    protected FilterParams cleanUpParams() {	
	FilterParams result = new FilterParams();
	Set<String> keys = filterParams.getKeys();
	for (String key : keys) {	     
	    result.setParam(key, filterParams.getParam(key));
	}

	for (String key : keys) {
	    if (filterParams.getParam(key) == null) {
		result.removeParam(key);
	    } else if (filterParams.getParam(key) instanceof String) {
		if (filterParams.getParam(key).equals("")) {
		    result.removeParam(key);
		}
	    }
	}
	return result;
    }

    public void setFilterParams(FilterParams filterParams) {
	this.filterParams = filterParams;
    }
}
