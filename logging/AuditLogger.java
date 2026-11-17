import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogger {

    private static AuditLogger logger; // انشاء نسخة وحيدة من السجل

    private ArrayList<String> logs; // تخزين سجل العمليات في قائمة

    // تحديد تنسيق التاريخ والوقت
    private static final DateTimeFormatter FORMAT = // date format : yyyy-MM-dd HH:mm:ss
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //منشئ خاص لمنع انشاء اكثر من نسخة        
    private AuditLogger() {
        logs = new ArrayList<>();
    }

    public static AuditLogger getInLogger() { // ارجاع النسخة المركزية من السجل
        if (logger == null)
            logger = new AuditLogger();
        return logger;
    }

    public void log(String operation) { // تسجيل عملية جديدة مع تحديد تاريخها ووقتها
        logs.add(LocalDateTime.now().format(FORMAT) + " | " + operation);
    }

    public void showLogs() { // عرض العمليات المسجلة
        for (String log : logs)
            System.out.println(log);
    }

    public ArrayList<String> getAllLogs() { // ارجاع سجل العمليات
        return new ArrayList<>(logs);
    }

    public void clearLogs() { // مسح السجل
        logs.clear();
    }

}
