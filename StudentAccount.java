
package dz.univ.uniaccounts.model;

import java.util.ArrayList;      //هنا استخدمت interface ArrayList 
import java.util.List;     //interface List
// Pattern: Builder & Prototype
public class StudentAccount implements Cloneable {
//final-->هنا استخدمت ا لمعلومات نحبوا نحددوها عند إنشاء الحساب، وما نحبوش تتبدل بعد ذلك
    private final String id;
    private final String fullName;
    private final String email;
    //---------------------------
   //الحقول الاختيارية (Optional Fields)
    //لأننا نحتاجوا نقدروا نعدلوهم بعد إنشاء الحساب باستعمال Setters
    private String phone;
    private String level;
    private String major;
    private boolean scholarship;
    private List<String> courses;
//-------------------------------
  //Builder  
    private StudentAccount(Builder builder) {// حابين نجبروا المستخدم على إنشاء الحساب عن طريق(يعني ما نحبوش أي شخص ينشئ حساباً دون التحقق من المعلومات الإجبارية.)  Builder
 // نقل المعلومات الإجبارية من Builder إلى الحساب.      
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        ----------------
        // نقل المعلومات الاختيارية
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = new ArrayList<>(builder.courses);//أنشأنا نسخة مستقلة من قائمة المقررات لتجنب مشاركة نفس القائمة في الذاكرة، وهذا يسمى Defensive Copy.
    }
      //Prototype Pattern
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
    // GETTERS : دوال قراءة البيانات
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean isScholarship() { return scholarship; }
    //نرجع نسخة من القائمة لحمايتها من التعديل المباشر خارج الصنف
    public List<String> getCourses() {
        return new ArrayList<>(courses);
    }
// SETTERS : دوال تعديل البيانات
    public void setPhone(String phone) { this.phone = phone; }
    public void setLevel(String level) { this.level = level; }
    public void setMajor(String major) { this.major = major; }
    public void setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
    }
 // تغيير قائمة المقررات.
    public void setCourses(List<String> courses) {
        this.courses = (courses != null)
                ? new ArrayList<>(courses)
                : new ArrayList<>();
    }
// ADD COURSE : إضافة مقرر دراسي
    public void addCourse(String course) {
        if (course != null && !course.trim().isEmpty()) {
            this.courses.add(course);
        }
    }
// TOSTRING : عرض معلومات الحساب
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
// BUILDER PATTERN : نمط الباني
    public static class Builder {
    // الحقول الإجبارية.
        private final String id;
        private final String fullName;
        private final String email;
// الحقول الاختيارية والقيم الافتراضية
        private String phone = null;
        private String level = "L1";
        private String major = "";
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();
  // نطلب المعلومات الإجبارية الثلاثة فقط.
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
// FLUENT INTERFACE : الاستدعاء المتسلسل
        public Builder phone(String phone) {// تحديد رقم الهاتف.
            this.phone = phone;
            return this;
        }

        public Builder level(String level) {// تحديد المستوى الدراسي.
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

        public Builder major(String major) {// تحديد التخصص.
            this.major = major;
            return this;
        }

        public Builder scholarship(boolean scholarship) {// تحديد هل الطالب يستفيد من المنحة أم لا.
            this.scholarship = scholarship;
            return this;
        }

        public Builder courses(List<String> courses) {// تحديد قائمة المقررات.
            if (courses != null) {
                this.courses = new ArrayList<>(courses);
            }
            return this;
        }

        public StudentAccount build() {// BUILD : إنشاء الحساب النهائي
            return new StudentAccount(this);
        }
    }
}
