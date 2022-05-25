<%@tag description="Card layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>

        <link href="/css/file.css" rel="stylesheet" type="text/css">

        <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>
        <script src=https://code.jquery.com/jquery-3.6.0.js></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link data-require="bootstrap@3.3.7" data-semver="3.3.7" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <!-- Подключаем CSS DateTimePicker -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css" />
        <!-- Здесь будет форма -->
        <!-- Подключаем библиотеку jQuery -->
        <script data-require="jquery@3.1.1" data-semver="3.1.1" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Подключаем локаль библиотеки moment -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/locale/ru.js"></script>
        <!-- Подключаем библиотеку moment -->
        <script data-require="MomentJS@2.10.0" data-semver="2.10.0" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
        <!-- Подключаем js-файл Bootstrap -->
        <script data-require="bootstrap@3.3.7" data-semver="3.3.7" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Подключаем js-файл библиотеки DateTimePicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    </head>
    <body>
        <section class="h-100 h-custom" style="background-color: #d7dee4;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-8 col-xl-6">
                        <div class="card rounded-3">
                            <jsp:doBody/>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
