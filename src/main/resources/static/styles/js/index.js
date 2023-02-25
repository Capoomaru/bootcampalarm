let checkboxes = document.querySelectorAll(".checkbox");

for (let i = 0; i < checkboxes.length; i++) {
    checkboxes[i].addEventListener("click", function () {
        let temp = checkboxes[i].firstElementChild;

        if(temp.checked) {
            checkboxes[i].classList.add("checked");
        } else {
            checkboxes[i].classList.remove("checked");
        }
    })
}


let subscribeBtn = document.querySelector(".subscribe");
let callBtn = document.querySelector(".call");

subscribeBtn.addEventListener("click", function () {
    let data = {
        mail : $(".mail-input").val(),
        is_new : true
    }

    console.log(data);

    $.ajax({
        url: "/api/v1/mails/codes",
        type: "POST",
        data: data,
        dataType: "json",
        timeout: 3000,
        contentType: "application/json",
    })
        .done(function (data, status) {
            console.log(data);
            console.log(status);
        })
        .fail(function () {
            console.log("error");
        })
})

callBtn.addEventListener("click", function () {
    let data = {
        mail : $(".mail-input").val(),
        is_new : false
    }

    console.log(data);

    $.ajax({
        url: "/api/v1/mails/codes",
        type: "POST",
        data: data,
        dataType: "json",
        timeout: 3000,
        contentType: "application/json",
    })
        .done(function (data, status) {
            console.log(data);
            console.log(status);
        })
        .fail(function () {
            console.log("error");
        })
})