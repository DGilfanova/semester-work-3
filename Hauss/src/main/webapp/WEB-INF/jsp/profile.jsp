<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:mainLayout title="Profile">
    <div class="row py-5 px-4 my-section">
        <div class="col-md-5 mx-auto">
            <div class="bg-white shadow rounded overflow-hidden">
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">
                        <div class="profile mr-3">
                            <c:if test="${account.photoLink != null}">
                                <img src="${account.photoLink}" width="130" class="rounded mb-2 img-thumbnail">
                                <a href="#" class="btn btn-outline-dark btn-sm btn-block">Edit profile</a>
                            </c:if>
                            <c:if test="${account.photoLink == null}">
                                <img src="/images/main/defaultAva.jpg" width="130" class="rounded mb-2 img-thumbnail">
                                <a href="#" class="btn btn-outline-dark btn-sm btn-block">Add photo</a>
                            </c:if>
                        </div>
                        <div class="media-body mb-5 text-white">
                            <h4 class="mt-0 mb-0">${account.firstName} ${account.lastName}</h4>
                            <h6 class="small mb-4">${account.email}</h6>
                        </div>
                    </div>
                </div>
                <div class="bg-light p-4 d-flex justify-content-end text-center">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <h5 class="font-weight-bold mb-0 d-block">${account.phoneNumber}</h5>
                            <small class="text-muted">
                                <i class="fas fa-phone mr-1"></i>
                                Phone number
                            </small>
                        </li>
                    </ul>
                </div>
                <div class="px-4 py-3">
                    <h5 class="mb-0">About me</h5>
                    <div class="p-4 rounded shadow-sm bg-light">
                        <i class="mb-0">${account.aboutMe}</i>
                    </div>
                    <div class="mr-3" style="padding-top: 10px;padding-bottom: 10px; position: absolute; right: 0px">
                        <a type="submit" href="<spring:url value="/logout"/>" style="background-color: rgba(129,103,169,.6)" class="btn">Logout</a>
                    </div>
                </div>
                <div class="py-4 px-4">
                    <div class="d-flex align-items-center justify-content-between mb-3 mt-3">
                        <h5 class="mb-0">Ideas</h5>
                        <a href="<spring:url value="/profile/create-idea"/>" class="btn btn-link text-muted">Create idea</a>
                    </div>
                    <c:if test="${empty account.ideas}">
                        <i class="text-muted" style="color: grey; text-align: center">You haven't ideas. Create!</i>
                    </c:if>
                    <c:if test="${param.success != null}">
                        <i style="color: blueviolet; text-align: center">You successfully create your idea<br></i>
                    </c:if>
                    <div class="title text-center">
                        <c:if test="${param.access != null}">
                            <p2 style="color: chocolate; text-align: center">You are not an author of this idea. Edit only ideas below.<br></p2>
                        </c:if>
                    </div>
                    <div class="row">
                            <div class="card-block">
                                <div class="row">
                                    <c:forEach var="idea" items="${account.ideas}">
                                        <div class="col-md-6 m-b-20">
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
                                                <a href="<spring:url value="/ideas/${idea.id}/delete"/>" class="btn btn-outline-danger" style="color: chocolate"><i class="fas fa-trash"></i></a></span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:mainLayout>
