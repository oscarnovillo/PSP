<%-- 
    Document   : newjsp
    Created on : 11-dic-2015, 9:42:37
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            response.getWriter().println("ECHO");
            for (int i = 0; i < 10; i++) {
        %>
    <for>
        
    </for>
        <div style="background: red">  JSP UNO</div>
        <%
            }
        %>
    </body>
</html>
