<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
    <a href="index">Go to main page</a>
        <form action="createGroup" method="POST">
            Create a group <br>
            <div>
                <label>Name</label>
                <input type="text" id="name" name="name"/>
            </div>
                <input type="submit" value="Create"/>

        </form>
        <table align="center">
            <thead>

                    <th>Name</th>
                    <th>Lessons</th>
                    <th>Delete</th>
                    <th>Update</th>
            </thead>
            <tbody>
                <c:forEach items="${groups}" var="group">
                    <tr>
                        <td>${group.name}</td>
                        <td><a href="lessonsByGroup?id=${group.id}">${group.name}'s lessons</a></td>
                        <td>
                            <form action="deleteGroup" method="POST">
                                <input type="text" id="id" name="id" value="${group.id}" hidden="true"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                        <td><a href="groupsUpdate?id=${group.id}">Update</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>