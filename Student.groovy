class Student {
    String firstName
    String lastName
    String group
    List<Grade> grades = []

    Student(String firstName, String lastName, String group) {
        this.firstName = firstName
        this.lastName = lastName
        this.group = group
    }

    void addGrade(String subject, int grade) {
        this.grades.add(new Grade(subject, grade))
    }

    void updateGrade(String subject, int newGrade) {
        def grade = this.grades.find { it.subject == subject }
        if (grade) {
            grade.grade = newGrade
        }
    }

    void updateGroup(String newGroup) {
        this.group = newGroup
    }

    String getFullName() {
        return "$firstName $lastName"
    }

    double getAverageGrade() {
        if (grades.isEmpty()) return 0
        return grades.sum { it.grade } / grades.size()
    }

    void printGrades() {
        grades.each { grade ->
            println "    ${grade.subject}: ${grade.grade}"
        }
    }
}
