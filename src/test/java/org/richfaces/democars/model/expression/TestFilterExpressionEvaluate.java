package org.richfaces.democars.model.expression;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

public class TestFilterExpressionEvaluate extends TestFilterExpression {    
    @Test
    public void testEvaluateStringParam() {	
	keys = new HashSet<String>();
	keys.add("normalParamKey");
	
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("normalParamKey")).andReturn("normalParamValue").anyTimes();	
	replay(filterParamsMock);
	
	String result = filterExpression.evaluate();
	assertEquals("Malformed expression", "where upper(testEntityAlias.normalParamKey) like 'NORMALPARAMVALUE%' ", result);
    }
    
    @Test
    public void testEvaluateEmptyKeySet() {	
	keys = new HashSet<String>();
	
	expect(filterParamsMock.getKeys()).andReturn(keys);		
	replay(filterParamsMock);
	
	String result = filterExpression.evaluate();
	assertEquals("Expression must be empty", "", result);
    }
    
    @Test
    public void testEvaluateMoreThanOneStringParam() {	
	keys = new HashSet<String>();
	keys.add("firstParamKey");
	keys.add("secondParamKey");
	
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("firstParamKey")).andReturn("firstParamValue").anyTimes();
	expect(filterParamsMock.getParam("secondParamKey")).andReturn("secondParamValue").anyTimes();
	
	replay(filterParamsMock);
	String result = filterExpression.evaluate();
	assertEquals("Malformed expression", "where upper(testEntityAlias.secondParamKey) like 'SECONDPARAMVALUE%' and upper(testEntityAlias.firstParamKey) like 'FIRSTPARAMVALUE%' ", result);
    }
    
    @Test
    public void testEvaluateNumberParam() {	
	keys = new HashSet<String>();
	keys.add("numberParamKey");
	
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("numberParamKey")).andReturn(new Integer(25)).anyTimes();
	replay(filterParamsMock);
	
	String result = filterExpression.evaluate();
	assertEquals("Malformed expression", "where testEntityAlias.numberParamKey <= 25 ", result);
    }
    
    @Test
    public void testEvaluateMoreThanOneNumberParam() {	
	keys = new HashSet<String>();
	keys.add("firstNumberParamKey");
	keys.add("secondNumberParamKey");
	
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("firstNumberParamKey")).andReturn(new Integer(25)).anyTimes();
	expect(filterParamsMock.getParam("secondNumberParamKey")).andReturn(new Integer(36)).anyTimes();
	replay(filterParamsMock);
	
	String result = filterExpression.evaluate();
	assertEquals("Malformed expression", "where testEntityAlias.firstNumberParamKey <= 25 and testEntityAlias.secondNumberParamKey <= 36 ", result);
    }
    
    @Test
    public void testEvaluateCombinedParams() {	
	keys = new HashSet<String>();
	keys.add("numberParamKey");
	keys.add("stringParamKey");
	
	expect(filterParamsMock.getKeys()).andReturn(keys);	
	expect(filterParamsMock.getParam("numberParamKey")).andReturn(new Integer(25)).anyTimes();
	expect(filterParamsMock.getParam("stringParamKey")).andReturn("stringParamValue").anyTimes();	
	replay(filterParamsMock);
	
	String result = filterExpression.evaluate();
	assertEquals("Malformed expression", "where upper(testEntityAlias.stringParamKey) like 'STRINGPARAMVALUE%' and testEntityAlias.numberParamKey <= 25 ", result);
    }
}
