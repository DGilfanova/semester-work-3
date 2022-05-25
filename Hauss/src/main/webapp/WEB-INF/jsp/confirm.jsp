<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Confirm">
    <div class="p-5 text-center bg-image" style="background-image: url('../images/main/background.jpg'); min-height: 730px; width: 100%;">
        <div class="mask">
            <div class="d-flex justify-content-center align-items-center h-100">
                    <c:if test="${param.success != null}">
                        <h3 style="color: rgba(129,103,169,0.8); background-color: white">You're successfully confirm your account</h3>
                    </c:if>
                    <c:if test='${param.error != null}'>
                        <h3 style="color: rgba(129,103,169,0.8); background-color: white">Wrong confirm code</h3>
                    </c:if>
            </div>
        </div>
    </div>
</t:mainLayout>
