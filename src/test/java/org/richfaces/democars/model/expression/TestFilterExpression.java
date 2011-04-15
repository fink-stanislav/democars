package org.richfaces.democars.model.expression;

import static org.easymock.EasyMock.createMock;

import java.util.Set;

import org.junit.Before;
import org.richfaces.democars.model.params.FilterParams;

public abstract class TestFilterExpression extends TestDataRetrievalExpression {
    protected FilterParams filterParamsMock;   
    protected FilterExpression filterExpression;
    protected Set<String> keys;
    
    @Before
    public void setUp() {
	filterParamsMock = createMock("filterParamsMock", FilterParams.class);		
	filterExpression = new FilterExpression(entityParamsMock);
	filterExpression.setFilterParams(filterParamsMock);	
    }
}
