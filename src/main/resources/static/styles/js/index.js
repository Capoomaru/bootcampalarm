let subscribeBtn = document.querySelector(".subscribe");
let pullBtn = document.querySelector(".pull");
let mail = document.querySelectorAll(".hide");

let checkboxes = document.querySelectorAll(".checkbox");

subscribeBtn.addEventListener("click", function() {
    console.log("subbtn event");
    pullBtn.style.display="none";
    for (let i = 0; i < mail.length; i++) {
        mail[i].style.display="block";
    }
})
pullBtn.addEventListener("click", function() {
    console.log("pullbtn event");
    subscribeBtn.style.display="none";
    for (let i = 0; i < mail.length; i++) {
        mail[i].style.display="block";
    }
});

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
