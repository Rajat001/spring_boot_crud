package org.StudentDetails.studentproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    //List<Student> students = new ArrayList<>();

    @Autowired
    StudentService studentService; // Using IOC Controller ...

    @PostMapping("students")
    public String createStudent(@RequestBody Student student){
            //students.add(student);
            return studentService.createStudent(student);
            // return "Saved Success";
    }

    @GetMapping("students")
    public List<Student> readStudents()
    {
        //return students;
        return studentService.readStudents();
    }
    
    // Single Student Data is Fetched 
    @GetMapping("students/{id}")
    public Student readStudent(@PathVariable Long id)
    {
        return studentService.readEmployee(id);
    }


    @DeleteMapping("students/{id}")
    public String deleteStudent(@PathVariable Long id){
        if(studentService.deleteStudent(id))
            return "Deleted Success";
        return "Not Found";    
    }

    @PutMapping("students/{id}")
    public String putMethod(@PathVariable Long id , @RequestBody Student student)
    {
        return studentService.updateStudent(id, student);
    }
}
