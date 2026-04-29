package com.mycompany.app;
import soot.jimple.infoflow.Infoflow;
import soot.jimple.infoflow.results.InfoflowResults;
import soot.jimple.infoflow.entryPointCreators.DefaultEntryPointCreator;
import soot.jimple.infoflow.sourcesSinks.manager.DefaultSourceSinkManager;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Infoflow infoflow = new Infoflow();

        String javaHome = "/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home/jre/lib/rt.jar";

        String targetJar = "/Users/krishshah/aws_java/getstarted/target/getstarted-1.0-SNAPSHOT.jar";

        List<String> entryPoints = Arrays.asList(
            "<org.benchmark1.HTTPHandler: void onHTTPPostEvent(com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent, com.amazonaws.services.lambda.runtime.Context)>",
            "<org.benchmark1.S3Handler: void onS3Upload(com.amazonaws.services.lambda.runtime.events.S3Event, com.amazonaws.services.lambda.runtime.Context)>"
        );

        List<String> sources = Arrays.asList(
            "<com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent: java.lang.String getBody()>"
        );

        List<String> sinks = Arrays.asList(
            "<java.lang.Runtime: java.lang.Process exec(java.lang.String)>"
        );

        DefaultEntryPointCreator entryPointCreator = new DefaultEntryPointCreator(entryPoints);
        DefaultSourceSinkManager sourceSinkManager = new DefaultSourceSinkManager(sources, sinks);

        infoflow.computeInfoflow(targetJar, javaHome, entryPointCreator, sourceSinkManager);

        InfoflowResults results = infoflow.getResults();

        if (results == null || results.isEmpty()) {
            System.out.println("No flows found");
        } else {
            System.out.println("Flows found: " + results.toString());
        }
    }
}