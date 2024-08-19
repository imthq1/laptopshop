
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard </title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Manage Products</h1>
                <ol class="breadcrumb mb-4">
                    <lí class="breadcrumb-item" ><a href="/admin">Dashboard</a></lí>
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="d-flex justify-content-between">
                    <h3>Table Products</h3>
                    <a href="/admin/product/create" class="btn btn-primary">Create a Product</a>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price </th>
                        <th scope="col">Factory</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="products" items="${products}">
                    <tr>
                        <th scope="row">${products.id}</th>
                        <td>${products.name}</td>
                        <td>${products.price}</td>
                        <td>${products.fatory}</td>
                        <td>
                            <a href="/admin/product/${products.id}" class="btn btn-sm btn-info">View</a>
                            <a href="/admin/product/update/${products.id}" class="btn btn-sm btn-warning">Update</a>
                            <a href="/admin/product/delete/${products.id}" class="btn btn-sm btn-danger">Delete</a>
                        </td>
                    </tr>
                    </c:forEach>

                </table>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
</body>

</html>