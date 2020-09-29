//Single element
/* const form = document.getElementById('my-form');
console.log(form);
const container = document.querySelector('h1'); // if more than one h1, select first
console.log(container); */
// Multiple elements
/* const items = document.querySelectorAll('.item');
console.log(items);

items.forEach(  (item) => console.log(item)); */


// DOM Manipulations
/* const ul = document.querySelector('.items');
console.log(ul);
ul.remove();
ul.lastElementChild.remove();
ul.firstElementChild.textContent = 'Hello';
ul.children[1].innerText = 'Brad';
ul.lastElementChild.innerHTML = `<h1>${ul.lastElementChild.textContent}</h1>`;
 */
/* const btn = document.querySelector('.btn');
btn.style.background = 'red';
btn.addEventListener('click', (e) => {
    e.preventDefault(); // prevent submit button to actually submit and refresh page
    console.log('click', e);
    document.querySelector('#my-form').style.background = '#ccc';
    document.querySelector('body').classList.add('bg-dark');
}); */

const myForm = document.querySelector('#my-form');
const nameInput = document.querySelector('#name');
const mailInput = document.querySelector('#email');
const msg = document.querySelector('.msg');
const userList = document.querySelector('#users');

myForm.addEventListener('submit', onSubmit);


function onSubmit(e) {
    e.preventDefault();
    if (nameInput.value === '' || mailInput.value === '') {
        msg.classList.add('error');
        msg.innerHTML = 'Please enter all fields';

        setTimeout(() => {
            msg.innerHTML = '';
            msg.classList.remove('error');
        }, 1500);
    } else {
        const li = document.createElement('li');
        li.appendChild(document.createTextNode(`${nameInput.value}; ${mailInput.value}`));
        userList.appendChild(li);

        nameInput.value = '';
        mailInput.value = '';
    }
}