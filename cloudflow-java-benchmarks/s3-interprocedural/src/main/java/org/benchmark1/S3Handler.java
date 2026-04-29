package org.benchmark1;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import java.io.IOException;

public class S3Handler {
    public void onS3Upload(S3Event event, Context context) throws IOException {
        String bucketName = "";
        String fileName = "";

        for (S3EventNotification.S3EventNotificationRecord record : event.getRecords()) {
            bucketName = record.getS3().getBucket().getName();
            fileName = record.getS3().getObject().getKey();

            System.out.println(bucketName);
            System.out.println(fileName);
        }

        String command = "cd /tmp; convert %s %s";
        String convert_command = String.format(command, fileName, bucketName + '_' + fileName);
        Runtime.getRuntime().exec(convert_command);
    }
}
