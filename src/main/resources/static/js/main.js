// 1. DOM Element Grabbing (Cache elements)
const addGeneralMessageButton = document.getElementById("add-general-message");
const generalMessageInput = document.getElementById("general-message");
const mainList = document.getElementById("list-main");
const textbox = document.getElementById("textbox");
const channelIdTag = document.getElementById("channel-id");

// Global Variables
let lastMessageSessionId = Number(sessionStorage.getItem("lastMessageId") || 0);
let storedUserId = sessionStorage.getItem("userId");
const channelId = channelIdTag.innerText;
const url = `/api/channel/${channelId}`;
const getUrl = `/api/channel/${channelId}/messages`;
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
  fetch(getUrl, options)
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
      const { allMessages, lastMessagesId } = data;
      let newMessages;
      let messageElement;
      console.log("getmessages allMessages is: ", allMessages);
      // Filter out messages newer than session message Id:
      let sessionMessageId = Number(
        sessionStorage.getItem("lastMessageId") || 0
      );
      console.log("session message id is: ", sessionMessageId);
      newMessages = allMessages?.filter(
        (message) => message[2] > sessionMessageId
      );
      console.log("newMessages message is: ", newMessages);

      // Iterate and append new messages to DOM:
      newMessages?.forEach((newMessage) => {
        messageElement = document.createElement("li");
        mainList.appendChild(messageElement);
        messageElement.innerText = `${newMessage[0]}: ${newMessage[1]}`;
      });

      // Update the lastMessageId if new messages were found
      if (newMessages?.length > 0) {
        newLastMessageId = newMessages[newMessages.length - 1][2];
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
    userId: storedUserId,
    channelId: channelId,
  };
  console.log("sending message body is: ", messageBody, url);

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(messageBody),
  };

  if (message) {
    const messageElement = document.createElement("li");
    fetch(url, options)
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
        console.log("what are the main.js res for addMsg: ", data);
        let newMessage = data[data.length - 1];
        messageElement.innerText = `${newMessage[0]}: ${newMessage[1]}`;
        mainList.appendChild(messageElement);

        // Get current session message number and update it:
        let sessionMessageId = Number(
          sessionStorage.getItem("lastMessageId") || 0
        );
        sessionStorage.setItem("lastMessageId", sessionMessageId + 1);
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
if (storedUserId === null) window.location.href = `/api/channel/`;

document.addEventListener("DOMContentLoaded", (event) => {
  mainList.innerHTML = "";
  sessionStorage.setItem("lastMessageId", 0);

  getMessages();
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
}, REFRESH_INTERVAL_MS);
