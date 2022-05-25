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
                        <a id="ordersHistory" href="#" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span style="color: darkmagenta">Orders history</span></a>
                        <a id="popularOffers" href="<spring:url value="/profile/statistics/popular-offer"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Your most popular offers</span> </a>
                        <a id="advantageousOffers" href="<spring:url value="/profile/statistics/adv-offer?category_id=1"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Your most advantageous offers</span></a>
                        <a id="lastIdeas" href="<spring:url value="/profile/statistics/last-ideas"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Last published ideas</span></a>
                    </div>
                </div>
            </div>
            <main class="col ps-md-2 pt-2">
                <div class="page-header pt-3">
                    <h2>Orders history</h2>
                </div>
                <hr>
                <div class="row">
                    <div class="col-12">
                        <table class="table">
                            <div class="row">
                                <div class="col-9">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Offer</th>
                                            <th>Date</th>
                                            <th>Price</th>
                                            <th>Currency</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="order" items="${orders}">
                                            <tr>
                                                <th scope="row">${order.id}</th>
                                                <td><a href="<spring:url value="/offers/${order.offer.id}"/>">${order.offer.id}</a></td>
                                                <td>${order.date}</td>
                                                <td>${order.totalPrice}</td>
                                                <td>${order.currency}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <c:if test="${orders == null}">
                                            <p1 class="muted">You don't have orders yet</p1>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                            <c:if test="${orders == null}">
                                <p1 class="muted">You haven't such orders</p1>
                            </c:if>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </div>
</t:mainLayout>
