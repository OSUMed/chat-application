// Cached elements
const addGeneralMessageButton = document.getElementById("add-general-message");
const generalMessageInput = document.getElementById("general-message");
const mainList = document.getElementById("list-main");
const textbox = document.getElementById("textbox"); // added this line

// Add message function
function addMessage() {
  const message = generalMessageInput.value;
  if (message) {
    const messageElement = document.createElement("li");
    axios
      .post("/api/channel/general", {
        personId: "test-user",
        message,
        channel: "general",
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error(error);
      });
    messageElement.innerText = message;
    mainList.appendChild(messageElement);
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
