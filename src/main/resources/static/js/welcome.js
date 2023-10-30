// 1. DOM Element Grabbing (Cache elements)
const body = document.getElementById("body");
const url = "/api/person";

// 2. Function Definitions
const promptUserId = () => {
  return prompt("Please enter your name:", "Default Name");
};

const sendUserIdToServer = (userName) => {
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userName),
  };
  fetch(url, options)
    .then((res) => res.json())
    .then((data) => {
      sessionStorage.setItem("userName", data.name);
      sessionStorage.setItem("userId", data.personId);
      let userIdReturn = sessionStorage.getItem("userId");

      console.log("Hello " + data.name + "! How are you today?");
      console.log(data);
    });
};

const introduceUser = (userName) => {
  let paragraph = document.createElement("p");
  const textNode = document.createTextNode(
    `Hello, ${userName}! How are you today?`
  );
  paragraph.appendChild(textNode);
  body.appendChild(paragraph);
};

// 3. On Page Load Logic
document.addEventListener("DOMContentLoaded", (event) => {
  let userName = promptUserId();
  sendUserIdToServer(userName);
  introduceUser(userName);
});
