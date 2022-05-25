<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cardLayout title="Create idea">
    <img class="card-img-top" src="/images/main/createIdea.jpeg" style="height: 250px" alt="Registration">
    <div class="card-body p-4 p-md-5">
        <h3 class="mb-3 pb-2 pb-md-0 mb-md-4 px-md-3" style="text-align: center">Create idea</h3>
        <c:if test="${error != null}">
            <div style="color: chocolate">${error}<br></div>
        </c:if>
        <form:form class="form-horizontal" method="POST" enctype="multipart/form-data" modelAttribute="ideaCreationDto">
            <div class="form-outline mb-4">
                Title<br>
                <form:input path="title" />
                <form:errors cssStyle="color: chocolate" path="title"/>
            </div>
            <div class="form-outline mb-4">
                Content<br>
                <form:input path="content" />
                <form:errors cssStyle="color: chocolate" path="content"/>
            </div>
            <div class="form-outline mb-4">
                Category
                <form:select path="category">
                    <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
                </form:select>
                <form:errors cssStyle="color: chocolate" path="category"/>
            </div>
            <div class="form-outline mb-4">
                Style
                <form:select path="style">
                    <form:options items="${styles}" itemValue="id" itemLabel="name"></form:options>
                </form:select>
                <form:errors cssStyle="color: chocolate" path="style"/>
            </div>
            <div class="form-outline mb-4">
                Private
                <form:checkbox path="isPrivate" />
                <form:errors cssStyle="color: chocolate" path="isPrivate"/>
            </div>
            <div class="form-outline mb-4">
                <div class="file-upload">
                    <div class="file-select">
                        <div class="file-select-button" id="fileName">Choose photo</div>
                        <div class="file-select-name" id="noFile">No photo chosen...</div>
                        <input type="file" name="file" id="chooseFile">
                    </div>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input class="btn" style="background: #FFEBCD" type="submit" value="Create" />
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
