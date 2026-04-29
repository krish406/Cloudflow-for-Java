package org.benchmark1;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent; //refer to AWS events github from AWS docs
import com.amazonaws.services.lambda.runtime.Context;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class HTTPHandler {
    private final S3Client s3Client;

    public HTTPHandler() {
        s3Client = DependencyFactory.s3Client();
    }

    public void onHTTPPostEvent(APIGatewayProxyRequestEvent event, Context context){
        String msgBody = event.getBody() != null ? event.getBody() : "Empty body"; //from AWS docs

        String uploadFileFolder = "uploads";
        String uploadFileName = msgBody + ".txt";
        String finalPath = uploadFileFolder + '/' + uploadFileName;

        String bucket = System.getenv("BUCKET_NAME");
        s3Client.putObject(PutObjectRequest.builder().bucket(bucket).key(finalPath)
                        .build(), RequestBody.empty());
    }
}
