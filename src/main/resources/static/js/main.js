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
const REFRESH_INTERVAL_MS = 500;
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
      // Define variables & Set up:
      const { allMessages } = data;
      let newMessages;
      let messageElement;
      let sessionMessageId = Number(
        sessionStorage.getItem("lastMessageId") || 0
      );

      // Filter new messages and newest message(race condition cover):
      newMessages = allMessages?.filter(
        (message) => message[2] > sessionMessageId
      );

      // Iterate and append new messages to DOM:
      newMessages?.forEach((newMessage) => {
        messageElement = document.createElement("li");
        mainList.appendChild(messageElement);
        messageElement.innerText = `${newMessage[0]}: ${newMessage[1]}`;
      });

      // Update the lastMessageId if new messages were found
      if (newMessages?.length > 0) {
        newLastMessageId = newMessages[newMessages.length - 1][2];
      }
      sessionStorage.setItem("lastMessageId", newLastMessageId);
    })
    .catch((error) => {
      console.error("Fetch error: " + error.message);
    });

  return newLastMessageId;
}
// Add message function
function addMessage() {
  const message = generalMessageInput.value;

  // Construct a JS object to send
  const messageBody = {
    message: message,
    userId: storedUserId,
    channelId: channelId,
  };

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
        // To prevent race condition, store the returned new message's id:
        let newMessage = data[data.length - 1];
        let newMessageId = data[data.length - 1][2];
        sessionStorage.setItem("lastMessageId", newMessageId);

        messageElement.innerText = `${newMessage[0]}: ${newMessage[1]}`;
        mainList.appendChild(messageElement);
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
