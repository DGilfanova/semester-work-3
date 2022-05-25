document.addEventListener('DOMContentLoaded', () => {
    $(document).on("click", "#searchButton", e => {
        let category = document.getElementById('my-category').value;

        $('#load-image').append(' <img id="my-img" src="https://acegif.com/wp-content/uploads/loading-11.gif" width="50px" height="50px">');
        $.ajax({
            type: "GET",
            url: "/offers/filter-category?category=" + category,
            dataType: "json",
            success: function (response) {
                $('#my-img').remove()
                if (response.success == true) {
                    let html = ""
                    for (let x = 0; x < response.data.length; x++) {
                        html += `<div class="row p-2 bg-white border rounded mt-2">
                            <div class="col-md-3 mt-1"><img class="img-fluid img-responsive rounded product-image" src="` + response.data[x].photoLink + `"></div>
                            <div class="col-md-6 mt-1">
                                <h5>` + response.data[x].title + `</h5>
                                <p1 class="text-justify text-truncate para mb-0">` + response.data[x].description + `<br><br></p1>
                                <div class="mt-1 mb-1 spec-1">
                                    <span class="dot"></span><span>`+ response.data[x].category.name + `</span>
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <span class="dot"></span><span>Execution-time: ` + response.data[x].executionTime + ` days</span>
                                </div>
                            </div>
                            <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                                <h6 class="text-success">Price</h6>
                                <div class="d-flex flex-row align-items-center">
                                    <h4 class="mr-1">` + response.data[x].price + ` P<br></h4>
                                </div>
                                <a href="/offers/`+ response.data[x].id + `" class="btn" style="background: #FFEBCD" type="submit" >Details</a>
                            </div>
                        </div>`
                    }
                    $('#offers').html(html)
                    $('#page').remove()
                }
            },
            error: function (exception) {
                console.log(exception)
                $('#offers').html('<div>We have problem</div>');
            }
        })
    })

    var timer, flag = true
    $(document).on("keyup", "#my-search", e => {
        let title = $("#my-search").val();

        if (flag) {
            $.ajax({
                type: "GET",
                url: "/offers/filter-title?title=" + title,
                dataType: "json",
                success: function (response) {
                    if (response.success == true) {
                        let html = ""
                        for (let x = 0; x < response.data.length; x++) {
                            html += `<div class="row p-2 bg-white border rounded mt-2">
                            <div class="col-md-3 mt-1"><img class="img-fluid img-responsive rounded product-image" src="` + response.data[x].photoLink + `"></div>
                            <div class="col-md-6 mt-1">
                                <h5>` + response.data[x].title + `</h5>
                                <p1 class="text-justify text-truncate para mb-0">` + response.data[x].description + `<br><br></p1>
                                <div class="mt-1 mb-1 spec-1">
                                    <span class="dot"></span><span>` + response.data[x].category.name + `</span>
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <span class="dot"></span><span>Execution-time: ` + response.data[x].executionTime + ` days</span>
                                </div>
                            </div>
                            <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                                <h6 class="text-success">Price</h6>
                                <div class="d-flex flex-row align-items-center">
                                    <h4 class="mr-1">` + response.data[x].price + ` P<br></h4>
                                </div>
                                <a href="/offers/` + response.data[x].id + `" class="btn" style="background: #FFEBCD" type="submit" >Details</a>
                            </div>
                        </div>`
                        }
                        $('#offers').html(html)
                        $('#page').remove()
                    }
                },
                error: function (exception) {
                    console.log(exception)
                    $('#offers').html('<div>We have problem</div>');
                }
            })

            flag = false;
            timer = setTimeout(function () {
                flag = true;
                console.log('query')}, 500)
        }
    })
});

