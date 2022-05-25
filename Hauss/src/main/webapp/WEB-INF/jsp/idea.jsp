<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Idea">
    <section>
        <div class="card1" style="max-width: 50%; margin: auto; background-color: #f6f6f6">
            <div class="card-body">
                <div class="d-flex mb-3">
                    <a href="">
                        <c:if test="${idea.account.photoLink != null}">
                            <img src="${idea.account.photoLink}" class="border rounded-circle me-2" style="height: 40px" />
                        </c:if>
                    </a>
                    <div>
                        <a href="<spring:url value="/profile/${idea.account.id}"/>" class="text-dark mb-0">
                            <strong>${idea.account.firstName} ${idea.account.lastName}</strong>
                        </a>
                        <div class="text-muted d-block" style="margin-top: -6px">
                            <small>${idea.account.email}</small>
                        </div>
                    </div>
                </div>
                <hr/>
                <div>
                    <h5>${idea.title}</h5>
                    <br>
                    <p1>${idea.content}</p1>
                    <br><br>
                    <p1>
                        <i>Category: ${idea.category}</i><br>
                        <i>Style: ${idea.style}</i><br><br>
                    </p1>
                </div>
            </div>
            <div class="bg-image hover-overlay ripple rounded-0" data-mdb-ripple-color="light">
                <c:if test="${idea.photoLink != null}">
                    <img src="${idea.photoLink}" class="w-100" />
                    <a href="#!">
                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                    </a>
                </c:if>
            </div>
            <div class="card-body">
                <div class="d-flex justify-content-between mb-3">
                    <c:if test="${idea.status == 'PRIVATE'}">
                        <img src="/images/main/privateIcon.jpg" width="25px" height="25px">
                    </c:if>
                    <div class="text-info">
                            <i class="fas fa-calendar-alt"></i>
                                ${idea.createdAt}
                    </div>
                </div>
            </div>
        </div>
    </section>
</t:mainLayout>
