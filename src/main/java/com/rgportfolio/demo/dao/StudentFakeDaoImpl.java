package com.rgportfolio.demo.dao;

import com.rgportfolio.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("FakeStudentDao")
public class StudentFakeDaoImpl implements StudentDao {

    private final Map<UUID, Student> database;

    public StudentFakeDaoImpl() {
        database = new HashMap<>();
    }

    @Override
    public int insertNewStudent(UUID studentId, Student student) {
        database.put(studentId, student);
        return 1;
    }

    @Override
    public Student selectStudentById(UUID studentId) {
        return database.get(studentId);
    }

    @Override
    public List<Student> selectAllStudents() {
        return new ArrayList<>(database.values());
    }

    @Override
    public int updateStudentById(UUID studentId, Student updatedStudent) {
        database.put(studentId, updatedStudent);
        return 1;
    }

    @Override
    public int deleteStudentById(UUID studentId) {
        database.remove(studentId);
        return 1;
    }
}
