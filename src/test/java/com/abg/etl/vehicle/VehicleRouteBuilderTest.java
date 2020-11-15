package com.abg.etl.vehicle;


import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class VehicleRouteBuilderTest extends CamelTestSupport {

    private static final String MOCK_OUTPUT = "mock:log:output";

    @EndpointInject(uri = "mock:log:output")
    protected MockEndpoint resultEndpoint;

    @Produce("direct:start")
    protected ProducerTemplate template;

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new VehicleRouteBuilder();
    }

    @Override
    public String isMockEndpoints() {
        return "log:output";
    }

    @Test
    public void testOrdersCanBeTransformedAndRoutedToGivenDestination() throws Exception {
        resultEndpoint.expectedMessageCount(1);
        template.sendBody(new VehicleData());
        // Assert Expectation
        getMockEndpoint(MOCK_OUTPUT).assertIsSatisfied();
    }
}
