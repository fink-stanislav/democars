package org.richfaces.democars.model.expression;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.Collections;
import java.util.HashSet;

import junit.framework.Assert;

import org.junit.Test;
import org.richfaces.democars.model.params.FilterParams;

public class TestFilterExpressionCleanUpParameters extends TestFilterExpression {    
    @Test
    public void testCleanUpParametersNullParam() {
	keys = new HashSet<String>();
	keys.add("nullParamKey");
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("nullParamKey")).andReturn(null).anyTimes();	
	replay(filterParamsMock);
	
	FilterParams result = filterExpression.cleanUpParams();
	Assert.assertEquals(Collections.emptySet(), result.getKeys());
    }
    
    @Test
    public void testCleanUpParametersEmptyString() {
	keys = new HashSet<String>();
	keys.add("emptyStringParamKey");
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("emptyStringParamKey")).andReturn("").anyTimes();	
	replay(filterParamsMock);
	
	FilterParams result = filterExpression.cleanUpParams();
	Assert.assertEquals(Collections.emptySet(), result.getKeys());
    }
}
