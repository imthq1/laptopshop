<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Create Product</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({ "display": "block" });
            });
        });
    </script>

</head>

<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Products</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item active">Product</li>
                </ol>

                <div class="container mt-5">
                    <h2 class="mb-4">Create a Product</h2>
                    <hr />

                    <form:form method="post" action="/admin/product/create" modelAttribute="product" enctype="multipart/form-data">
                    <div class="container overflow-hidden">
                        <div class="row gx-5">
                            <div class="col">
                                <div class="p-3 border bg-light">
                                    <div class="mb-3">
                                        <c:set var="errorName">
                                            <form:errors path="name" cssClass="invalid-feedback"/>
                                        </c:set>
                                        <label class="form-label">Name:</label>
                                        <form:input type="text" class="form-control ${not empty errorName?'is-invalid':''}" path="name"/>
                                            ${errorName}
                                    </div>
                                    <div class="mb-3">
                                        <c:set var="errorprice">
                                            <form:errors path="price" cssClass="invalid-feedback"/>
                                        </c:set>
                                        <label class="form-label">Price:</label>
                                        <form:input type="number" class="form-control ${not empty errorprice?'is-invalid':''}" path="price"/>
                                            ${errorprice}
                                    </div>
                                    <div class="mb-3">
                                        <c:set var="errorDetailDesc">
                                            <form:errors path="detailDesc" cssClass="invalid-feedback"/>
                                        </c:set>
                                        <label class="form-label">Detail description:</label>
                                        <form:input class="form-control ${not empty errorDetailDesc?'is-invalid':''}" path="detailDesc"/>
                                            ${errorDetailDesc}
                                    </div>
                                    <div class="mb-3">
                                        <c:set var="errorshortDesc">
                                            <form:errors path="shortDesc" cssClass="invalid-feedback"/>
                                        </c:set>
                                        <label class="form-label">Short description:</label>
                                        <form:input class="form-control ${not empty errorshortDesc?'is-invalid':''}" path="shortDesc"/>
                                            ${errorshortDesc}
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="p-3 border bg-light">
                                    <div class="mb-3">
                                        <label class="form-label">Factory:</label>
                                        <form:select class="form-select" path="fatory">
                                            <form:option value="Apple (MacBook)">Apple (MacBook)</form:option>
                                            <form:option value="Dell">Dell</form:option>
                                            <!-- Add more options as needed -->
                                        </form:select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Target:</label>
                                        <form:select class="form-select" path="target">
                                            <form:option value="Gaming">Gaming</form:option>
                                            <form:option value="Business">Business</form:option>
                                            <!-- Add more options as needed -->
                                        </form:select>
                                    </div>
                                    <div class="mb-3">
                                        <c:set var="errorQuantity">
                                            <form:errors path="quantity" cssClass="invalid-feedback"/>
                                        </c:set>
                                        <label class="form-label">Quantity:</label>
                                        <form:input type="number" class="form-control ${not empty errorQuantity?'is-invalid':''}" path="quantity"/>
                                            ${errorQuantity}
                                    </div>
                                    <div class="mb-3">
                                        <label for="avatarFile" class="form-label">Avatar:</label>
                                        <input type="file" class="form-control"
                                               id="avatarFile" accept=".png, .jpg, .jpeg"
                                               name="hoidanitFile"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="col-12 mb-3">
                            <img alt="avatar preview" style="max-height: 350px; display: none" id="avatarPreview">
                        </div>
                        <div class="col-12 mb-5">
                            <button type="submit" class="btn btn-primary">Create</button>
                        </div>
                    </form:form>
                </div>
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