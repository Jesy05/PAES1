package ni.edu.uam._raclases1p1.dao;

import ni.edu.uam._raclases1p1.models.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private List<Student> students;

    public StudentDAO() {
        students = new ArrayList<Student>();
    }

    public void add(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public double getAverageGrade() {
        double averageGrade = 0;
        if (students.isEmpty()) {
            return averageGrade;
        }

        int sum = 0;
        for (Student s : students) {
            sum += s.getGrade();
        }

        averageGrade = (double) sum / students.size();
        return averageGrade;
    }
}