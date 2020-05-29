package service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

public class AuditService {
    private static final AuditService instance = new AuditService();

    private AuditService() {}

    public static AuditService getInstance() {
        return instance;
    }

    public Path getPath() {
        return Paths.get("C:\\Users\\vladd\\IdeaProjects\\PAO Project\\audit.csv");
    }

    public String getTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return ts.toString();
    }

    public void writeAction(String action, String timeStamp, String threadName) {
        try (FileWriter csvWriter = new FileWriter(String.valueOf(getPath()), true)) {
            List<String> line =  Arrays.asList(action, timeStamp, threadName);
            csvWriter.append(String.join(",", line));
            csvWriter.append("\n");
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
