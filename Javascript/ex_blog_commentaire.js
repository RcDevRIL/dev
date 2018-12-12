//VARIABLES
var myBtn = document.getElementById('submit');
var my2emeBtn = document.getElementById('reset');
//FUNCTIONS
function myBtnClickHandler(evt){
    console.log(evt);
    var newDiv = document.createElement('div');
    newDiv.id = 'newComment';
    newDiv.value = 'CECI EST UN SUPER TEST WOUHOUUUUU!!!';
    document.getElementById('comment_principal').appendChild(newDiv);
};
function myBtnReset(evt){
    document.getElementById('plop').value=' ';
    document.getElementById('name').value=' ';
};




myBtn.addEventListener('click', myBtnClickHandler);
my2emeBtn.addEventListener('click', myBtnReset);

//myBtn.removeEventListener('click',myBtnClickHandler);