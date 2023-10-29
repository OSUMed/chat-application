// Cached elements
const addGeneralMessageButton = document.getElementById("add-general-message");
const generalMessageInput = document.getElementById("general-message");
const mainList = document.getElementById("list-main");
const textbox = document.getElementById("textbox"); // to scroll to bottom

// Add message function
function addMessage() {
  const message = generalMessageInput.value;
  console.log("this is the message: ", message);

  // Construct a JS object to send
  const messageObject = {
    message: message,
    personId: "test-user",
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
        mainList.appendChild(messageElement);
        messageElement.innerText = `${messageObject.personId}: ${message}`;

        console.log("this is the data: ", data);
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
