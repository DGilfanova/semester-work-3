<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Ideas">
    <div class="container mt-2 gallery">
        <div class="title h1 text-center">Favourites ideas</div>
        <div class="row">
            <c:forEach var="idea" items="${ideas}">
                <div class="col-md-3 col-sm-6">
                    <div class="card card-block">
                        <c:if test="${idea.photoLink != null}">
                            <img src="${idea.photoLink}" style="height:150px;width:100%;">
                        </c:if>
                        <c:if test="${idea.photoLink == null}">
                            <img src="/images/main/defaultPhoto.png" style="height:150px;width:100%;">
                        </c:if>
                        <h5 class="card-title mt-3 mb-3">${idea.title}</h5>
                        <p1 class="card-text">${idea.content}</p1>
                        <span><a href="<spring:url value="/ideas/${idea.id}"/>" class="btn btn-outline-success">Read More</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</t:mainLayout>
