<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cardLayout title="Sign in">
    <img class="card-img-top" src="images/main/signin.jpg" style="height: 250px" alt="Sign in">
    <div class="card-body p-4 p-md-5">
        <h3 class="mb-3 pb-2 pb-md-0 mb-md-4 px-md-3" style="text-align: center">Sign In</h3>
        <c:if test="${param.error != null}">
            <div style="color: chocolate">
                Wrong email/password.<br>
            </div>
        </c:if>
        <c:if test='${param.success != null}'>
            <div style="color: rgba(129,103,169,.6)">You are successfully registered<br> Check your email and confirm account</div>
        </c:if>
        <c:if test='${param.logout != null}'>
            <div style="color: chocolate">
                You are logged out.<br>
            </div>
        </c:if>
         <form:form class="form-horizontal" method="POST" modelAttribute="signInDto">
             <div class="form-outline mb-4">
                 Email<br>
                 <form:input path="email" />
             </div>
             <div class="form-outline mb-4">
                 Password<br>
                 <form:password path="password" />
             </div>
             Remember Me <input type="checkbox" name="rememberMe" /><br><br>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
             <input class="btn" style="background: #FFEBCD" type="submit" value="Sign in" />
             <a type="submit" class="btn" style="background: #D2B48C" href="${spring:mvcUrl("RC#getWelcomePage").build()}">Home Page</a>
         </form:form>
        <br>
        <a type="submit" class="btn" style="background: rgba(52,135,255,0.29)" href="${oAuthUri}">Sign in with VK</a>
        <br>
        <br>
        <br>
    </div>
</t:cardLayout>
