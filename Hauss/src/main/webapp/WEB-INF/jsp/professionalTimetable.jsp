<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                        <a href="<spring:url value="/profile/timetable/client"/>" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span>Like client</span></a>
                        <a href="#" class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><span style="color: darkmagenta">Like professional</span> </a>
                    </div>
                </div>
            </div>
            <main class="col ps-md-2 pt-2">
                <div class="title h1 text-center">Your nearest orders</div>

                <hr>
                <div class="container mt-5 mb-5">
                    <div class="d-flex justify-content-center row">
                        <div class="col-md-10">
                            <c:forEach var="order" items="${orders}">
                                <div class="row p-2 bg-white border rounded mt-2">
                                    <div class="col-md-8 mt-1">
                                        <h5>Order id: ${order.id}</h5>
                                        <p1 class="text-justify text-truncate para mb-0">Wishes: ${order.comment}<br><br></p1>
                                        <div class="mt-1 mb-1 spec-1">
                                            <span class="dot"></span><strong>Date: ${order.date} (Execution time: ${order.offer.executionTime} days)</strong>
                                        </div>
                                        <div class="mt-1 mb-1 spec-1">
                                            <span class="dot"></span><span><a href="<spring:url value="/offers/${order.offer.id}"/>">For offer</a></span>
                                        </div>
                                    </div>
                                    <div class="align-items-center align-content-center col-md-4 border-left mt-1">
                                        <h6 class="text-success">Total price</h6>
                                        <div class="d-flex flex-row align-items-center">
                                            <h4 class="mr-1">${order.totalPrice} ${order.currency}<br></h4>
                                        </div>
                                        <br>
                                        <h6 class="text-success">Client info</h6>
                                        <div class="d-flex">
                                            <p3>${order.account.email}</p3>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
</t:mainLayout>
