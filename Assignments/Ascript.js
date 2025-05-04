const uploadBtn = document.getElementById("uploadBtn");
const uploadModal = document.getElementById("uploadModal");
const closeUploadModal = document.getElementById("closeUploadModal");
const uploadForm = document.getElementById("uploadForm");
const notesContainer = document.getElementById("notes-container");
const previewModal = document.getElementById("previewModal");
const closePreviewModal = document.getElementById("closePreviewModal");
const previewTitle = document.getElementById("previewTitle");
const previewDescription = document.getElementById("previewDescription");
const previewDate = document.getElementById("previewDate");
const previewFiles = document.getElementById("previewFiles");
const downloadPreview = document.getElementById("downloadPreview");
const previewOverlay = document.getElementById("previewOverlay");

let notes = [];

uploadBtn.onclick = () => {
  uploadModal.classList.remove("hidden");
};

closeUploadModal.onclick = () => {
  uploadModal.classList.add("hidden");
};

uploadForm.onsubmit = function (e) {
  e.preventDefault();
  const title = document.getElementById("noteTitle").value;
  const description = document.getElementById("noteDescription").value;
  const files = document.getElementById("noteFiles").files;
  const date = new Date().toLocaleDateString();

  if (!title || !description || files.length === 0) return;

  const note = {
    title,
    description,
    date,
    files: Array.from(files)
  };

  notes.unshift(note);
  renderNotes();
  uploadModal.classList.add("hidden");
  uploadForm.reset();
};

function truncateText(text, maxChars = 180) {
  return text.length > maxChars ? text.slice(0, maxChars) + "..." : text;
}

function renderNotes() {
  notesContainer.innerHTML = "";
  if (notes.length === 0) {
    notesContainer.classList.add("notes-blank-state");
    notesContainer.innerHTML = '<p class="blank-message">No notes yet. Upload your first notes to get started.</p>';
    return;
  }

  notesContainer.classList.remove("notes-blank-state");

  notes.forEach(note => {
    const card = document.createElement("div");
    card.className = "note-card";
    card.onclick = () => openPreview(note.title, note.description, note.files);

    const fileIcons = note.files.slice(0, 3).map(file => {
      return `<span class="file-icon" title="${file.name}">${file.name}</span>`;
    }).join("");

    const moreFiles = note.files.length > 3 ? `<span class="more-files">⋯</span>` : "";

    card.innerHTML = `
      <div class="title">${note.title}</div>
      <p class="description">${truncateText(note.description)}</p>
      <p class="meta"><strong>Uploaded on:</strong> ${note.date} &nbsp; | &nbsp; <strong>Files:</strong> ${note.files.length}</p>
      <div class="file-icons">${fileIcons}${moreFiles}</div>
    `;

    notesContainer.appendChild(card);
  });
}

function openPreview(title, message, files) {
  // Ensure the correct IDs are used
  previewTitle.textContent = title;
  previewDescription.textContent = message; // Correctly references previewDescription
  previewFiles.innerHTML = "";

  files.forEach(file => {
    const iconWrapper = document.createElement("div");
    iconWrapper.className = "file-icon";

    const icon = document.createElement("i");
    icon.className = "fas fa-file-alt"; // Use a better file icon if available

    const fileName = document.createElement("span");
    fileName.textContent = file.name.length > 20 ? file.name.slice(0, 17) + "..." : file.name;

    iconWrapper.appendChild(icon);
    iconWrapper.appendChild(fileName);
    previewFiles.appendChild(iconWrapper);
  });

  // Ensure the overlay is displayed
  previewOverlay.classList.remove("hidden");
}

function closePreview() {
  previewOverlay.classList.add("hidden");
}

previewOverlay.addEventListener("click", function (e) {
  if (e.target === this) {
    closePreview();
  }
});

function downloadAllFiles() {
  const files = document.querySelectorAll("#previewFiles .file-icon span");
  files.forEach(fileSpan => {
    const fileName = fileSpan.textContent.replace("...", "");
    const link = document.createElement("a");
    link.href = `uploads/${fileName}`; // Adjust to your actual file location
    link.download = fileName;
    link.click();
  });
}

downloadPreview.onclick = downloadAllFiles;

closePreviewModal.onclick = closePreview;
