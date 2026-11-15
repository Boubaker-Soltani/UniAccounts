
package dz.univ.uniaccounts.model;

import java.util.ArrayList;
import java.util.List;

// Pattern: Builder & Prototype
public class StudentAccount implements Cloneable {

    private final String id;
    private final String fullName;
    private final String email;
    private String phone;
    private String level;
    private String major;
    private boolean scholarship;
    private List<String> courses;

    private StudentAccount(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = new ArrayList<>(builder.courses);
    }

    @Override
    public StudentAccount clone() {
        try {
            StudentAccount copy = (StudentAccount) super.clone();
            copy.courses = new ArrayList<>(this.courses);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Account cloning failed: " + e.getMessage());
        }
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean isScholarship() { return scholarship; }

    public List<String> getCourses() {
        return new ArrayList<>(courses);
    }

    public void setPhone(String phone) { this.phone = phone; }
    public void setLevel(String level) { this.level = level; }
    public void setMajor(String major) { this.major = major; }
    public void setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
    }

    public void setCourses(List<String> courses) {
        this.courses = (courses != null)
                ? new ArrayList<>(courses)
                : new ArrayList<>();
    }

    public void addCourse(String course) {
        if (course != null && !course.trim().isEmpty()) {
            this.courses.add(course);
        }
    }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", major='" + major + '\'' +
                ", scholarship=" + scholarship +
                ", courses=" + courses +
                '}';
    }

    public static class Builder {

        private final String id;
        private final String fullName;
        private final String email;

        private String phone = null;
        private String level = "L1";
        private String major = "";
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Registration number (id) is required and non-empty!");
            }

            if (fullName == null || fullName.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Full name is required and non-empty!");
            }

            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException(
                        "Email must contain '@'!");
            }

            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder level(String level) {
            if (level != null &&
                    (level.equals("L1") || level.equals("L2") ||
                     level.equals("L3") || level.equals("M1") ||
                     level.equals("M2"))) {
                this.level = level;
            } else {
                this.level = "L1";
            }
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder scholarship(boolean scholarship) {
            this.scholarship = scholarship;
            return this;
        }

        public Builder courses(List<String> courses) {
            if (courses != null) {
                this.courses = new ArrayList<>(courses);
            }
            return this;
        }

        public StudentAccount build() {
            return new StudentAccount(this);
        }
    }
}
