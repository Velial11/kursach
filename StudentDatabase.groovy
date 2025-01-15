class StudentDatabase {
    List<Student> students = []

    void addStudent(Student student) {
        students.add(student)
    }

    void removeStudent(String fullName) {
        students.removeIf { it.getFullName() == fullName }
    }

    Student findStudentByFullName(String fullName) {
        return students.find { it.getFullName() == fullName }
    }

    List<Student> findStudentsByGroup(String group) {
        return students.findAll { it.group == group }
    }

    List<Student> findStudentsByName(String name) {
        return students.findAll { it.firstName.contains(name) || it.lastName.contains(name) }
    }

    List<Student> getAllStudents() {
        return students
    }

    List<Student> findStudentsByAverageGrade(double grade) {
        return students.findAll { it.getAverageGrade() >= grade }
    }
}
