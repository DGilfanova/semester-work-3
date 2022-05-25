<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Statistics">

    <div class="container-fluid" style="margin-top: 20px">
        <div class="row flex-nowrap">
            <div class="col-auto px-0">
                <div id="sidebar" class="collapse collapse-horizontal show border-end">
                    <div id="sidebar-nav" class="list-group border-0 rounded-0 text-sm-start min-vh-100">
                        <a id="ordersHistory" href="<spring:url value="/profile/statistics/orders-history"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Orders history</span> </a>
                        <a id="popularOffers" href="<spring:url value="/profile/statistics/popular-offer"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Your most popular offers</span> </a>
                        <a id="advantageousOffers" href="#" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span style="color: darkmagenta">Your most advantageous offers</span></a>
                        <a id="lastIdeas" href="<spring:url value="/profile/statistics/last-ideas"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Last published ideas</span></a>
                    </div>
                </div>
            </div>
            <main class="col ps-md-2 pt-2">
                <div class="page-header pt-3">
                    <h2>Your most advantageous offers</h2>
                </div>
                <hr>
                <div class="row">
                    <div class="col-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Title</th>
                                <th>Price</th>
                                <th>Execution time</th>
                                <th>Category</th>
                                <th>Count of orders</th>
                                <th>Average price for this category</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="offer" items="${offers}">
                                <tr>
                                    <th scope="row"><a href="<spring:url value="/offers/${offer.id}"/>">${offer.id}</a></th>
                                    <td>${offer.title}</td>
                                    <td>${offer.price}</td>
                                    <td>${offer.executionTime}</td>
                                    <td>${offer.category.name}</td>
                                    <td>${offer.ordersCount}</td>
                                    <td>${offer.averagePrice}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <c:if test="${offers == null}">
                                <p1 class="muted">You haven't such offers</p1>
                            </c:if>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </div>
</t:mainLayout>
