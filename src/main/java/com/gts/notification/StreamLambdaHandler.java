package com.gts.notification;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class StreamLambdaHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            LambdaContainerHandler.getContainerConfig().setInitializationTimeout(50_000);
            handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
                                .defaultProxy()
                                .asyncInit()
                                .springBootApplication(GTSNotificationsService.class)
                                .buildAndInitialize();
            System.out.println("handler: "+handler);
        } catch (ContainerInitializationException e) {
        	System.out.println("Exception in : "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("error", e);
        }
    }
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
    	System.out.println("inputStream: "+inputStream);
    	System.out.println("outputStream: "+outputStream);
    	System.out.println("context: "+context);
    	
        handler.proxyStream(inputStream, outputStream, context);
    }
}
