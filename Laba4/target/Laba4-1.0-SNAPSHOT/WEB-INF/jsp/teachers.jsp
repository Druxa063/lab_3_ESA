<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styles/styles.css">
</head>
<body>
<a href="index">Go to main page</a>
<table align="center">
    <form action="createTeacher" method="POST">
        Create a teacher <br>
        <div>
            <label>First name</label>
            <input type="text" id="firstName" name="firstName"/>
        </div>
        <div>
            <label>Last name</label>
            <input type="text" id="lastName" name="lastName"/>
        </div>
        <div>
            <label>Position</label>
            <input type="text" id="position" name="position"/>
        </div>
        <input type="submit" value="Create"/>
    </form>
    <thead>
        <th>First name</th>
        <th>Last name</th>
        <th>Position</th>
        <th>Lessons</th>
        <th>Delete</th>
        <th>Update</th>
    </thead>
    <tbody>
    <c:forEach items="${teachers}" var="teacher">
        <tr>
            <td>${teacher.firstName}</td>
            <td>${teacher.lastName}</td>
            <td>${teacher.position}</td>
            <td><a href="lessonsByTeacher?id=${teacher.id}">Lessons</a></td>
            <td>
                <form action="deleteTeacher" method="POST">
                    <input type="text" id="id" name="id" value="${teacher.id}" hidden="true"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
            <td><a href="teachersUpdate?id=${teacher.id}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>