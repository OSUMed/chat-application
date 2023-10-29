// Cached elements
const addGeneralMessageButton = document.getElementById("add-general-message");
const generalMessageInput = document.getElementById("general-message");
const mainList = document.getElementById("list-main");
const textbox = document.getElementById("textbox");

// Redirect to login on page load-
// let lastMessageId = Number(sessionStorage.getItem("lastMessageId") || 0);
let lastMessageSessionId = Number(sessionStorage.getItem("lastMessageId") || 0);
let storedName = sessionStorage.getItem("userName");
if (storedName == null) window.location.href = `/api/channel/`;

// On page load clear session storage...
document.addEventListener("DOMContentLoaded", (event) => {
  sessionStorage.setItem("lastMessageId", 0);
  let newLastMessageId = getMessages();
  console.log("what is the new thing, : ", newLastMessageId);
});

function getMessages() {
  let newLastMessageId;
  fetch("/api/channel/general/messages", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => {
      const { allMessages } = data;
      let newMessages;
      let messageElement;

      // Filter out messages newer than session message Id:
      let sessionMessageId = Number(
        sessionStorage.getItem("lastMessageId") || 0
      );

      newMessages = allMessages?.filter(
        (message) => message.messageId > sessionMessageId
      );

      // Iterate and append new messages to DOM:
      newMessages?.forEach((item) => {
        messageElement = document.createElement("li");
        mainList.appendChild(messageElement);
        messageElement.innerText = `${item.personId}: ${item.message}`;
      });

      // Update the lastMessageId if new messages were found
      if (newMessages?.length > 0) {
        newLastMessageId = newMessages[newMessages.length - 1].messageId;
        sessionStorage.setItem("lastMessageId", newLastMessageId);
      }
    });
  return newLastMessageId;
}
// Add message function
function addMessage() {
  const message = generalMessageInput.value;
  console.log("this is the message: ", message);

  // Construct a JS object to send
  const messageObject = {
    message: message,
    personId: storedName,
    channel: "general",
  };

  if (message) {
    const messageElement = document.createElement("li");
    fetch("/api/channel/general", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(messageObject),
    })
      .then((res) => res.json())
      .then((data) => {
        // Get newest message and append to DOM-
        const { allMessages } = data;
        let newMessage = allMessages[allMessages.length - 1];
        messageElement.innerText = `${newMessage.personId}: ${newMessage.message}`;
        mainList.appendChild(messageElement);

        // Get current session message number and update it:
        let sessionMessageId = Number(
          sessionStorage.getItem("lastMessageId") || 0
        );
        sessionStorage.setItem("lastMessageId", sessionMessageId + 1);
        console.log("AddMessage: sessionMessageId is in : ", sessionMessageId);
      });

    generalMessageInput.value = "";

    // Adjusting the scroll for textbox
    setTimeout(() => {
      textbox.scrollTop = textbox.scrollHeight;
    }, 50);
  }
}

// Listen for 'Enter' key press
generalMessageInput.addEventListener("keyup", function (event) {
  if (event.key === "Enter") {
    event.preventDefault();
    addMessage();
  }
});

addGeneralMessageButton.addEventListener("click", function (event) {
  event.preventDefault();
  addMessage();
});

setInterval(function () {
  getMessages();
  let sessionMessageId = Number(sessionStorage.getItem("lastMessageId") || 0);
  console.log("This message will be logged every 5 seconds", sessionMessageId);
}, 1000);
