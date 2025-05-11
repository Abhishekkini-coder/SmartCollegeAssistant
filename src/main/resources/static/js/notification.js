// Endpoint for notifications
const baseUrl = "http://localhost:8080/notifications";

// Function to fetch notifications
async function fetchNotifications() {
    const studentId = 1; // Set this dynamically based on the logged-in student
    const response = await fetch(`${baseUrl}/student/${studentId}`);
    if (response.ok) {
        const notifications = await response.json();
        displayNotifications(notifications);
    } else {
        alert("Error fetching notifications.");
    }
}

// Function to display notifications in the DOM
function displayNotifications(notifications) {
    const notificationList = document.getElementById("notificationList");
    notificationList.innerHTML = ""; // Clear existing notifications
    notifications.forEach(notification => {
        const notificationDiv = document.createElement("div");
        notificationDiv.classList.add("notification");
        notificationDiv.innerHTML = `
            <p><strong>Message:</strong> ${notification.message}</p>
            <p><strong>Timestamp:</strong> ${new Date(notification.timestamp).toLocaleString()}</p>
            <p><strong>Status:</strong> ${notification.read ? "Read" : "Unread"}</p>
            <hr>
        `;
        notificationList.appendChild(notificationDiv);
    });
}

// Function to add a new notification
async function addNotification(event) {
    event.preventDefault(); // Prevent form from reloading the page

    const message = document.getElementById("message").value;
    const studentId = document.getElementById("studentId").value;

    const newNotification = {
        message: message,
        timestamp: new Date().toISOString(),
        read: false,
        student: { id: studentId }
    };

    const response = await fetch(`${baseUrl}/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newNotification)
    });

    if (response.ok) {
        alert("Notification added successfully!");
        fetchNotifications(); // Refresh the notification list
    } else {
        alert("Failed to add notification.");
    }
}

// Event listener for adding notification form
document.getElementById("addNotificationForm").addEventListener("submit", addNotification);

// Fetch notifications on page load
window.onload = fetchNotifications;
