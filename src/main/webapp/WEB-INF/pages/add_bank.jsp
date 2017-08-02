<%@ page import="org.mongo.domain.ContactSearchCriteria" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="table.bank.add"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h2><spring:message code="table.bank.add"/></h2>
<form:form method="POST" action="/add/bank" modelAttribute="bank" id="add_bank">
    <form:hidden path="id" />
    <%--<form:hidden path="contacts" />--%>
    <table class="table table-striped table-bordered">
        <tr>
            <td><spring:message code="table.bank.name"/>:</td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.bank.info"/>:</td>
            <td><form:input path="info" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.address.country"/>:</td>
            <td><form:input path="address.country" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.address.city"/>:</td>
            <td><form:input path="address.city" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.address.state"/>:</td>
            <td><form:input path="address.state" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.address.street"/>:</td>
            <td><form:input path="address.street" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.address.house"/>:</td>
            <td><form:input path="address.house" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.address.apartment"/>:</td>
            <td><form:input path="address.apartment" /></td>
        </tr>
    </table>
    <c:if test="${! empty bank.contacts}">
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
            <c:forEach var="contact" items="${bank.contacts}">
            <tr>
                <td><form:checkbox path="newContactIds" value="${contact.id}" checked="checked"/></td>
                <%--<td><form:label path="contact.id" /></td>--%>
                <%--<td><form:label path="contact.name" /></td>--%>
                <%--<td><form:label path="contact.lastName" /></td>--%>
                <td>${contact.id}</td>
                <td>${contact.name}</td>
                <td>${contact.lastName}</td>
            <tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <table class="table table-striped table-bordered">
        <tr>
            <td>
                <%--<a href="${pageContext.request.contextPath}/search/form"><spring:message code="table.contact.add"/></a>--%>
                <%--<input name="submit" value="<spring:message code="table.contact.add"/>" type="submit" onClick="document.getElementById('add_bank').action='/search/form'"/>--%>
                    <label for="criteria"><spring:message code="table.contact.name"/></label>
                    <input name="criteria" id="criteria"/>
                    <%
                        String value=request.getParameter("criteria"); //Print value what you will provide
                        ContactSearchCriteria criteria=new ContactSearchCriteria();
                        criteria.setCriteria(value);
                    %>
                    <input name="submit" value="<spring:message code="table.contact.search"/>" type="submit" onClick="document.getElementById('add_bank').action='/search/contact?criteria=<%request.getParameter("criteria");%>'"/>
                    <%--<input name="submit" value="<spring:message code="table.contact.search"/>" type="submit" onClick="document.getElementById('add_bank').action='/search/contact?criteria=<%criteria.getCriteria();%>'"/>--%>

            </td>
        </tr>
    </table>
    <c:if test="${! empty bank_contacts}">
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
                <c:forEach var="contact" items="${bank_contacts}">
                <tr>
                        <%--<td><form:input path="ids" type="checkbox" name="selectedItems" value="${contact.id}"/></td>--%>
                    <td><form:checkbox path="newContactIds" value="${contact.id}"/></td>
                    <td>${contact.id}</td>
                    <td>${contact.name}</td>
                    <td>${contact.lastName}</td>
                <tr>
                </c:forEach>
                </tbody>
            </table>
            <input name="submit" value="<spring:message code="table.contact.add"/>" type="submit" onClick="document.getElementById('add_bank').action='/add/contact/bank'"/>
    </c:if>
    <table class="table table-striped table-bordered">
        <tr>
            <td colspan="2">
                <input type="submit" />
                <input type="button" value="Cancel" onclick="window.location.href='/'">
            </td>
        </tr>
    </table>
</form:form>
<%--<form:form method="GET" action="/search/contact" modelAttribute="bank" id="searchContact">--%>
    <%--<table class="table table-striped table-bordered">--%>
        <%--<tr>--%>
            <%--<td><form:label path="name"><spring:message code="table.contact.name"/></form:label></td>--%>
            <%--<td><form:input path="name"/></td>--%>
            <%--<td><input type="submit" name="submit" value="Search"/></td>--%>
            <%--&lt;%&ndash;<td><input type="button" name="submit" value="Search" onclick="document.getElementsById('searchContact').action='/search/contact'"/></td>&ndash;%&gt;--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>
</body>
</html>
