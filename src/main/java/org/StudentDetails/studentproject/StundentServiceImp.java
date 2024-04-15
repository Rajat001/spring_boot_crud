package org.StudentDetails.studentproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class StundentServiceImp implements StudentService {


    @Autowired
    private StudentRepository studentRepository;
    List<Student> students = new ArrayList<>(); 
    @Override
    // public String createStudent(Student student) {
    //     students.add(student);
    //     return "Added Successfully !!!";
    // }

    public String createStudent(Student student)
    {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(student, studentEntity);
        studentRepository.save(studentEntity);
        return "Saved Successfully";

    }

    @Override
    // public List<Student> readStudents() {
    //     return students;
    // }

    public List<Student> readStudents(){
        List<StudentEntity> studentList = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        
        for(StudentEntity studentEntity : studentList)
        {
            Student stud = new Student();
            stud.setId(studentEntity.getId());
            stud.setName(studentEntity.getName());
            stud.setRollNo(studentEntity.getRollNo());
            stud.setState(studentEntity.getState());
            students.add(stud);
        }
            return students;
    }

    @Override
    public Student readEmployee(Long id)
    {
        StudentEntity studeEntity = studentRepository.findById(id).get();

        Student stud = new Student();
        BeanUtils.copyProperties(studeEntity , stud);
        return stud;
    }

    @Override
    public boolean deleteStudent(Long id) {
    //    students.remove(id);

       StudentEntity stud = studentRepository.findById(id).get();
       studentRepository.delete(stud);
       return true;
    }

    @Override
    public String updateStudent(Long id, Student student) {

        StudentEntity existingStudent = studentRepository.findById(id).get();
        existingStudent.setName(student.getName());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setState(student.getState());

        studentRepository.save(existingStudent);
        return "Update Successfully";
    }


    
}
