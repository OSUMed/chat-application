// 1. DOM Element Grabbing (Cache elements)
const body = document.getElementById("body");
const url = "/api/user";

// 2. Helper Functions
const promptUserName = () => {
	return prompt("Please enter your name:", "Default Name");
};

const storeUserInfoSessions = (name, userId) => {
	sessionStorage.setItem("userName", name);
	sessionStorage.setItem("userId", userId);
};

const attachUserDialogToDom = (userName) => {
	let paragraph = document.createElement("p");
	const textNode = document.createTextNode(
		`Hello, ${userName}! How are you today?`
	);
	paragraph.appendChild(textNode);
	body.appendChild(paragraph);
};

const sendUserIdToServer = (userName) => {
	const options = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: userName,
	};
	fetch(url, options)
		.then((res) => res.json())
		.then((data) => {
			let name = data.name;
			let userId = data.userId;
			storeUserInfoSessions(name, userId);
		});
};

// 3. On Page Load Logic
document.addEventListener("DOMContentLoaded", (event) => {
	// Prompt User's name, send to server & save to sessions:
	let userName;
	if (!sessionStorage.getItem('hasVisited')) {
		userName = promptUserName();
		sendUserIdToServer(userName);

		sessionStorage.setItem('name', userName);
		sessionStorage.setItem('hasVisited', 'true');
	}
	attachUserDialogToDom(sessionStorage.getItem('name'));
});
