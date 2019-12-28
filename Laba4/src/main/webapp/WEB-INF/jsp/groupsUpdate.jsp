<%--
  Created by IntelliJ IDEA.
  User: qwerty
  Date: 10.12.2019
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styles/styles.css"/>
</head>
<body>
    <form action="groupsUpdate" method="POST">
        Update a group <br>
        <div>
            <label>Name</label>
            <input type="text" id="name" name="name" value="${group.name}"/>
            <input type="text" id="id" name="id" value="${group.id}" hidden="true">
        </div>
        <input type="submit" value="Update"/>
    </form>
</body>
</html>
