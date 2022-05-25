<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Ideas">
    <div class="container mt-2 gallery">
        <c:if test="${param.denyAccess != null}">
            <div class="title h5 text-center" style="color: chocolate">It's private idea. Look at the others.<br></div>
        </c:if>
        <div class="title h1 text-center">Ideas</div>
        <div class="row">
            <c:forEach var="idea" items="${ideasPage.ideas}">
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
                        <span>
                        <i class="fas fa-calendar-alt mr-2"></i>${idea.createdAt}<br><br>
                        <a href="<spring:url value="/ideas/${idea.id}"/>" class="btn btn-outline-success btn-sm" style="width: 50%">Read More</a>
                        <a id="icon-button" onclick="addToFavor()" href="<spring:url value="/ideas/favorites?idea=${idea.id}"/>" class="btn btn-outline-danger btn-sm" style="width: 15%"><i id="icon" class="fa fa-heart"></i></a>
                    </span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <c:if test="${not empty ideasPage.ideas}">
    <div class="content-title center" style="alignment: center">
        <div style="margin: 0 auto;width: 120px;; padding-bottom: 10px"><strong>
        <c:if test="${ideasPage.currentPage == 0}">
            <a href="<spring:url value="/ideas?page=${ideasPage.totalPages - 1}"/>"><p2>prev |</p2></a>
        </c:if>
        <c:if test="${ideasPage.currentPage != 0}">
            <a href="<spring:url value="/ideas?page=${ideasPage.currentPage - 1}"/>"><p2>prev |</p2></a>
        </c:if>
        <p2>${ideasPage.currentPage + 1}</p2>
        <c:if test="${ideasPage.currentPage == ideasPage.totalPages - 1}">
            <a href="<spring:url value="/ideas?page=0"/>"><p2>| next</p2></a>
        </c:if>
        <c:if test="${ideasPage.currentPage != ideasPage.totalPages - 1}">
            <a href="<spring:url value="/ideas?page=${ideasPage.currentPage + 1}"/>"><p2>| next</p2></a>
        </c:if>
        </strong>
            <br>
        </div>
    </div>
    </c:if>
    <script type="text/javascript" src="<c:url value="../js/favorites.js" />"></script>
</t:mainLayout>
