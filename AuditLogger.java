import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogger {

    private static AuditLogger logger; // singelton : one log

    private ArrayList<String> logs;

    private static final DateTimeFormatter FORMAT = // date format : yyyy-MM-dd HH:mm:ss
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AuditLogger() {
        logs = new ArrayList<>();
    }

    public static AuditLogger getInLogger() {
        if (logger == null)
            logger = new AuditLogger();
        return logger;
    }

    public void log(String operation) {
        logs.add(LocalDateTime.now().format(FORMAT) + " | " + operation);
    }

    public void showLogs() {
        for (String log : logs)
            System.out.println(log);
    }

    public ArrayList<String> getAllLogs() {
        return new ArrayList<>(logs);
    }

    public void clearLogs() {
        logs.clear();
    }

}