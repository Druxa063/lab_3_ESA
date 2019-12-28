<%--
  Created by IntelliJ IDEA.
  User: qwerty
  Date: 10.12.2019
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styles/styles.css"/>
</head>
<body>
<form action="teachersUpdate" method="POST">
    Create a teacher <br>
    <div>
        <label>First name</label>
        <input type="text" id="firstName" name="firstName" value="${teacher.firstName}"/>
    </div>
    <div>
        <label>Last name</label>
        <input type="text" id="lastName" name="lastName" value="${teacher.lastName}"/>
    </div>
    <div>
        <label>Position</label>
        <input type="text" id="position" name="position" value="${teacher.position}"/>
    </div>
    <input id="id" name="id" value="${teacher.id}" hidden="true"/>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
