<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="User offers">

    <div class="gallery">
        <div class="title h1 text-center">Your offers</div>
        <div class="title text-center">
            <c:if test="${param.error != null}">
                <i style="color: chocolate; text-align: center"><br>${error}<br></i>
            </c:if>
        </div>
        <div class="title text-center">
            <c:if test="${param.success != null}">
                <i style="color: blueviolet; text-align: center">You successfully create your offer<br><br></i>
            </c:if>
        </div>
        <div class="title text-center">
            <c:if test="${param.edit != null}">
                <i style="color: blueviolet; text-align: center">You successfully update your offer<br><br></i>
            </c:if>
        </div>
        <div class="title text-center">
            <c:if test="${param.access != null}">
                <p2 style="color: chocolate; text-align: center">You are not an author of this offer. Edit only orders below.<br></p2>
            </c:if>
        </div>
        <div class="title text-center">
            <c:if test="${empty offersPage.offers}">
                <i class="text-muted" style="color: grey; text-align: center">You haven't offers. Create!<br><br></i>
            </c:if>
        </div>

        <div class="title text-center">
            <a type="submit" class="btn" style="background: #D2B48C" href="<spring:url value="/profile/create-offer"/>">Create new</a>
        </div>
        <div class="container mt-5 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10">
                    <c:forEach var="offer" items="${offersPage.offers}">
                        <div class="row p-2 bg-white border rounded mt-2">
                            <div class="col-md-3 mt-1"><img class="img-fluid img-responsive rounded product-image" src="${offer.photoLink}"></div>
                            <div class="col-md-6 mt-1">
                                <a href="<spring:url value="/offers/${offer.id}"/>"><h5>${offer.title}</h5></a>
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
                                <a href="<spring:url value="/offers/${offer.id}/edition"/>" class="btn" style="background: #FFEBCD" type="submit" >Edit</a>
                                <a href="<spring:url value="/offers/${offer.id}/deletion"/>" class="btn" style="background: #D2B48C" type="submit">Delete</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <c:if test="${not empty offersPage.offers}">
        <div class="content-title center" style="alignment: center">
            <div style="margin: 0 auto;width: 120px;; padding-bottom: 10px"><strong>
                <c:if test="${offersPage.currentPage == 0}">
                    <a href="<spring:url value="/profile/offers?page=${offersPage.totalPages - 1}"/>"><p2>prev |</p2></a>
                </c:if>
                <c:if test="${offersPage.currentPage != 0}">
                    <a href="<spring:url value="/profile/offers?page=${offersPage.currentPage - 1}"/>"><p2>prev |</p2></a>
                </c:if>
                <p2>${offersPage.currentPage + 1}</p2>
                <c:if test="${offersPage.currentPage == offersPage.totalPages - 1}">
                    <a href="<spring:url value="/profile/offers?page=0"/>"><p2>| next</p2></a>
                </c:if>
                <c:if test="${offersPage.currentPage != offersPage.totalPages - 1}">
                    <a href="<spring:url value="/profile/offers?page=${offersPage.currentPage + 1}"/>"><p2>| next</p2></a>
                </c:if>
            </strong>
                <br>
            </div>
        </div>
        </c:if>
    </div>
</t:mainLayout>
