package dz.univ.uniaccounts.model;

public class Main {
    public static void main(String[] args) {
        StudentAccount original = new StudentAccount.Builder("20260001", "Ahmed", "ahmed@univ.dz")
                .major("Informatique")
                .level("L3")
                .build();

        original.addCourse("Design Patterns");
        original.addCourse("POO");

        StudentAccount cloneAcc = original.clone();
        cloneAcc.addCourse("Base de Données");

        System.out.println("Original: " + original.getCourses()); // [Design Patterns, POO]
        System.out.println("Clone: " + cloneAcc.getCourses());    // [Design Patterns, POO, Base de Données]
        System.out.println(original);
    }
}
