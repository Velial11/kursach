import java.util.Scanner

class StudentManagementSystem {
    static StudentDatabase database = new StudentDatabase()

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)
        while (true) {
            println "Меню:"
            println "1. Добавить студента"
            println "2. Редактировать данные студента"
            println "3. Удалить студента"
            println "4. Найти студента"
            println "5. Поиск студентов по средней оценке"
            println "6. Сгенерировать отчет по успеваемости"
            println "7. Сгенерировать отчет по группе"
            println "8. Выйти"
            print "Выберите опцию: "
            String choice = scanner.nextLine()

            switch (choice) {
                case "1":
                    addStudent(scanner)
                    break
                case "2":
                    editStudent(scanner)
                    break
                case "3":
                    removeStudent(scanner)
                    break
                case "4":
                    searchStudent(scanner)
                    break
                case "5":
                    searchByAverageGrade(scanner)
                    break
                case "6":
                    generateReport()
                    break
                case "7":
                    generateGroupReport(scanner)
                    break
                case "8":
                    println "Выход..."
                    return
                default:
                    println "Некорректный выбор. Попробуйте снова."
            }
        }
    }

    static void addStudent(Scanner scanner) {
        print "Введите имя студента: "
        String firstName = scanner.nextLine()
        print "Введите фамилию студента: "
        String lastName = scanner.nextLine()
        print "Введите группу студента: "
        String group = scanner.nextLine()

        Student student = new Student(firstName, lastName, group)
        database.addStudent(student)
        println "Студент ${student.getFullName()} добавлен!"
    }

    static void editStudent(Scanner scanner) {
        print "Введите фамилию и имя студента для редактирования: "
        String fullName = scanner.nextLine()
        Student student = database.findStudentByFullName(fullName)

        if (student) {
            println "Выбрали студента: ${student.getFullName()}"
            println "1. Изменить группу"
            println "2. Изменить оценку"
            print "Выберите опцию: "
            String option = scanner.nextLine()
            if (option == "1") {
                print "Введите новую группу: "
                String newGroup = scanner.nextLine()
                student.updateGroup(newGroup)
                println "Группа изменена на $newGroup."
            } else if (option == "2") {
                print "Введите название предмета: "
                String subject = scanner.nextLine()
                print "Введите новую оценку: "
                int grade = scanner.nextInt()
                scanner.nextLine()  // consume newline
                student.updateGrade(subject, grade)
                println "Оценка для предмета $subject обновлена на $grade."
            }
        } else {
            println "Студент не найден."
        }
    }

    static void removeStudent(Scanner scanner) {
        print "Введите фамилию и имя студента для удаления: "
        String fullName = scanner.nextLine()
        database.removeStudent(fullName)
        println "Студент $fullName удален."
    }

    static void searchStudent(Scanner scanner) {
        print "Введите фамилию или имя для поиска: "
        String name = scanner.nextLine()
        List<Student> foundStudents = database.findStudentsByName(name)

        if (foundStudents.isEmpty()) {
            println "Студенты не найдены."
        } else {
            foundStudents.each { student ->
                println "Студент: ${student.getFullName()}, Группа: ${student.group}"
            }
        }
    }

    static void searchByAverageGrade(Scanner scanner) {
        print "Введите минимальную среднюю оценку для поиска: "
        double grade = scanner.nextDouble()
        List<Student> foundStudents = database.findStudentsByAverageGrade(grade)

        if (foundStudents.isEmpty()) {
            println "Студенты с такой средней оценкой не найдены."
        } else {
            foundStudents.each { student ->
                println "Студент: ${student.getFullName()}, Группа: ${student.group}, Средний балл: ${student.getAverageGrade()}"
            }
        }
    }

    static void generateReport() {
        println "Генерация отчета по успеваемости..."
        ReportGenerator.generatePerformanceReport(database.getAllStudents())
    }

    static void generateGroupReport(Scanner scanner) {
        print "Введите группу для генерации отчета: "
        String group = scanner.nextLine()
        ReportGenerator.generateGroupReport(database.getAllStudents(), group)
    }
}
]
