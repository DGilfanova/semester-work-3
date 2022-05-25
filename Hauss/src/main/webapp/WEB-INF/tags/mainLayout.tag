<%@tag description="Main Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="title" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>

        <link href="/css/main.css" rel="stylesheet" type="text/css">
        <link href="/css/offer.css" rel="stylesheet" type="text/css">
        <link href="/css/ideas.css" rel="stylesheet" type="text/css">

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <script type="text/javascript" src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" />"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
        </script><script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="hero-anime">
        <t:header/>
        <div class="main">
            <jsp:doBody/>
        </div>
        <t:footer/>
    </body>
</html>
