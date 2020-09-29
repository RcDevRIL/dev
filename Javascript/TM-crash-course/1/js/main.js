//alert('Hello World');

// CONSOLE CMD
/* console.log('Hello World');
console.error('ERROR');
console.warn('warning!'); */

// let, const

/* let age = 30;
age = 31; // OK
const age = 30;
age = 31; // error
console.log(age); */

// String, Numbers, Boolean, null, undefined
/* const a = 'John';
const b = 30;

console.log(typeof a); */
//Concat
/* console.log('My name is ' + a + ' and I am ' + b); */
// Template
/* const hello = `My name is ${a} and I am ${b}`;
console.log(hello); */
// Arrays
/* const num = new Array(1, 2, 3, 4, 5);
console.log(num);
const fruits = ['apples', 'oranges', 'pears'];
console.log(fruits);
fruits.push('mangos');
fruits.unshift('strawberries');
console.log(fruits);
fruits.pop();
console.log(fruits);
console.log(Array.isArray(fruits));
console.log(fruits.indexOf('oranges')); */
// object literals
/* const person = {
    firstName: 'John',
    age: 30,
    hobbies: ['music', 'movies', 'sports'],
    address: {
        street: '39 main street',
        city: 'Boston',
        state: 'MA',
    }
}
console.log(person.hobbies[1]);
console.log(person.address.city);
// destructuring
const { firstName, age, address: { city } } = person;

console.log(firstName);
console.log(city);

//add object
person.email = 'johh@';
console.log(person); */

// with arrays
const todos = [{ id: 1, text: 'take out trash', isCompleted: true }, { id: 2, text: 'meet with boss', isCompleted: true }, { id: 3, text: 'dentist apt', isCompleted: false }];
/* console.log(todos[1].text);
const todoJSON = JSON.stringify(todos);
console.log(todoJSON); */

// While loop   
/* let i = 0;
while (i < 10) {
    console.log(`While loop n°${i}`);
    i++;
} */
// For loop
/* for (let i = 0; i < todos.length; i++) {
    console.log(todos[i].text);
} */
// better
/* for (let todo of todos) {
    console.log(todo.text);
} */
// even better: forEach, map, filter
/* todos.forEach(function (todo) {
    console.log(todo.text);
});

const todoMap = todos.map(function (todo) {
    return todo.text;
});
console.log(todoMap);

const todoFilter = todos.filter(function (todo) {
    return todo.isCompleted == true;
});
console.log(todoFilter);
const todoFilterMap = todos.filter(function (todo) {
    return todo.isCompleted == true;
}).map(function (todo) {
    return todo.text;
});
console.log(todoFilterMap); */

// Conditions
/* const x = 6;
const y = 10;
// OR : ||, AND: &&
if (x > 5 || y > 10) {
    console.log('x > 5 || y > 10');
} else if (x > 10) {
    console.log('x > 10');
} else {
    console.log('x < 10');
} */
// ternary op
/* const x = 10;
const color = x > 10 ? 'green' : 'orange';
console.log(color);
// switch
switch (color) {
    case 'green':
        console.log('greeeeeeeen');
        break;
    case 'red':
        console.log('reeeeeeeeed');
        break;
    default:
        console.log('oups');
} */