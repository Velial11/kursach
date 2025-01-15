class ReportGenerator {
    static void generatePerformanceReport(List<Student> students) {
        students.each { student ->
            println "Студент: ${student.getFullName()}, Группа: ${student.group}, Средний балл: ${student.getAverageGrade()}"
            student.printGrades()
            println "-----------------------------"
        }
    }

    static void generateGroupReport(List<Student> students, String group) {
        def groupStudents = students.findAll { it.group == group }
        if (groupStudents.isEmpty()) {
            println "Студентов в группе $group нет."
        } else {
            println "Отчет по группе: $group"
            generatePerformanceReport(groupStudents)
        }
    }
}
