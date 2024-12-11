package com.example.quan_ly_thu_vien.Service;

import com.example.quan_ly_thu_vien.Model.Student;
import com.example.quan_ly_thu_vien.Repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();

    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }
}
