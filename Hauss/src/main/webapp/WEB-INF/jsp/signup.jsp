<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cardLayout title="Registration">
    <img class="card-img-top" src="images/main/signup.jpg" style="height: 250px" alt="Registration">
    <div class="card-body p-4 p-md-5">
        <h3 class="mb-4 pb-2 pb-md-0 mb-md-4 px-md-4" style="text-align: center">Registration</h3>
        <c:if test="${error != null}">
            <div style="color: chocolate">${error}</div><br>
        </c:if>
        <form:form class="form-horizontal" method="POST" modelAttribute="signUpDto">
            <div class="form-outline mb-4">
                First Name<br>
                <form:input path="firstName" />
                <form:errors cssStyle="color: chocolate" path="firstName"/>
            </div>
            <div class="form-outline mb-4">
                Last Name<br>
                <form:input path="lastName" />
                <form:errors cssStyle="color: chocolate" path="lastName"/>
            </div>
            <div class="form-outline mb-4">
                Email<br>
                <form:input path="email" />
                <form:errors cssStyle="color: chocolate" path="email"/>
            </div>
            <div class="form-outline mb-4">
                Phone number<br>
                <form:input path="phoneNumber" />
                <form:errors cssStyle="color: chocolate" path="phoneNumber"/>
            </div>
            <div class="form-outline mb-4">
                Password<br>
                <form:password path="password" />
                <form:errors cssStyle="color: chocolate" path="password"/>
            </div>
            <div class="form-outline mb-4">
                Confirm Password<br>
                <form:password path="confirmPassword" />
                <form:errors cssStyle="color: chocolate" path="confirmPassword"/>
                <form:errors cssStyle="color: chocolate" path=""/>
            </div>
            <div class="form-outline mb-4">
                About me<br>
                <form:input path="aboutMe" />
                <form:errors cssStyle="color: chocolate" path="aboutMe"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input class="btn" style="background: #FFEBCD" type="submit" value="Sign up" />
            <a type="submit" class="btn" style="background: #D2B48C" href="${spring:mvcUrl("RC#getWelcomePage").build()}">Home Page</a>
        </form:form>
    </div>
</t:cardLayout>
