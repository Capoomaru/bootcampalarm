let is_new = true;

let campListBtn = document.querySelector("#camp-list-button");
let fieldListBtn = document.querySelector("#field-list-button");
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
    } else if (target.id === "camp-list-button") {
        document.querySelector(".camps-wrapper").classList.toggle("hide");
    } else if (target.id === "field-list-button") {
        document.querySelector(".fields-wrapper").classList.toggle("hide");
    }
})


let subscribeBtn = document.querySelector(".subscribe-button");
let pullBtn = document.querySelector(".pull-button");

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

            document.querySelector(".pull-button").disabled = true;

            is_new = true;
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
pullBtn.addEventListener("click", function () {

    event.preventDefault();

    let data = {
        mail : $(".mail-input").val(),
        is_new : "false"
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
            document.querySelector(".loading").innerHTML = "<span>메일을 전송했습니다...</span>";
        }
    })
        .done(function (data, status) {
            // test code
            console.log(data);
            console.log(status);

            document.querySelector(".loading").innerHTML = "<span>메일을 전송했습니다! 인증 번호를 입력하세요.</span>";
            document.querySelector(".email-authentication-form").classList.remove("hide");

            document.querySelector(".mail-input").disabled = true;
            subscribeBtn.disabled = true;

            is_new = false;
        })
        .fail(function (data, status) {
            // test code
            console.log(data);
            console.log(status);

            document.querySelector(".loading").innerHTML = "<span>불러오기를 실패했습니다...</span>";
        })
})

let authCodeBtn = document.querySelector(".authentication-button");

/**
 * 이메일 인증 버튼에 대한 처리
 */
authCodeBtn.addEventListener("click", function () {
    event.preventDefault();

    let data = {
        mail : $(".mail-input").val(),
        code : $(".authentication-input").val(),
    }

    data = JSON.stringify(data);

    // test code
    console.log(data);

    if (is_new === true) {
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

                saveButton.classList.remove("hide");

                pullBtn.disabled = true;
                subscribeBtn.disabled = true;
                authCodeBtn.disabled = true;
                document.querySelector(".mail-input").disabled = true;
                document.querySelector(".authentication-input").disabled = true;
                document.querySelector(".save-alert").innerHTML = "<span>저장 버튼을 꼭 눌러 주세요!</span>"
            })
            .fail(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("메일 인증에 실패했습니다.");
            })
    } else {
        $.ajax({
            url: `/api/v1/subscribes?mail=${$(".mail-input").val()}&code=${$(".authentication-input").val()}`,
            type: "GET",
            contentType: "application/json",
        })
            .done(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("메일 인증을 완료했습니다!");

                saveButton.classList.remove("hide");
                document.querySelector(".cancel-subscribe-button").classList.remove("hide");

                pullBtn.disabled = true;
                subscribeBtn.disabled = true;
                authCodeBtn.disabled = true;
                document.querySelector(".mail-input").disabled = true;
                document.querySelector(".authentication-input").disabled = true;
                document.querySelector(".save-alert").innerHTML = "<span>저장 버튼을 꼭 눌러 주세요!</span>"
                document.querySelector(".camps-wrapper").classList.toggle("hide");
                document.querySelector(".fields-wrapper").classList.toggle("hide");
                let checkboxes = document.querySelectorAll(".checkbox > input");

                for (let i = 0; i < checkboxes.length; i++) {
                    console.log(checkboxes[i].parentElement);
                    if (data.includes(Number(checkboxes[i].id))) {
                        console.log(Number(checkboxes[i].id));
                        checkboxes[i].checked = true;
                        checkboxes[i].parentElement.classList.add("checked");
                    } else {
                        checkboxes[i].checked = false;
                        checkboxes[i].parentElement.classList.remove("checked");
                    }
                }
            })
            .fail(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("메일 인증에 실패했습니다.");
            })
    }


})

let saveButton = document.querySelector(".save-button");
/**
 * 저장하기 버튼에 대한 처리
 */

saveButton.addEventListener("click", function () {
    event.preventDefault();

    let checkboxes = document.querySelectorAll(".checkbox > input");
    let subCheckList = [];
    let subSendList = [];

    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            // test code
            console.log(`input id ${checkboxes[i].value}`);
            subCheckList.push(checkboxes[i].value);
            subSendList.push(checkboxes[i].id);
        }
    }

    let conf;
    conf = confirm(`${subCheckList} 맞습니까?`);

    if (conf === true) {
        let data = {
            mail : $(".mail-input").val(),
            code : $(".authentication-input").val(),
            subscribe_list : subSendList,
        }

        data = JSON.stringify(data);

        $.ajax({
            url: "/api/v1/subscribes/forms",
            type: "POST",
            data: data,
            contentType: "application/json",
        })
            .done(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("저장을 완료했습니다!");

                location.reload();
            })
            .fail(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("저장에 실패했습니다.");
            })
    } else {

    }
})

let subscribeCancelBtn = document.querySelector(".cancel-subscribe-button");

subscribeCancelBtn.addEventListener("click", function () {

    let data = {
        mail : $(".mail-input").val(),
        code : $(".authentication-input").val(),
    }

    data = JSON.stringify(data);

    let conf;
    conf = confirm("정말 구독을 취소하실건가요?");

    if (conf === true) {
        $.ajax({
            url: "/api/v1/users",
            type: "POST",
            data: data,
            contentType: "application/json",
        })
            .done(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("구독을 취소했습니다!");

                location.reload();
            })
            .fail(function (data, status) {
                // test code
                console.log(data);
                console.log(status);

                alert("구독 취소를 실패했습니다!");
            })
    } else {

    }

})