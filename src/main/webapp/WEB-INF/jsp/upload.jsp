<%--
  Created by IntelliJ IDEA.
  User: Vladimir
  Date: 07.08.2017
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<div>
    <h3> Choose File to Upload </h3>
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="upload"/>
    </form>
</div>
<button onclick="window.history.back()">Cancel</button>
</body>
</html>
