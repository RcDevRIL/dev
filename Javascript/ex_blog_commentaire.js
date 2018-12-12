//VARIABLES
var myBtn = document.getElementById('submit');
var my2emeBtn = document.getElementById('reset');
//FUNCTIONS

function insertAfter(newElement, afterElement) {
    var parent = afterElement.parentNode;
    if (parent.lastChild === afterElement) { // Si le dernier élément est le même que l'élément après lequel on veut insérer, il suffit de faire appendChild()
        parent.appendChild(newElement);
    } else { // Dans le cas contraire, on fait un insertBefore() sur l'élément suivant
        parent.insertBefore(newElement, afterElement.nextSibling);
    }
};

function myBtnClickHandler(evt){
    //console.log(evt);
    var template = document.querySelector('.newComment');
    var newComment = document.importNode(template.content, true);
    newComment.querySelector('#other_comment').className = 'lastComment';
    var lastComment = document.querySelector('.lastComment');
    lastComment.className="";
    newComment.querySelector('.titre_auteur').innerHTML= document.getElementById('name').value;
   // console.log(a);
    //;var a 
    newComment.querySelector('#other_comment').style.position='relative';
    newComment.querySelector('#other_comment').style.top='0';
    insertAfter(newComment, lastComment);
    clearForm();
};

function clearForm(){
    document.getElementById('plop').value=' ';
    document.getElementById('name').value=' ';
}

function myBtnReset(evt){
    clearForm();
};





myBtn.addEventListener('click', myBtnClickHandler);
my2emeBtn.addEventListener('click', myBtnReset);

//myBtn.removeEventListener('click',myBtnClickHandler);