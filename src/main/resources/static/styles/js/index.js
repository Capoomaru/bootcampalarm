let subscribeBtn = document.querySelector(".subscribe");
let pullBtn = document.querySelector(".pull");
let mail = document.querySelectorAll(".hide");


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



