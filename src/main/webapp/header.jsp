<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%
        if( request.getAttribute("title") == null){
            out.print("Homepage");
        }else{
            out.print(request.getAttribute("title"));
        }
    %></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        .pagination {
            display: inline-block;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: white;
        }

        .pagination a:hover:not(.active) {background-color: #ddd;}
    </style>
</head>
<body>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">e gadgets</a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/products">Products <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/myproducts">My Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/myproducts/new">New Product</a>
            </li>
        </ul>
        <div class="navbar-nav ml-auto">
            <%
                String userId = null;
                if(request.getSession().getAttribute("user_id") != null){
                    userId = request.getSession().getAttribute("user_id").toString();
                    out.print(userId);
                    out.print("<a class=\"nav-link\" href='" + request.getContextPath() + "/logout'>Logout</a>");
                }else{
                    out.print("<a class=\"nav-link\" href='" + request.getContextPath() + "/login'>Login</a>");
                    out.print("<a class=\"nav-link\" href='" + request.getContextPath() + "/register'>Register</a>");
                }
            %>
        </div>
    </nav>
</header>