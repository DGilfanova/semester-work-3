<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Offer">
    <section>
        <div class="card1" style="max-width: 50%; margin: auto; background-color: #f6f6f6">
            <div class="card-body">
                <!-- Data -->
                <div class="d-flex mb-3">
                    <a href="">
                        <c:if test="${offer.account.photoLink != null}">
                            <img src="${offer.account.photoLink}" class="border rounded-circle me-2" style="height: 40px" />
                        </c:if>
                    </a>
                    <div>
                        <a href="<spring:url value="/profile/${offer.account.id}"/>" class="text-dark mb-0">
                            <strong>${offer.account.firstName} ${offer.account.lastName}</strong>
                        </a>
                        <div class="text-muted d-block" style="margin-top: -6px">
                            <small>${offer.account.email}</small>
                        </div>
                    </div>
                </div>
                <hr/>
                <!-- Description -->
                <div>
                    <h5>${offer.title}</h5>
                    <br>
                    <p1>${offer.description}</p1>
                    <br><br>
                    <p1>
                        <p2 style="color: #8167a9">Category: ${offer.category.name}</p2><br>
                        <p2 style="color: #8167a9"><strong>Price: ${offer.price} P</strong></p2><br><br>
                        <p2 style="color: #8167a9">Execution-time: ${offer.executionTime} days</p2><br><br>
                    </p1>
                    <br>
                    <a href="<spring:url value="/profile/create-order?offer=${offer.id}"/>" class="btn" style="background: #D2B48C" type="submit" >Order</a>
                </div>
            </div>
            <div class="bg-image hover-overlay ripple rounded-0" data-mdb-ripple-color="light">
                <c:if test="${offer.photoLink != null}">
                    <img src="${offer.photoLink}" class="w-100" />
                    <a href="#!">
                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                    </a>
                </c:if>
            </div>
            <br><hr/>
            Write a comment
            <div id="result"></div>
            <div class="d-flex mb-3">
                <c:if test="${offer.account.photoLink != null}">
                    <a href="">
                        <img src="${offer.account.photoLink}" class="border rounded-circle me-2"
                             alt="Avatar" style="height: 40px" />
                    </a>
                </c:if>
                <div class="form-outline w-100">
                        <c:if test="${param.reviewSuccess != null}">
                            <div style="color: rgba(129,103,169,.6)">Add review successfully</div>
                        </c:if>
                        <textarea class="form-control" rows="2" id="content" name="content"></textarea>
                    <button class="btn" id="addReviewButton" style="background: rgba(129,103,169,.6)">Add</button>
                </div>
            </div>
            <hr/>
            <div id="reviews"></div>
            <div id="offerId" hidden>${offer.id}</div>
            <button id="getReviewBtn" class="btn" style="background: #D2B48C; margin: auto">Renew</button>
        </div>
    </section>

    <script type="text/javascript" src="<c:url value="../js/reviews.js" />"></script>
</t:mainLayout>
