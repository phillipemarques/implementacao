<%-- 
    Document   : index
    Created on : 15/12/2015, 11:43:15
    Author     : phillipe
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search in repositories</title>

        <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap-theme.min.css" />
        <script src="./resources/js/bootstrap.min.js"></script>

    </head>
    <body>
        <jsp:include page="top_bar.jsp"/>
        <jsp:include page="search_bar.jsp"/>
        <div class="content">
            <code>
                <pre>
                    ${requestScope.search}
                </pre>    
            </code>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./resources/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>