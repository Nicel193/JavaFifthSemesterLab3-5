<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style type="text/css">
    .content {
        max-width: 800px;
        margin: auto;
    }
</style>
<head>
    <meta charset="UTF-8">
    <title>${titleHospitalDepartment} (WEB LAB#2)</title>
</head>
<body>
<div class="content">
    <caption>
        <br>
        <h1><c:out value="${titleHospitalDepartment}"></c:out> (WEB LAB#2)</h1>
    </caption>
    <br>
    <p style="color: red;">${errorString}</p>
    <br>
    <form action="hospitalDepartment" method="post">
        <input type="hidden" name="idDep" value="${hospitalDep.id}" required>
        <p>
            <label for="nameDep"> nameDep </label></p>
        <p>
            <input type="text" name="nameDep" id="nameDep" value="${hospitalDep.nameDep}"
                   pattern="[A-Z][a-zA-Z]*"
                   required/>
        </p>
        <p>
            <label for="nameSDep"> nameSDep </label></p>
        <p>
            <input type="text" name="nameSDep" id="nameSDep" value="${hospitalDep.nameSDep}"
                   pattern="[A-Z][a-zA-Z]*"
                   required/>
        </p>
        <p>
            <label for="codeBuilding"> codeBuilding </label></p>
        <p>
            <input type="text" name="codeBuilding" id="codeBuilding" value="${hospitalDep.codeBuilding}"
                   pattern="[A-Z0-9]*"
                   required/>
        </p>
        <p>
            <label for="floor"> floor </label></p>
        <p>
            <input type="number" name="floor" id="floor" value="${hospitalDep.floor}"
                   min = "1" max = "10"
                   required/>
        </p>
        <p>
            <label for="boxCount"> boxCount </label></p>
        <p>
            <input type="number" name="boxCount" id="boxCount" value="${hospitalDep.boxCount}"
                   min = "1"
                   required>
        </p>
        <p>
            <button type="submit"> Зберігти</button>
            <a href="hospitalDepartments"> Відміна </a>
        </p>
    </form>
</div>
</body>
</html>
