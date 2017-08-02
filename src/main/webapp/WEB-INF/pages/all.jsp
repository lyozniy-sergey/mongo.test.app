<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib prefix="display" uri="http://displaytag.sf.net" %>--%>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<img src="/resources/logo.png"><br/>
<div class="col-sm-offset-1 col-sm-10">

    <legend>
        <spring:message code="table.bank.title"/>
    </legend>
    <div id="bankPagination">
        <table id="banks" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th><a href="/banks?sort=id,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.bank.id"/></a></th>
                <th><a href="/banks?sort=name,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.bank.name"/></a></th>
                <th><a href="/banks?sort=info,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.bank.info"/></a></th>
                <th><spring:message code="table.contact.count"/></th>
                <th><spring:message code="table.contact.action"/></th>
            </tr>
            <thead>
            <tbody>
            <c:forEach var="bank" items="${banks}">
            <tr>
                <td>${bank.id}</td>
                <td>${bank.name}</td>
                <td>${bank.info}</td>
                <td>${bank.contacts.size()}</td>
                <td><a href="/edit/bank?id=${bank.id}">Edit</a> | <a href="/delete/bank?id=${bank.id}">Delete</a></td>
            <tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <a href="/bank"><spring:message code="table.bank.add"/></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <legend>
        <spring:message code="table.contact.title"/>
    </legend>

    <div id="contactPagination">
        <table id="contacts" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th><a href="/?sort=id,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.contact.id"/></a></th>
                <th><a href="/?sort=name,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.contact.firstName"/></a></th>
                <th><a href="/?sort=lastName,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.contact.lastName"/></a></th>
                <th><a href="/?sort=number,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.contact.age"/></a></th>
                <th><a href="/?sort=balance,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.contact.balance"/></a></th>
                <th><a href="/?sort=age,${direction}&page=${page}&size=${size}"><spring:message
                        code="table.contact.email"/></a></th>
                <th><spring:message code="table.contact.action"/></th>
            </tr>
            <thead>
            <tbody>
            <c:forEach var="contact" items="${contacts}">
            <tr>
                <td>${contact.id}</td>
                <td>${contact.name} (${contact.aggregations.name})</td>
                <td>${contact.lastName} (${contact.aggregations.lastName})</td>
                <td>${contact.age}</td>
                <td>${contact.balance}</td>
                <td>${contact.email}</td>
                <td><a href="/edit/contact?id=${contact.id}">Edit</a> | <a href="/delete/contact?id=${contact.id}">Delete</a></td>
            <tr>
                </c:forEach>
            <tr>
                <td colspan="7">
                    <a href="/add/contact"><spring:message code="table.contact.add"/></a>
                </td>
            </tr>
            </tr>
            <td colspan="7">
            </td>
            </tr>
            <tr>
                <td>
                    <a href="<spring:url value="/?page=${page - 1}&sort=${sort},${direction}&size=${size}"/>">Previous</a>
                    | <a href="<spring:url value="/?page=${page + 1}&sort=${sort},${direction}&size=${size}"/>">Next</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>