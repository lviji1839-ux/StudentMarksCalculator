package com.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class MarksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String mark1 = request.getParameter("mark1");
        String mark2 = request.getParameter("mark2");
        String mark3 = request.getParameter("mark3");

        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be empty.");
            }

            int m1 = parseMark(mark1);
            int m2 = parseMark(mark2);
            int m3 = parseMark(mark3);

            if (!isValidMark(m1) || !isValidMark(m2) || !isValidMark(m3)) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }

            int total = m1 + m2 + m3;
            double average = total / 3.0;
            String grade;

            if (average >= 90) {
                grade = "A";
            } else if (average >= 75) {
                grade = "B";
            } else if (average >= 60) {
                grade = "C";
            } else if (average >= 40) {
                grade = "D";
            } else {
                grade = "Fail";
            }

            request.setAttribute("studentName", name.trim());
            request.setAttribute("total", total);
            request.setAttribute("average", average);
            request.setAttribute("grade", grade);
            request.getRequestDispatcher("/result.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            showError(response, "Please enter whole-number marks for all three subjects.");
        } catch (IllegalArgumentException e) {
            showError(response, e.getMessage());
        }
    }

    private int parseMark(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("Missing mark");
        }
        return Integer.parseInt(value.trim());
    }

    private boolean isValidMark(int mark) {
        return mark >= 0 && mark <= 100;
    }

    private void showError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html;charset=UTF-8");
        String safeMessage = escapeHtml(message);
        response.getWriter().println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Input Error</title></head>");
        response.getWriter().println("<body style='font-family:Arial;text-align:center;margin-top:60px;background:#eef2f7'>");
        response.getWriter().println("<div style='background:white;display:inline-block;padding:25px;border-radius:10px'>");
        response.getWriter().println("<h2>Invalid Input</h2><p>" + safeMessage + "</p>");
        response.getWriter().println("<a href='index.html'>Go Back</a></div></body></html>");
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
