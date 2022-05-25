<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cardLayout title="Edit offer">
    <img class="card-img-top" src="/images/main/offer.jpg" style="height: 250px" alt="Registration">
    <div class="card-body p-4 p-md-5">
        <h3 class="mb-3 pb-2 pb-md-0 mb-md-4 px-md-3" style="text-align: center">Edit your offer</h3>
        <c:if test="${error != null}">
            <div style="color: chocolate">${error}<br></div>
        </c:if>
        <form:form class="form-horizontal" method="POST" enctype="multipart/form-data" modelAttribute="offerCreationDto">
            <div class="form-outline mb-4">
                Title<br>
                <form:input path="title" />
                <form:errors cssStyle="color: chocolate" path="title"/>
            </div>
            <div class="form-outline mb-4">
                Description<br>
                <form:input path="description" />
                <form:errors cssStyle="color: chocolate" path="description"/>
            </div>
            <div class="form-outline mb-4">
                Price<br>
                <form:input path="price" /> rubles
                <form:errors cssStyle="color: chocolate" path="price"/>
            </div>
            <div class="form-outline mb-4">
                Execution time<br>
                <form:input path="executionTime" /> days
                <form:errors cssStyle="color: chocolate" path="executionTime"/>
            </div>
            <div class="form-outline mb-4">
                Category
                <form:select path="category">
                    <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
                </form:select>
                <form:errors cssStyle="color: chocolate" path="category"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input class="btn" style="background: #FFEBCD" type="submit" value="Edit" />
            <a type="submit" class="btn" style="background: #D2B48C" href="<spring:url value="/profile"/>">Profile</a>
        </form:form>
    </div>

    <script>
        $('#chooseFile').bind('change', function () {
            let filename = $("#chooseFile").val();
            if (/^\s*$/.test(filename)) {
                $(".file-upload").removeClass('active');
                $("#noFile").text("No photo chosen...");
            }
            else {
                $(".file-upload").addClass('active');
                $("#noFile").text(filename.replace("C:\\fakepath\\", ""));
            }
        });
    </script>
</t:cardLayout>
