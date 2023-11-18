<%@ page import="javax.swing.plaf.synth.SynthTextAreaUI" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style.css">
<head>
    <title>HospitalDepartment list (WEB Lab#2)</title>
</head>
<body>
<div class="content">
    <h1>HospitalDepartment list (WEB Lab#2)</h1>
    <br>
    <table style="border: #000000">
        <td style="border: #000000">
            <%--            Кнопка ДОДАТИ       --%>
            <form action="hospitalDepartment" method="GET">
                <input type="hidden" name="idDep" value="0">
                <button accesskey="d"> Додати</button>
            </form>
        </td>
        <td style="border: #000000">
            <%--            Фільтр              --%>
            <span>Filter by CodeBuilding:</span>
            <select id="CodeBuilding_filter" name="CodeBuilding_filter" onchange="filterByCodeBuilding()">
                <option value="all">all</option>
            </select>
        </td>
        <td style="border: #000000">
            <button onclick="sortTableByInt(3)"> Sort by floor</button>
            <button onclick="sortTableByInt(4)"> Sort by boxCount</button>
        </td>
        <td style="border: #000000">
            <b><label for="search-text"> Find By NameDep </label></b>
            <input type="text"
                   placeholder="Почніть вводити nameDep" id="search-text"
                   onkeyup="tableSearch()">
        </td>
        <td style="border: #000000">
            <form action="hospitalDepartments" method="get">
                <%--                <input type="hidden" name="progLangFilter" value="">--%>
                <%--                <input type="hidden" name="pl_filter" value="">--%>
                <%--                <input type="hidden" name="gender_filter" value="">--%>
                <button type="submit" accesskey="x">Reset</button>
            </form>
        </td>
    </table>
    <br>

    <table id="hospitalDepartments-table">
        <tr>
            <th>NameDep</th>
            <th>NameSDep</th>
            <th>CodeBuilding</th>
            <th>Floor</th>
            <th>BoxCount</th>
            <th colspan="2">Actions</th>
        </tr>
        <c:forEach var="hd" items="${mylist}">
            <tr>
                <td><c:out value="${hd.getNameDep()}"></c:out></td>
                <td><c:out value="${hd.getNameSDep()}"></c:out></td>
                <td><c:out value="${hd.getCodeBuilding()}"></c:out></td>
                <td><c:out value="${hd.getFloor()}"></c:out></td>
                <td><c:out value="${hd.getBoxCount()}"></c:out></td>

                    <%-- Комірка із кнопками редагування та вилучення --%>
                <td>
                    <form action="hospitalDepartment" method="get">
                        <input type="hidden" name="idDep" value="${hd.id}">
                        <input type="submit" value="Редагувати">
                    </form>
                </td>
                <td>
                    <form action="del_hospitalDepartment" method="post">
                        <input type="hidden" name="idDep" value="${hd.id}">
                        <input type="submit" value="Вилучити" onclick="return confirmation()">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/script.js"></script>
</html>