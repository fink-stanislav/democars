package org.richfaces.democars.model.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDefaultExpression extends TestDataRetrievalExpression {
    private DefaultExpression defaultExpression;   
    
    @Before
    public void setUp() {	
	defaultExpression = new DefaultExpression(entityParamsMock);
    }
    
    @Test
    public void testEvaluate() {	
	String result = defaultExpression.evaluate();
	Assert.assertEquals("Expression string should be empty", "", result);
    }
}
