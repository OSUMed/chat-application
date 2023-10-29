// Cached elements
const body = document.getElementById("body");

var userName = prompt("Please enter your name:", "Default Name");
let paragraph = document.createElement("p");
const textNode = document.createTextNode(
  `Hello, ${userName}! How are you today?`
);
paragraph.appendChild(textNode);
body.appendChild(paragraph);

if (userName != null) {
  sessionStorage.setItem("userName", userName);
  console.log("Hello " + userName + "! How are you today?");
}
