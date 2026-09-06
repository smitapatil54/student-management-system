<%@ page import="java.util.List" %>
<%@ page import="com.student.Student" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        input { padding: 8px; margin: 4px; }
        button { padding: 8px 14px; }
        table { border-collapse: collapse; width: 100%; max-width: 800px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background: #f2f2f2; }
    </style>
</head>
<body>
    <h1>Student Management System</h1>

    <h2>Add Student</h2>
    <form method="post" action="<%= request.getContextPath() %>/students">
        <input type="hidden" name="action" value="add">
        <input type="number" name="studentId" placeholder="Student ID" required>
        <input type="text" name="name" placeholder="Name" required>
        <input type="text" name="course" placeholder="Course" required>
        <input type="number" step="0.01" name="marks" placeholder="Marks" required>
        <button type="submit">Add Student</button>
    </form>

    <h2>Students</h2>
    <table>
        <tr><th>ID</th><th>Name</th><th>Course</th><th>Marks</th><th>Action</th></tr>
<%
    if (students != null) {
        for (Student s : students) {
%>
        <tr>
            <td><%= s.getStudentId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getCourse() %></td>
            <td><%= s.getMarks() %></td>
            <td>
                <form method="post" action="<%= request.getContextPath() %>/students" style="margin:0">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
<%
        }
    }
%>
    </table>
</body>
</html>
