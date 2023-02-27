let checkboxesForm = document.querySelector(".checkboxes-form");

/**
 * 체크박스들에 대한 이벤트를 이벤트 위임형식으로 처리
 */
checkboxesForm.addEventListener("click", function (e) {
    let target = e.target;

    // test code
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

/**
 * 구독하기 버튼에 대한 처리
 */
subscribeBtn.addEventListener("click", function () {

    event.preventDefault();

    let data = {
        mail : $(".mail-input").val(),
        is_new : "true"
    }

    data = JSON.stringify(data);

    // test code
    console.log(data);

    $.ajax({
        url: "/api/v1/mails/codes",
        type: "POST",
        data: data,
        contentType: "application/json",
        beforeSend: function () {
            document.querySelector(".loading").innerHTML = "<span>메일 전송 중입니다...</span>";
        }
    })
        .done(function (data, status, xhr) {
            // test code
            console.log(data, status, xhr);

            document.querySelector(".email-authentication-form").classList.remove("hide");

            document.querySelector(".loading").innerHTML = "<span>메일을 전송했습니다! 인증 번호를 입력하세요.</span>";
        })
        .fail(function (xhr, status, errorThrown) {
            // test code
            console.log(xhr, status, errorThrown);

            // 에러 코드나 상황에 따라 다른 지침을 세워야 함.
            document.querySelector(".loading").innerHTML = "<span>문제가 발생했습니다..</span>";
        })
})

/**
 * 불러오기 버튼에 대한 처리
 */
callBtn.addEventListener("click", function () {
    event.preventDefault();
    let data = {
        mail : $(".mail-input").val(),
        is_new : false
    }

    data = JSON.stringify(data);

    // test code
    console.log(data);

    $.ajax({
        url: "/api/v1/mails/codes",
        type: "POST",
        data: data,
        contentType: "application/json",
    })
        .done(function (data, status) {
            // test code
            console.log(data);
            console.log(status);
            document.querySelector(".email-authentication-form").classList.remove("hide");
        })
        .fail(function (data, status) {
            // test code
            console.log(data);
            console.log(status);
        })
})

let authBtn = document.querySelector(".authentication-button");

/**
 * 이메일 인증 버튼에 대한 처리
 */
authBtn.addEventListener("click", function () {
    event.preventDefault();

    let checkboxes = document.querySelectorAll(".checkbox > input");
    let subList = [];

    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            // test code
            console.log(`input id ${checkboxes[i].id}`);
            subList.push(checkboxes[i].id);
        }
    }

    // test code
    console.log(subList);

    let data = {
        mail : $(".mail-input").val(),
        code : $(".authentication-input").val(),
        subscribe_list : subList,
    }

    data = JSON.stringify(data);

    // test code
    console.log(data);

    $.ajax({
        url: "/api/v1/mails/authorize",
        type: "POST",
        data: data,
        contentType: "application/json",
    })
        .done(function (data, status) {
            // test code
            console.log(data);
            console.log(status);

            alert("메일 인증을 완료했습니다!");
        })
        .fail(function (data, status) {
            // test code
            console.log(data);
            console.log(status);

            alert("메일 인증에 실패했습니다.");
        })
})