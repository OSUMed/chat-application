// 1. DOM Element Grabbing (Cache elements)
const body = document.getElementById("body");

// 2. Function Definitions
const promptUserId = () => {
  return prompt("Please enter your name:", "Default Name");
};

function generateUUID() {
  // Browser API:
  return crypto.randomUUID();
}

const sendUserIdToServer = (userName) => {
  const bodyData = {
    name: userName,
    personId: generateUUID(),
  };
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: bodyData,
  };
  fetch("api/person", options)
    .then((res) => res.json())
    .then((data) => {
      sessionStorage.setItem("userName", data.name);
      console.log("Hello " + data.name + "! How are you today?");
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
