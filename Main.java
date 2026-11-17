public class Main {
    public static void main(String[] args) {
        // هنا استدعينا النسخة الوحيدة من سجل العمليات AuditLogger باستعمال نمط Singleton [3]
        AuditLogger logger = AuditLogger.getInLogger(); // ارجاع النسخة المركزية من السجل [3]

        // هنا استخدمت List و ArrayList لتخزين قائمة المستودع (Dépôt) [4]
        List<StudentAccount> depot = new ArrayList<>();

        System.out.println("=== DÉBUT DU PROGRAMME ===");
        System.out.println();

        // 1. إنشاء حسابي طالبين باستعمال نمط الباني (Builder Pattern) المعرف في الملاحظات [1, 2]
        // هنا استخدمنا Builder لفرض المعلومات الإجبارية وتحديد التخصص والمستوى [1, 2]
        StudentAccount etudiant1 = new StudentAccount.Builder("E101", "Jean Dupont", "jean.dupont@email.com")
                .major("Informatique") // تحديد التخصص [2]
                .level("L1")          // تحديد المستوى الدراسي [2]
                .build();             // بناء الحساب النهائي [2]

        StudentAccount etudiant2 = new StudentAccount.Builder("E102", "Marie Curie", "marie.curie@email.com")
                .major("Mathématiques") // تحديد التخصص [2]
                .level("L2")           // تحديد المستوى الدراسي [2]
                .build();              // بناء الحساب النهائي [2]

        // 2. تنفيذ عملية الإضافة مرتين (Ajout) وتسجيل كل عملية في السجل [3]
        System.out.println("--- Execution des Ajouts ---");

        depot.add(etudiant1);
        logger.log("AJOUT : Étudiant (" + etudiant1.getId() + " - " + etudiant1.getFullName() + ") ajouté au dépôt."); // تسجيل عملية جديدة في السجل [3]
        System.out.println("AJOUT : " + etudiant1.getFullName() + " ajouté avec succès.");

        depot.add(etudiant2);
        logger.log("AJOUT : Étudiant (" + etudiant2.getId() + " - " + etudiant2.getFullName() + ") ajouté au dépôt."); // تسجيل عملية جديدة في السجل [3]
        System.out.println("AJOUT : " + etudiant2.getFullName() + " ajouté avec succès.");
        System.out.println();

        // 3. تنفيذ عملية التعديل (Modification) على الطالب الأول باستعمال Setter [1]
        System.out.println("--- Execution de la Modification ---");
        etudiant1.setMajor("Génie Logiciel"); // هنا استخدمت Setter لتعديل التخصص [1]
        logger.log("MODIFICATION : Changement de filière pour l'étudiant " + etudiant1.getId() + " vers Génie Logiciel."); // تسجيل التعديل [3]
        System.out.println("MODIFICATION : Filière de " + etudiant1.getFullName() + " mise à jour vers Génie Logiciel.");
        System.out.println();

        // 4. تنفيذ عملية الحذف مرتين لنفس الحساب (E102) لإظهار رسالة الخطأ في المرة الثانية
        System.out.println("--- Execution des Suppressions ---");

        // الحذف الأول: ينجح وتتم إزالته من المستودع
        boolean supprime1 = depot.removeIf(e -> e.getId().equals("E102"));
        if (supprime1) {
            logger.log("SUPPRESSION : Compte étudiant E102 supprimé avec succès du dépôt."); // تسجيل الحذف الناجح [3]
            System.out.println("SUPPRESSION : Compte E102 supprimé avec succès.");
        }

        // الحذف الثاني لنفس الحساب (E102): يفشل وتظهر رسالة الخطأ بالفرنسية
        boolean supprime2 = depot.removeIf(e -> e.getId().equals("E102"));
        if (!supprime2) {
            String msgErreur = "ERREUR SUPPRESSION : Le compte E102 n'existe pas ou a déjà été supprimé !";
            logger.log(msgErreur); // تسجيل محاولة الحذف الفاشلة في السجل [3]
            System.out.println(msgErreur); // عرض رسالة الخطأ بالفرنسية
        }
        System.out.println();

        // 5. عرض سجل العمليات كامل في نهاية البرنامج باستعمال showLogs() [3]
        System.out.println("=== JOURNAL COMPLET DES OPÉRATIONS (AUDIT LOGS) ===");
        logger.showLogs(); // عرض العمليات المسجلة بالوقت والتاريخ [3]
        System.out.println();

        // 6. عرض محتوى المستودع الحالي (Dépôt)
        System.out.println("=== CONTENU ACTUEL DU DÉPÔT ===");
        if (depot.isEmpty()) {
            System.out.println("Le dépôt est vide.");
        } else {
            for (StudentAccount st : depot) {
                System.out.println(st); // عرض معلومات الحساب [1]
            }
        }
        System.out.println();

        System.out.println("=== FIN DU PROGRAMME ===");
    }
}
