let checkboxesForm = document.querySelector(".checkboxes-form");

// 이벤트 위임
checkboxesForm.addEventListener("click", function (e) {
    let target = e.target;

    console.log(target);

    let targetParent = target.parentElement;


    if (target.tagName == "LABEL") {
        if (target.control.checked) {
            targetParent.classList.remove("checked");
        } else {
            targetParent.classList.add("checked");
        }
    }
})


let subscribeBtn = document.querySelector(".subscribe");
let callBtn = document.querySelector(".call");


subscribeBtn.addEventListener("click", function () {
    event.preventDefault();

    let data = {
        mail : $(".mail-input").val(),
        is_new : "true"
    }

    data = JSON.stringify(data);

    console.log(data);

    $.ajax({
        url: "/api/v1/mails/codes",
        type: "POST",
        data: data,
        contentType: "application/json",
    })
        .done(function (data, status, xhr) {
            console.log(data, status, xhr);
            document.querySelector(".email-authentication-form").classList.remove("hide");
        })
        .fail(function (xhr, status, error) {
            console.log(xhr, status, error);
        })
})

callBtn.addEventListener("click", function () {
    event.preventDefault();
    let data = {
        mail : $(".mail-input").val(),
        is_new : false
    }

    data = JSON.stringify(data);

    console.log(data);

    $.ajax({
        url: "/api/v1/mails/codes",
        type: "POST",
        data: data,
        contentType: "application/json",
    })
        .done(function (data, status) {
            console.log(data);
            console.log(status);
            document.querySelector(".email-authentication-form").classList.remove("hide");
        })
        .fail(function (data, status) {
            console.log(data);
            console.log(status);
        })
})

let authBtn = document.querySelector(".authentication-button");

authBtn.addEventListener("click", function () {
    event.preventDefault();

    let checkboxes = document.querySelectorAll(".checkbox > input");
    let subList = [];

    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            console.log(`input id ${checkboxes[i].id}`);
            subList.push(checkboxes[i].id);
        }
    }

    console.log(subList);

    let data = {
        mail : $(".mail-input").val(),
        code : $(".authentication-input").val(),
        subscribe_list : subList,
    }

    data = JSON.stringify(data);

    console.log(data);

    $.ajax({
        url: "/api/v1/mails/authorize",
        type: "POST",
        data: data,
        contentType: "application/json",
    })
        .done(function (data, status) {
            console.log(data);
            console.log(status);
        })
        .fail(function (data, status) {
            console.log(data);
            console.log(status);
        })
})