document.addEventListener('DOMContentLoaded', () => {
    $(document).on("click", "#getReviewBtn", e => {
        let offerId = $("#offerId").text();

        $.ajax({
            type: "GET",
            url: "/offers/"+offerId+"/reviews",
            dataType: "json",
            success: function (response) {
                if (response.success == true) {
                    let html = ""
                    for (let x = 0; x < response.data.length; x++) {
                            html += `<div class="d-flex mb-3"><a>
                                <img src="` + response.data[x].authorPhotoLink + `" class="border rounded-circle me-2" style="height: 40px" />
                            </a>
                            <div>
                                <div class="bg-light rounded-3 px-3 py-1">
                                    <a class="text-dark mb-0">
                                        <strong>` + response.data[x].authorFirstName + ` ` + response.data[x].authorLastName + `</strong>
                                    </a>
                                    <a class="text-muted d-block">
                                        <small>` + response.data[x].content + `</small>
                                    </a>
                                </div>
                            </div></div>`
                    }
                    $('#reviews').html(html)
                } else {
                    console.log(response.errors)
                }
            },
            error: function (exception) {
                $('#reviews').html('<div>We have problem</div>');
            }
        })
    })

    $(document).on("click", "#addReviewButton", e => {
        let content = $("#content").val();
        let offerId = $("#offerId").text();

        let userData = ({
            'content': content
        });

        $.ajax({
            type: "POST",
            url: "/offers/" + offerId + "/reviews",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(userData),
            success: function (response) {
                if (response.success == true) {
                    let html = '<div style="background: cornsilk">You are successfully add your review. Click "review" to see</div>'
                    $('#content').val('')
                    $('#result').html(html)
                } else {
                    let html = '<div style="color: chocolate">You try to add invalid message</div>'
                    $('#content').val('')
                    $('#result').html(html)
                    console.log(response.errors)
                }
                console.log(response)
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 500) {
                    let html = '<div style="color: chocolate">Please sign in before</div>'
                    $('#content').val('')
                    $('#result').html(html)
                }
            }
        })
    })
});

