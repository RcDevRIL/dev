//VARIABLES
var btn1 = document.getElementById("img1");
var btn2 = document.getElementById("img2");
var btn3 = document.getElementById("img3");

//FONCTIONS
function btn1Handler(evt){
    currentSlide = document.querySelector("img.currentSlide");
    currentSlide.className="";
    document.querySelector("#slideContent img:nth-child(1)").className="currentSlide";
    btn1.style.backgroundColor="cyan";
    btn2.style.backgroundColor="grey";
    btn3.style.backgroundColor="grey";
}
function btn2Handler(evt){
    currentSlide = document.querySelector("img.currentSlide");
    currentSlide.className="";
    document.querySelector("#slideContent img:nth-child(2)").className="currentSlide";
    btn1.style.backgroundColor="grey";
    btn2.style.backgroundColor="cyan";
    btn3.style.backgroundColor="grey";
}
function btn3Handler(evt){
    currentSlide = document.querySelector("img.currentSlide");
    currentSlide.className="";
    document.querySelector("#slideContent img:nth-child(3)").className="currentSlide";
    btn1.style.backgroundColor="grey";
    btn2.style.backgroundColor="grey";
    btn3.style.backgroundColor="cyan";
}

//LISTENERS
btn1.addEventListener('click', btn1Handler);
btn2.addEventListener('click', btn2Handler);
btn3.addEventListener('click', btn3Handler);