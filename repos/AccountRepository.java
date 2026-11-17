package repos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logging.AuditLogger;
import model.StudentAccount;

public class AccountRepository {
    //Singelton 
    private static AccountRepository repository; // انشاء نسخة واحدة من المستودع


    private AuditLogger logger = AuditLogger.getInLogger(); // ربط المستودع بالسجل لتسجيل العمليات
    
    private Map<String, StudentAccount> accounts; // كمفتاح Id تخزين حسابات الطلبة باستخدام  
    
    //منشئ خاص لمنع انشاء نسخ متعددة
    private AccountRepository() {
        accounts = new HashMap<>();
    }


    public static AccountRepository getRepository() { // ارجاع النسخة المركزية من المستودع
        if (repository == null)
            repository = new AccountRepository();
        return repository;
    }

    public boolean exist(String id) { // Id التحقق ان الحساب موجود عبر 
        return accounts.containsKey(id);
    }

    public StudentAccount getAccount(String id) { // الحصول على حساب طالب
        return accounts.get(id);
    }

    public void addAccount(StudentAccount account) { // اضافة حساب جديد

        if (account == null) { // اذا كان الحساب فارغا نطبع رسالة خطا 
            System.out.println("Invalid account!");
            logger.log("ADD failed: invalid account"); // نسجل عملية جديدة في السجل (فشل الاضافة)
            return;
        }

        if (exist(account.getId())) { // اذا كان الحساب موجودا بالفعل 
            System.out.println("Student already exists!"); // نطبع رسالة تنبيه
            logger.log("ADD failed: " + account.getId() + " already exists"); // تسجيل فشل اضافة حساب
            return;
        }
        
        // اضافة حساب
        accounts.put(account.getId(), account);
        System.out.println("Student added successfully!");
        logger.log("ADD success: " + account.getId()); // تسجيل عملية نجاح الاضافة

    }

    // الحصول على قائمة الحسابات المسجلة
    public Collection<StudentAccount> getAllAccounts() {
    return new ArrayList<>(accounts.values());
}

    public void updateAccount(StudentAccount updatedAccount) { // تحديث معلومات طالب
        if (updatedAccount == null || !exist(updatedAccount.getId())) { // اذا كان الحساب فارغا او غير موجود
            System.out.println("Account not found!"); // نطبع رسالة خطا
            logger.log("UPDATE failed: account not found"); // تسجيل عملية فشل التحديث
            return;
        }

        // تحديث
        accounts.put(updatedAccount.getId(), updatedAccount);
        System.out.println("Update successfull");
        logger.log("UPDATE success: " + updatedAccount.getId()); // تسجيل عملية نجاح التحديث
    }

    public void deleteAccount(StudentAccount account) { // حذف حساب طالب
        if (account == null || !exist(account.getId())) { // اذا كان الحساب غير موجود او فارغ 
            System.out.println("Account not found!"); // رسالة خطا
            logger.log("DELETE failed: account not found"); // تسجيل عملية فشل الحذف
            return;
        }

        String id =  account.getId();

        accounts.remove(id); // حذف حساب
        System.out.println("Delete successfull");
        logger.log("DELETE success: " + id);
    }

}
