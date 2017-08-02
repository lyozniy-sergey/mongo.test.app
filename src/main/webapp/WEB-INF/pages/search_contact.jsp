<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h2><spring:message code="table.contact.search"/></h2>
<div class="searchform">
    <form:form method="GET" action="/search/contact" modelAttribute="contactSearchCriteria" id="searchContact">
        <table border="0" width="400">
            <tr>
                <td><form:label path="name"><spring:message code="table.contact.name"/></form:label></td>
                <td><form:input path="name"/></td>
                <%--<td><input type="submit" name="submit" value="Search"/></td>--%>
                <td><input type="submit" name="submit" value="Search" onclick="document.getElementById('searchContact').action='/search/contact'"/></td>
            </tr>
        </table>
    </form:form>
</div>
<c:if test="${! empty contacts}">
    <form:form method="POST" action="/add/contact/bank" modelAttribute="contactHolder" id="addToBank">
        <table id="contacts" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th></th>
                <th><spring:message code="table.contact.id"/></th>
                <th><spring:message code="table.contact.firstName"/></th>
                <th><spring:message code="table.contact.lastName"/></th>
            </tr>
            <thead>
            <tbody>
            <c:forEach var="contact" items="${contacts}">
            <tr>
                    <%--<td><form:input path="ids" type="checkbox" name="selectedItems" value="${contact.id}"/></td>--%>
                <td><form:checkbox path="contacts" value="${contact.id}"/></td>
                <td>${contact.id}</td>
                <td>${contact.name}</td>
                <td>${contact.lastName}</td>
            <tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" value="<spring:message code="table.contact.add"/>"/>
    </form:form>
</c:if>
</body>