<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Error">
    <h3 style="text-align: center">
        <br>У вас ${errorCode} ошибочка. Но... <br><br>
    </h3>
    <img src="/images/main/error.jpeg" width="350" height="450" style="display: block; margin-left: auto; margin-right: auto">
</t:mainLayout>
