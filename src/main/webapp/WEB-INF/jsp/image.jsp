<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<section>
    <jsp:useBean id="image" type="com.imagebank.model.Image" scope="request"/>
    <h3><c:if test="${image.isNew()}">Add</c:if></h3>
    <h4><c:if test="${image.isNew()}">${message}</c:if></h4>
    <h3><c:if test="${!image.isNew()}">Edit</c:if></h3>
    <h4><c:if test="${!image.isNew()}">${fileName}</c:if></h4>
</section>

<section>
    <hr>
    <form method="post" action="images">
        <input type="hidden" name="id" value="${image.id}">
        <c:if test="${!image.isNew()}"><input type="hidden" name="link" value=${image.link}</c:if>
        <c:if test="${image.isNew()}"><input type="hidden" name="link" value=${link}</c:if>

        <dl>
            <dt>category:</dt>
            <dd><input type="text" value="${image.category.name()}" name="category"></dd>
        </dl>
        <dl>
            <dt>title:</dt>
            <dd><input type="text" value="${image.title}" name="title"></dd>
        </dl>
        <dl>
            <dt>description:</dt>
            <dd><input type="text" value="${image.description}" name="description"></dd>
        </dl>

        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
