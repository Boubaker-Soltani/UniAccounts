import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // =================================================================
        // Singelton التهيئة: الحصول على الكائنات المشتركة عبر نمط
        // =================================================================
        AccountRepository repository = AccountRepository.getRepository(); // استدعاء المستودع
        AuditLogger logger = AuditLogger.getInLogger(); // استدعاء سجل التدقيق

        System.out.println("======================= UniAccounts ===========================\n");

        // =================================================================
        // [المهمة 1]: إنشاء حسابين لطالبين باستخدام نمط Builder
        // =================================================================
        System.out.println("--- [Task 1] Creating two student accounts ---");

        // احمد بن علي(Ahmed ben ali) بناء حساب الطالب الأول
        StudentAccount student1 = new StudentAccount.Builder("20260001", "Ahmed ben ali", "ahmed.ali@univ.dz")
                .major("Informatique") // التخصص
                .level("L3") // المستوى
                .courses(List.of("Java", "Bases de donnees", "Systemes d'exploitation"))
                .build(); // إنشاء الكائن النهائي

        // مريم فرحات (Meriam ferhat) بناء حساب الطالب الثاني
        StudentAccount student2 = new StudentAccount.Builder("20260002", "Meriam ferhat", "meryem.k@univ.dz")
                .major("Mathematiques") // التخصص
                .level("M1") // المستوى
                .build(); // إنشاء الكائن النهائي

        // =================================================================
        // [المهام 2]: إضافة الحساب الأول والثاني الى المستودع
        // =================================================================
        System.out.println("--- [Tasks 2] Adding the accounts to repository ---");

        repository.addAccount(student1); // اضافة الحساب الاول
        repository.addAccount(student2); // اضافة الحساب الثاني
        System.out.println();

        // =================================================================
        // [المهمة 3] طباعة الطلبة المسجلين مباشرة بعد الإضافة
        // =================================================================
        System.out.println("--- [Task 3] Show registered Students currently in the repository ---");

        // المرور على جميع الحسابات المخزنة في المستودع وطباعتها
        for (StudentAccount student : repository.getAllAccounts())
            System.out.println(student);
        System.out.println();

        // =================================================================
        // [المهمة 4]: حذف الحساب الأول ثم الحساب الثاني
        // =================================================================
        System.out.println("--- [Tasks 4] Deleting the first account ---");

        repository.deleteAccount(student1); // حذف الحساب الأول
        System.out.println();

        // =================================================================
        // [المهمة 5]: إعادة تنفيذ عملية الإضافة مرتين مع تسجيل كل عملية
        // =================================================================

        System.out.println("--- [Task 5] Re-create secound account ---");
        repository.addAccount(student2); // اعادة اضافة الطالب الثاني

        // =================================================================
        // [المهمة 6]: إعادة تنفيذ عملية الحذف مرتين مع تسجيل كل عملية
        // =================================================================
        System.out.println("--- [Task 6] Re-running the delete operation twice on student 1 ---");

        repository.deleteAccount(student1); // إعادة حذف الحساب الأول
        System.out.println();

        // =================================================================
        // [المهمة 7]: تحديث معلومات حساب طالب
        // =================================================================
        System.out.println("--- [Task 7] Update Account 2 (add course) ---");

        student2.addCourse("Algebre"); // إضافة مقرر للطالبة الثانية
        repository.updateAccount(student2); // حفظ التحديث في المستودع
        System.out.println();
        
        // =================================================================
        // [المهمتان 8]: عرض ملعومات الطالب الثاني بعد التعديل
        // =================================================================
        System.out.println("--- [Task 8] Show Student 2 after update ---");
        System.out.println(student2);
        System.out.println();

        // =================================================================
        // [المهمتان 9]: عرض سجل العمليات التاريخي الشامل وتفقده
        // =================================================================
        System.out.println("--- [Tasks 8] Displaying the operations history ---");

        // عرض السجل الذي يحتفظ بكافة الأحداث الناجحة والفاشلة بترتيبها الزمني
        ArrayList<String> logs = logger.getAllLogs();
        for (String log : logs)
            System.out.println(log);
    }
}
