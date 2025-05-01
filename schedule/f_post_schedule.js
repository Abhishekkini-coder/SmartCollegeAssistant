let selectedSlot = null;

// Show form to add/edit class details
function showClassForm(slot) {
    const roomInput = document.getElementById('room');
    const semesterInput = document.getElementById('semester');
    const detailsInput = document.getElementById('details');
    const formContainer = document.getElementById('form-container');

    if (slot.classList.contains('filled')) {
        // If the slot already has a class, pre-fill the form
        roomInput.value = slot.getAttribute('data-room') || '';
        semesterInput.value = slot.getAttribute('data-semester') || '';
        detailsInput.value = slot.getAttribute('data-details') || '';
    } else {
        // Clear the form for new class
        roomInput.value = '';
        semesterInput.value = '';
        detailsInput.value = '';
    }

    // Display the form below the table
    formContainer.style.display = 'block';
    selectedSlot = slot;
}

// Save class details to the slot
function saveClassDetails() {
    const roomInput = document.getElementById('room').value;
    const semesterInput = document.getElementById('semester').value;
    const detailsInput = document.getElementById('details').value;

    if (!selectedSlot) return;

    // Save details to the slot element
    selectedSlot.classList.add('filled');
    selectedSlot.setAttribute('data-room', roomInput);
    selectedSlot.setAttribute('data-semester', semesterInput);
    selectedSlot.setAttribute('data-details', detailsInput);

    // Display only the Classroom Number in the slot
    selectedSlot.textContent = roomInput;

    // Close the form
    document.getElementById('form-container').style.display = 'none';
}

// Tooltip on hover
function showTooltip(slot, event) {
    let tooltip = document.getElementById('dynamic-tooltip');

    // Create the tooltip dynamically if it doesn't exist
    if (!tooltip) {
        tooltip = document.createElement('div');
        tooltip.id = 'dynamic-tooltip';
        tooltip.style.position = 'absolute';
        tooltip.style.backgroundColor = '#333';
        tooltip.style.color = '#fff';
        tooltip.style.padding = '13px';
        tooltip.style.borderRadius = '5px';
        tooltip.style.fontSize = '18px';
        tooltip.style.zIndex = '1000';
        tooltip.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.2)';
        tooltip.style.pointerEvents = 'none'; // Prevent tooltip from interfering with mouse events
        tooltip.style.whiteSpace = 'normal'; // Allow text wrapping
        tooltip.style.width = '250px'; // Fixed width for the tooltip
        tooltip.style.wordWrap = 'break-word'; // Break long words if necessary
        document.body.appendChild(tooltip);
    }

    // Update tooltip content and position
    tooltip.style.display = 'block';
    tooltip.style.left = `${event.pageX + 10}px`; // Position slightly to the right of the cursor
    tooltip.style.top = `${event.pageY + 10}px`; // Position slightly below the cursor
    tooltip.innerHTML = `
        <strong>Room:</strong> ${slot.getAttribute('data-room')}<br>
        <strong>Semester:</strong> ${slot.getAttribute('data-semester')}<br>
        <strong>Details:</strong> ${slot.getAttribute('data-details')}
    `;
}

function hideTooltip() {
    const tooltip = document.getElementById('dynamic-tooltip');
    if (tooltip) {
        tooltip.style.display = 'none';
    }
}

// Event listeners for slots
document.querySelectorAll('.slot').forEach(slot => {
    slot.addEventListener('dblclick', () => showClassForm(slot));

    // Hover event for tooltips
    slot.addEventListener('mouseover', (event) => {
        if (slot.classList.contains('filled')) {
            showTooltip(slot, event);
        }
    });

    slot.addEventListener('mousemove', (event) => {
        if (slot.classList.contains('filled')) {
            showTooltip(slot, event); // Update position as the mouse moves
        }
    });

    slot.addEventListener('mouseout', () => hideTooltip());
});

// Save button click
document.getElementById('saveButton').addEventListener('click', saveClassDetails);
