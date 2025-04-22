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
    const tooltip = document.getElementById('tooltip');
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
    const tooltip = document.getElementById('tooltip');
    tooltip.style.display = 'none';
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
