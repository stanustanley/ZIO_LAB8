package pl.example.spring.punkty;


import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.spring.punkty.db.StudentRepository;
import io.vavr.collection.List;
import pl.example.spring.punkty.db.StudentRow;

import java.util.Optional;
import java.util.function.Function;

@Service
public class StudentService {
    private final StudentRepository repository;


    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    List<Student> getStudents() {
        return List.ofAll(this.repository.findAll())
                .map(StudentRow::toStudent);
        //throw new UnsupportedOperationException();
    }

//    private Function<StudentRow, Student> getStudentRowStudentFunction() {
//        return dbObj->new Student(dbObj.getId(),dbObj.getName(),dbObj.getNumber(),dbObj.getGroup());
//    }
    @Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber)
    {
        final Optional<StudentRow> student = this.repository.findById(studentId);
        return student.map(c ->
                {
                    c.setNumber(newNumber);
                    //repository.save(c);
                    return c.toStudent();
                }
        );
    }

    Student addStudent(final NewStudent newStudent) {
        //StudentRow created = this.repository.save(new StudentRow(newStudent.name,newStudent.number,newStudent.group));
        return this.repository.save(new StudentRow(newStudent.name,newStudent.number,newStudent.group)).toStudent();
        //return getStudentRowStudentFunction().apply(created);
    }
}
