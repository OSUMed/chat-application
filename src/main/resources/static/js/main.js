document.addEventListener("DOMContentLoaded", (event) => {
  sessionStorage.clear();
});

let lastMessageId = Number(sessionStorage.getItem("lastMessageId") || 0);
var storedName = sessionStorage.getItem("userName");
console.log("Stored name is: ", lastMessageId); // Outputs the name stored earlier
if (storedName == null) window.location.href = `/api/channel/`;

// Cached elements
const addGeneralMessageButton = document.getElementById("add-general-message");
const generalMessageInput = document.getElementById("general-message");
const mainList = document.getElementById("list-main");
const textbox = document.getElementById("textbox"); // to scroll to bottom

function getMessages() {
  fetch("/api/channel/general/messages", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => {
      let newMessages;
      let lastMessageId = sessionStorage.getItem("lastMessageId");

      const { allMessages, lastMessageId: newLastMessageId } = data;

      console.log("server amount and allmesga", allMessages, lastMessageId);

      console.log("lastMessageId before is: ", lastMessageId, newLastMessageId);
      let messageElement;
      newMessages = allMessages?.filter(
        (message) => message.messageId > lastMessageId
      );

      newMessages?.forEach((item) => {
        messageElement = document.createElement("li");
        mainList.appendChild(messageElement);
        messageElement.innerText = `${item.personId}: ${item.message}`;
      });

      console.log("this is the newMessages getMessages data: ", newMessages);
      // Update the lastMessageId if new messages were found
      if (newMessages?.length > 0) {
        let newLastMessageId = newMessages[newMessages.length - 1].messageId;
        console.log("First is ", newMessages);
        console.log("second is:  ", newMessages[newMessages.length - 1]);
        sessionStorage.setItem("lastMessageId", newLastMessageId);
      }
      console.log(
        "lastMessageId after is: ",
        lastMessageId,
        typeof lastMessageId,
        newLastMessageId
      );
    });
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
        const { allMessages } = data;
        let newMessage = allMessages[allMessages.length - 1];
        let currentMessageId =
          Number(sessionStorage.getItem("lastMessageId")) || 0;
        sessionStorage.setItem("lastMessageId", currentMessageId + 1);

        messageElement.innerText = `${newMessage.personId}: ${newMessage.message}`;
        console.log(
          "what is his: ",
          `${newMessage.personId}: ${newMessage.message}`
        );
        mainList.appendChild(messageElement);
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
  console.log("This message will be logged every 5 seconds");
}, 2000);
