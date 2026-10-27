<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    private String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
%>
<%
    String studentName = (String) request.getAttribute("studentName");
    Object totalValue = request.getAttribute("total");
    Object averageValue = request.getAttribute("average");
    String grade = (String) request.getAttribute("grade");

    if (studentName == null || totalValue == null || averageValue == null || grade == null) {
        response.sendRedirect("index.html");
        return;
    }
    int total = ((Number) totalValue).intValue();
    double average = ((Number) averageValue).doubleValue();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef2f7;
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .result-card {
            background: white;
            width: 100%;
            max-width: 420px;
            padding: 30px;
            border-radius: 14px;
            box-shadow: 0 8px 24px rgba(0,0,0,.10);
        }
        h1 { color: #1d4ed8; text-align: center; margin-top: 0; }
        .row {
            display: flex;
            justify-content: space-between;
            gap: 12px;
            padding: 13px 0;
            border-bottom: 1px solid #e5e7eb;
        }
        .value { font-weight: bold; text-align: right; }
        .grade { font-size: 22px; color: #15803d; }
        a {
            display: block;
            text-align: center;
            margin-top: 24px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            padding: 12px;
            border-radius: 7px;
        }
    </style>
</head>
<body>
<div class="result-card">
    <h1>Student Result</h1>
    <div class="row"><span>Student Name</span><span class="value"><%= escapeHtml(studentName) %></span></div>
    <div class="row"><span>Total Marks</span><span class="value"><%= total %> / 300</span></div>
    <div class="row"><span>Average Marks</span><span class="value"><%= String.format(java.util.Locale.US, "%.2f", average) %></span></div>
    <div class="row"><span>Final Grade</span><span class="value grade"><%= escapeHtml(grade) %></span></div>
    <a href="index.html">Calculate Another Result</a>
</div>
</body>
</html>
