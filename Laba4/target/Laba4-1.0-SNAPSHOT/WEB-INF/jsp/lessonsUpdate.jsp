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
<form action="lessonsUpdate" method="POST">
    Update a lesson <br>
    <div>
        <label>Name</label>
        <input type="text" id="name" name="name" value="${lesson.name}"/>
    </div>
    <div>
        <label>Date</label>
        <input type="datetime-local" id="startTime" name="startTime" value="${lesson.startTime}"/>
    </div>
    <div>
        <label>Group</label>
        <select name="group" id="group">
            <c:forEach items="${groups}" var="group">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label>Teacher</label>
        <select name="teacher" id="teacher">
            <c:forEach items="${teachers}" var="teacher">
                <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="Create"/>
    <input type="text" id="id" name="id" value="${lesson.id}" hidden="true"/>

</form>
</body>
</html>
