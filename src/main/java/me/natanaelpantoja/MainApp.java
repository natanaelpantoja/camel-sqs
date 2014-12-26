package me.natanaelpantoja;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

/**
 * A Camel Application
 */
public class MainApp {

    public static void main(String... args) throws Exception {
    	
    	AWSCredentials awsCredentials = new BasicAWSCredentials("myAccessKey", "mySecretKey");
    	AmazonSQS client = new AmazonSQSClient(awsCredentials);
    	JndiRegistry registry = new JndiRegistry();    	
    	registry.bind("sqsClient", client);
    	DefaultCamelContext ctx = new DefaultCamelContext(registry);
    	
        ctx.addRoutes(new SQSRouteBuilder());
        ctx.start();
        Thread.sleep(100000);
    }

}

