<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<section>
    <h3>Images</h3>

    <form method="post" action="filter">

        <dl>
            <dt>filter:</dt>
            <dd><input type="text" name="title" value="${param.title}"></dd>
        </dl>
        <button type="submit">search</button>
    </form>

    <hr>
    <h4><a href="upload">upload image</a></h4>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>category</th>
            <th>title</th>
            <th>description</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${images}" var="image">
            <jsp:useBean id="image" scope="page" type="com.imagebank.model.Image"/>
            <tr>
                <td>${image.category}</td>
                <td>${image.title}</td>
                <td>${image.description}</td>
                <td><a href="update?id=${image.id}">update</a></td>
                <td><a href="delete?id=${image.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>