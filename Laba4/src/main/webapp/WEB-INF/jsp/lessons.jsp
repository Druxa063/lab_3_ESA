<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styles/styles.css">
</head>
<body>
<a href="index">Go to main page</a>
<table align="center">
    <form action="createLesson" method="POST">
        Create a lesson <br>
        <div>
            <label>Name</label>
            <input type="text" id="name" name="name"/>
        </div>
        <div>
            <label>Date</label>
            <input type="datetime-local" id="startTime" name="startTime"/>
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

    </form>
    <thead>
        <th>Name</th>
        <th>Start time</th>
        <c:if test="${!byGroup}">
        <th>Group</th>
        </c:if>
        <c:if test="${!byTeacher}">
        <th>Teacher</th>
        </c:if>
        <th>Delete</th>
        <th>Update</th>
    </thead>
    <tbody>
    <c:forEach items="${lessons}" var="lesson">
        <tr>
            <td>${lesson.name}</td>
            <td>${lesson.startTime}</td>
            <c:if test="${!byGroup}">
            <td><a href="groupsByLesson?id=${lesson.group.id}">${lesson.group.name}</a></td>
            </c:if>
            <c:if test="${!byTeacher}">
            <td><a href="teachersByLesson?id=${lesson.teacher.id}">${lesson.teacher.firstName} ${lesson.teacher.lastName}</a></td>
            </c:if>
            <td>
                <form action="deleteLesson" method="POST">
                    <input type="text" id="id" name="id" value="${lesson.id}" hidden="true"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
            <td><a href="lessonsUpdate?id=${lesson.id}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>