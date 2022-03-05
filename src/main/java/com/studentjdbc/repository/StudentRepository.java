package com.studentjdbc.repository;

import com.studentjdbc.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Student addStudent(Student student) {
        String insert = "INSERT INTO students(firstname,lastname,age,email) VALUES(?,?,?,?)";
        jdbcTemplate.update(
                insert,
                student.getFirstname(),
                student.getLastname(),
                student.getAge(),
                student.getEmail());
        return student;
    }

    public Student updateStudent(Student student) {
        String update = "UPDATE students SET firstname = ?,lastname = ?, age = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(update,
                student.getFirstname(),
                student.getLastname(),
                student.getAge(),
                student.getEmail(),
                student.getId());

        return student;
    }

    public Student getStudentById(Long id) {
        String get = "SELECT * FROM students WHERE id=?";
        RowMapper<Student> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Student.class);
        Student student = jdbcTemplate.queryForObject(get, beanPropertyRowMapper, id);
        return student;
    }

    public int deleteStudent(Long id) {
        String delete = "DELETE FROM students WHERE id=?";
        return jdbcTemplate.update(delete, id);
    }

    public List<Student> getAllStudents() {
        String sqlSelect = "SELECT * FROM students";
        BeanPropertyRowMapper<Student> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Student.class);
        List<Student> students = jdbcTemplate.query(sqlSelect, beanPropertyRowMapper);

        return students;

    }
}





