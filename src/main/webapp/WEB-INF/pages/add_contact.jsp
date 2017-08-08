<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add contact</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
        }
    </style>
</head>
<body>
<form:form method="POST" action="/add/contact" modelAttribute="contact">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><spring:message code="table.contact.firstName"/>:</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.lastName"/>:</td>
            <td><form:input path="lastName" /></td>
            <td><form:errors path="lastName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.Number"/>:</td>
            <td><form:input path="number" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.email"/>:</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.age"/>:</td>
            <td><form:input path="age" /></td>
            <td><form:errors path="age" cssClass="error" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.contact.balance"/>:</td>
            <td><form:input path="balance" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.address.country"/>:</td>
            <td><form:input path="address.country" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.address.city"/>:</td>
            <td><form:input path="address.city" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.address.state"/>:</td>
            <td><form:input path="address.state" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.address.street"/>:</td>
            <td><form:input path="address.street" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.address.house"/>:</td>
            <td><form:input path="address.house" /></td>
        </tr>
        <tr>
            <td><spring:message code="table.address.apartment"/>:</td>
            <td><form:input path="address.apartment" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" />
                <input type="button" value="Cancel" onclick="window.location.href='/'">
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
