<%@tag description="Basic layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header>
    <div class="navigation-wrap bg-light start-header start-style">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav class="navbar navbar-expand-md navbar-light">

                        <img class="navbar-brand" src="/images/main/logo.png" width="130" height="60">

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ml-auto py-4 py-md-0">
                                <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                    <a class="nav-link" href="<spring:url value="/welcome"/>">Welcome</a>
                                </li>
                                <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                    <a class="nav-link" href="<spring:url value="/ideas"/>">Ideas</a>
                                </li>
                                <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                    <a class="nav-link" href="<spring:url value="/offers"/>">Offers</a>
                                </li>
                                <sec:authorize access="isAnonymous()">
                                    <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Sign In</a>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="<spring:url value="/signin"/>">Sign In</a>
                                            <a class="dropdown-item" href="<spring:url value="/signup"/>">Register</a>
                                        </div>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
                                    <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Profile</a>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="<spring:url value="/profile"/>">Profile</a>
                                            <a class="dropdown-item" href="<spring:url value="/profile/offers"/>">Offers</a>
                                            <a class="dropdown-item" href="<spring:url value="/profile/ideas/favorites"/>">Favorites ideas</a>
                                            <a class="dropdown-item" href="<spring:url value="/profile/timetable/client"/>">Timetable</a>
                                            <a class="dropdown-item" href="<spring:url value="/profile/statistics/orders-history"/>">Statistics</a>
                                        </div>
                                    </li>
                                </sec:authorize>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>

</header>

