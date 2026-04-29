package org.benchmark1;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

//testing to see if the program will successfully use the handler and pass in the event which will then create the
public class App {
    public static void main(String[] args){
        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setBody("testinput");
        new HTTPHandler().onHTTPPostEvent(event, null);
    }
}
