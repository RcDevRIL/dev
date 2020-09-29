/* function addNums(num1 = -1, num2 = -1) { return num1 + num2 };
const addSix = num1 => num1 + 6;

// call with defaults
console.log(addNums());
// normal calls
console.log(addNums(1, 2));
console.log(addSix(2)); */

// OBJECT BASICS
// Constructor function
/* function Person(firstName, lastName, dob) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = new Date(dob);
}

Person.prototype.getBirthYear = function () {
    return this.dob.getFullYear();
}
Person.prototype.getFullName = function () {
    return `${this.firstName} ${this.lastName}`;
}

// Instantiation
const pers1 = new Person('John', 'Doe', '4-3-1980');

console.log(pers1.dob);
console.log(pers1.dob.getFullYear());
console.log(pers1.getBirthYear());
console.log(pers1.getFullName());

console.log(pers1); */

// Classes
class Person {
    constructor(firstName, lastName, dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = new Date(dob);
    }

    getBirthYear() { return this.dob.getFullYear(); }
    getFullName() { return `${this.firstName} ${this.lastName}`; }
}

const pers1 = new Person('John', 'Doe', '4-3-1980');

console.log(pers1.dob);
console.log(pers1.dob.getFullYear());
console.log(pers1.getBirthYear());
console.log(pers1.getFullName());

console.log(pers1);