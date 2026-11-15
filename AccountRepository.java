import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    private static AccountRepository repository; // singelton : one repository

    private Map<String, StudentAccount> accounts;
    private AuditLogger logger = AuditLogger.getInLogger();

    private AccountRepository() {
        accounts = new HashMap<>();
    }

    public static AccountRepository getRepository() { // singelton
        if (repository == null)
            repository = new AccountRepository();
        return repository;
    }

    public boolean exist(String id) {
        return accounts.containsKey(id);
    }

    public StudentAccount getAccount(String id) {
        return accounts.get(id);
    }

    public void addAccount(StudentAccount account) {

        if (account == null) {
            System.out.println("Invalid account!");
            logger.log("ADD failed: invalid account");
            return;
        }

        if (exist(account.getId())) {
            System.out.println("Student already exists!");
            logger.log("ADD failed: " + account.getId() + " already exists");
            return;
        }
        accounts.put(account.getId(), account);
        System.out.println("Student added successfully!");
        logger.log("ADD success: " + account.getId());

    }

    public Collection<StudentAccount> getAllAccounts() {
    return new ArrayList<>(accounts.values());
}

    public void updateAccount(StudentAccount updatedAccount) {
        if (updatedAccount == null || !exist(updatedAccount.getId())) {
            System.out.println("Account not found!");
            logger.log("UPDATE failed: account not found");
            return;
        }
        accounts.put(updatedAccount.getId(), updatedAccount);
        System.out.println("Update successfull");
        logger.log("UPDATE success: " + updatedAccount.getId());
    }

    public void deleteAccount(StudentAccount account) {
        if (account == null || !exist(account.getId())) {
            System.out.println("Account not found!");
            logger.log("DELETE failed: account not found");
            return;
        }
        accounts.remove(account.getId());
    }

}