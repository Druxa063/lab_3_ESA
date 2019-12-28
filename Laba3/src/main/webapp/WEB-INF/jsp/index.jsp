<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Employee</title>
    <base href="${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="../util.js"></script>
</head>
<body onload="loadTable()">
<form name="saveForm">
    <input type="number" name="empno" hidden/><br>
    Ename : <input type="text" name="ename"/><br>
    Number department : <input type="number" name="deptno"/><br>
    <button onclick="save()">Submit</button>
</form>
<br/>

Number Worker : <input id="empnoSearch" type="text" name="empno"/>
<button onclick="search()">Search</button>
<br/>

<table border="1" id="tableEmployee">
    <thead>
    <th>EmpNO</th>
    <th>Ename</th>
    <th>Number department</th>
    <th>Dept name</th>
    <th>Location</th>
    <th>Delete</th>
    <th>Update</th>
    </thead>
    <tbody id="tableEmployeeBody">
    </tbody>
</table>
</body>
</html>