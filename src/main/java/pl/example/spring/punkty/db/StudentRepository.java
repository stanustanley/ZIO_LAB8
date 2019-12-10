package pl.example.spring.punkty.db;

//import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentRow,Long>
        //CrudRepository<StudentRow,Long>
{

}