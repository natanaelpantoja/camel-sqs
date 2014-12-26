package me.natanaelpantoja;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class SQSRouteBuilder extends RouteBuilder {
	public void configure() {
		from("aws-sqs://caelumQueue?amazonSQSClient=#sqsClient&deleteIfFiltered=false")
				//.filter(simple("${header.identity} == 'login'"))
				.log("We have a message! ${body}")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						String messagestring = exchange.getIn().toString();
						System.out.println("messagestring : " + messagestring);
					}
				})
				.to("file:target?fileName=sqs-msg.xml");
	}

}
