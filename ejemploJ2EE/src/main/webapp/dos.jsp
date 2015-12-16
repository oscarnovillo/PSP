<%-- 
    Document   : dos
    Created on : 11-dic-2015, 10:22:38
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
        <h1>JSP DOS</h1>
        <%
            for (int i = 0; i < 5; i++) {
        %>
        <h2>
        <%=request.getSession().getAttribute("numero")%>
        </h2>
        <%
            }
        %>

        <%
            response.getWriter().println(request.getSession().getAttribute("numero"));
            response.getWriter().println(getServletContext().getAttribute("contador"));
        %>
    </body>
</html>
