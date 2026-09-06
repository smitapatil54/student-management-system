package com.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("students", studentService.getAllStudents());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(request.getParameter("studentId"));
            String name = request.getParameter("name");
            String course = request.getParameter("course");
            double marks = Double.parseDouble(request.getParameter("marks"));

            studentService.addStudent(new Student(id, name, course, marks));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("studentId"));
            studentService.deleteStudentById(id);
        }

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
