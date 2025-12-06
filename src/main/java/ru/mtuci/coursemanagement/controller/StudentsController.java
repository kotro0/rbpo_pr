package ru.mtuci.coursemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mtuci.coursemanagement.repository.StudentRepository;

@Controller
public class StudentsController {

    private final StudentRepository studentRepository;

    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }
}
