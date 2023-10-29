// 1. DOM Element Grabbing (Cache elements)
const addGeneralMessageButton = document.getElementById("add-general-message");
const generalMessageInput = document.getElementById("general-message");
const mainList = document.getElementById("list-main");
const textbox = document.getElementById("textbox");

// Global Variables
let lastMessageSessionId = Number(sessionStorage.getItem("lastMessageId") || 0);
let storedName = sessionStorage.getItem("userName");
const REFRESH_INTERVAL_MS = 1500;
const SCROLL_ADJUST_DELAY_MS = 50;

// 2. Function Definitions
function getMessages() {
  let newLastMessageId = Number(sessionStorage.getItem("lastMessageId") || 0);
  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("/api/channel/general/messages", options)
    .then((res) => {
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      return res.json();
    })
    .then((data) => {
      if (data.error) {
        throw new Error(data.message);
      }
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
      console.log("in getMethod: ", newMessages, newLastMessageId);
    })
    .catch((error) => {
      console.error("Fetch error: " + error.message);
    });
  return newLastMessageId;
}
// Add message function
function addMessage() {
  const message = generalMessageInput.value;
  console.log("The new message value is: ", message);

  // Construct a JS object to send
  const messageBody = {
    message: message,
    personId: storedName,
    channel: "general",
  };

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: messageBody,
  };

  if (message) {
    const messageElement = document.createElement("li");
    fetch("/api/channel/general", options)
      .then((res) => {
        if (!res.ok) {
          throw new Error(`HTTP error! Status: ${res.status}`);
        }
        return res.json();
      })
      .then((data) => {
        if (data.error) {
          throw new Error(data.message);
        }
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
        console.log(
          "AddMessage: sessionMessageId is in : ",
          sessionMessageId + 1
        );
      })
      .catch((error) => {
        console.error("Fetch error: " + error.message);
      });

    generalMessageInput.value = "";

    // Adjusting the scroll for textbox
    setTimeout(() => {
      textbox.scrollTop = textbox.scrollHeight;
    }, SCROLL_ADJUST_DELAY_MS);
  }
}

// 3. On Page Load Logic

// Redirect to login on page load
if (storedName === null) window.location.href = `/api/channel/`;

document.addEventListener("DOMContentLoaded", (event) => {
  mainList.innerHTML = "";
  sessionStorage.setItem("lastMessageId", 0);

  getMessages();

  // Debugging Helpers: erase later
  // let anewLastMessageId = getMessages();
  // // sessionStorage.setItem("lastMessageId", newLastMessageId);
  // let newLastMessageId = Number(sessionStorage.getItem("lastMessageId") || 0);
  // console.log("getMessage returned newLastMessageId is : ", newLastMessageId);
});

// 4. Event Listeners

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

// 5. setInterval Logic

setInterval(function () {
  getMessages();

  // Debugging help:
  // let debugSessionMessageId = Number(
  //   sessionStorage.getItem("lastMessageId") || 0
  // );
  // console.log(
  //   "This message will be logged every 5 seconds",
  //   debugSessionMessageId
  // );
}, REFRESH_INTERVAL_MS);
