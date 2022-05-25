<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Offers">

    <div class="gallery">
        <div class="title h1 text-center">Offers</div>
    <div class="col-lg-12">
    <div class="d-lg-flex">
        <div class="d-lg-flex align-items-center border">
            <div class="dropdown w-100 my-lg-0 my-2">
                Categories:
                <select id="my-category">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div id="load-image"></div>
            <div class="d-flex align-items-center w-100 h-100 ps-lg-0 ps-sm-3" style="padding-right: 70px; padding-left: 3px">
                <div id="searchButton" class="btn btn-primary d-flex align-items-center justify-content-center"> SEARCH</div>
            </div>
            <div class="d-flex align-items-center w-100 h-100 ps-lg-0 ps-sm-3">
                <input id="my-search" class=" ps-md-0 ps-3" type="text" placeholder="Find by title">
            </div>
        </div>
    </div>

        <div class="container mt-5 mb-5">
            <div class="d-flex justify-content-center row">
                <div id="offers" class="col-md-10">
                    <c:forEach var="offer" items="${offersPage.offers}">
                        <div class="row p-2 bg-white border rounded mt-2">
                            <div class="col-md-3 mt-1"><img class="img-fluid img-responsive rounded product-image" src="${offer.photoLink}"></div>
                            <div class="col-md-6 mt-1">
                                <h5>${offer.title}</h5>
                                <p1 class="text-justify text-truncate para mb-0">${offer.description}<br><br></p1>
                                <div class="mt-1 mb-1 spec-1">
                                    <span class="dot"></span><span>${offer.category.name}</span>
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <span class="dot"></span><span>Execution-time: ${offer.executionTime} days</span>
                                </div>
                            </div>
                            <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                                <h6 class="text-success">Price</h6>
                                <div class="d-flex flex-row align-items-center">
                                    <h4 class="mr-1">${offer.price} P<br></h4>
                                </div>
                                <a href="<spring:url value="/offers/${offer.id}"/>" class="btn" style="background: #FFEBCD" type="submit" >Details</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <c:if test="${not empty offersPage.offers}">
        <div id="page" class="content-title center" style="alignment: center">
            <div style="margin: 0 auto;width: 120px;; padding-bottom: 10px"><strong>
                <c:if test="${offersPage.currentPage == 0}">
                    <a href="<spring:url value="/offers?page=${offersPage.totalPages - 1}"/>"><p2>prev |</p2></a>
                </c:if>
                <c:if test="${offersPage.currentPage != 0}">
                    <a href="<spring:url value="/offers?page=${offersPage.currentPage - 1}"/>"><p2>prev |</p2></a>
                </c:if>
                <p2>${offersPage.currentPage + 1}</p2>
                <c:if test="${offersPage.currentPage == offersPage.totalPages - 1}">
                    <a href="<spring:url value="/offers?page=0"/>"><p2>| next</p2></a>
                </c:if>
                <c:if test="${offersPage.currentPage != offersPage.totalPages - 1}">
                    <a href="<spring:url value="/offers?page=${offersPage.currentPage + 1}"/>"><p2>| next</p2></a>
                </c:if>
            </strong>
                <br>
            </div>
        </div>
        </c:if>
    </div>
    <script type="text/javascript" src="<c:url value="../js/filter.js" />"></script>
</t:mainLayout>
