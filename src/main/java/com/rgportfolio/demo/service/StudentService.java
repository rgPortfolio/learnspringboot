package com.rgportfolio.demo.service;

import com.rgportfolio.demo.dao.StudentDao;
import com.rgportfolio.demo.exceptions.StudentAlreadyExistsException;
import com.rgportfolio.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDao studentDao;

    //is there a manual wire?
    //what happens if there are two fakestudentdaos ?
    @Autowired
    public StudentService(@Qualifier("FakeStudentDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int persistNewStudent(UUID studentId, Student student) {
        studentId = studentId == null ? UUID.randomUUID() : studentId;

        if (getStudentById(studentId) != null) {
            throw new StudentAlreadyExistsException("Student already exists: " + student.getFirstName());
        }

        return studentDao.insertNewStudent(studentId, student);
    }

    public Student getStudentById(UUID studentId) {
        return studentDao.selectStudentById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public int updateStudentById(UUID studentId, Student updatedStudent) {
        return studentDao.updateStudentById(studentId, updatedStudent);
    }

    public int deleteStudentById(UUID studentId) {
        Student studentById = getStudentById(studentId);
        if (studentById == null) {
            throw new IllegalStateException();
        }
        return studentDao.deleteStudentById(studentId);
    }
}
