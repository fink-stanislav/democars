package org.richfaces.democars.model.expression;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import org.junit.Before;
import org.richfaces.democars.model.params.EntityParams;

public abstract class TestDataRetrievalExpression {
    protected EntityParams entityParamsMock;
    
    @Before
    public void setUpClass() {
	entityParamsMock = createMock(EntityParams.class);
	expect(entityParamsMock.getEntityAlias()).andReturn("testEntityAlias");
	expect(entityParamsMock.getEntityName()).andReturn("testEntityName");
	replay(entityParamsMock);
    }
}
