<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cardLayout title="Create order">
    <img class="card-img-top" src="/images/main/order.jpg" style="height: 250px" alt="Order">
    <div class="card-body p-4 p-md-5">
        <h3 class="mb-3 pb-2 pb-md-0 mb-md-4 px-md-3" style="text-align: center">Create order</h3>
        <c:if test="${error != null}">
            <div style="color: chocolate">${error}<br></div>
        </c:if>
        <div class="form-outline mb-4">
            <a href="<spring:url value="/offers/${orderCreationDto.offerId}"/>">FOR OFFER</a>
        </div>
        <form:form class="form-horizontal" method="GET" action="/profile/create-order" modelAttribute="changeCurrencyDto">
            <div class="form-outline mb-4">
                Total price: <p1 style="color: #8167a9"><strong>${orderCreationDto.totalPrice} ${orderCreationDto.currencyName} </strong></p1>
                <form:select path="newCurrencyId">
                    <form:options items="${currencies}" itemValue="id" itemLabel="name"></form:options>
                </form:select>
                <input type="hidden" name="currencyId" value="${orderCreationDto.currencyId}">
                <input type="hidden" name="totalPrice" value="${orderCreationDto.totalPrice}">
                <input type="hidden" name="offer" value="${orderCreationDto.offerId}">
                <input type="hidden" name="convert" value="true">
                <input class="btn" style="background: #FFEBCD" type="submit" value="Change" />
                <c:if test="${param.convertError != null}">
                    <div style="color: chocolate">
                        Can't convert currencies<br>
                    </div>
                </c:if>
            </div>
<%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
        </form:form>
        <form:form class="form-horizontal" method="POST" modelAttribute="orderCreationDto">
            <div class="row">
                <div class='col-sm-6'>
                    <div class="form-group">
                        <label class="form-label select-label">Choose date</label>
                        <div class='input-group date' id='datetimepicker1'>
                            <input name="date" value="${orderCreationDto.date}" type='text' class="form-control field" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        $('#datetimepicker1').datetimepicker({
                            format: 'YYYY-MM-DD'
                        });
                    });
                </script>
            </div>
            <div class="form-outline mb-4">
                Wishes<br>
                <form:textarea path="comment" />
                <form:errors cssStyle="color: chocolate" path="comment"/>
            </div>
            <input type="hidden" name="offerId" value="${orderCreationDto.offerId}">
            <input type="hidden" name="currencyId" value="${orderCreationDto.currencyId}">
            <input type="hidden" name="currencyName" value="${orderCreationDto.currencyName}">
            <input type="hidden" name="totalPrice" value="${orderCreationDto.totalPrice}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input class="btn" style="background: #FFEBCD" type="submit" value="Create" />
            <a type="submit" class="btn" style="background: #D2B48C" href="<spring:url value="/profile"/>">Profile</a>
        </form:form>
    </div>
</t:cardLayout>
