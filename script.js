document.addEventListener('DOMContentLoaded', () => {
    const avatarImage = document.getElementById('avatar-image');
    const avatarUpload = document.getElementById('avatar-upload');
    const avatarDiv = document.querySelector('.avatar');
    const uploadButton = document.querySelector('.upload-button');

    // Load saved image from session storage
    const savedImage = sessionStorage.getItem('avatarImage');
    if (savedImage) {
        avatarImage.src = savedImage;
    } else {
        avatarImage.src = "white.png";
    }

    // Make the avatar image clickable
    uploadButton.addEventListener('click', (event) => {
        uploadButton.style.opacity = 1;
        avatarUpload.click(); // Trigger the file input
        event.stopPropagation();
    });

    avatarUpload.addEventListener('change', (event) => {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.addEventListener('load', (e) => {
                avatarImage.src = e.target.result;

                // Save the image to session storage
                sessionStorage.setItem('avatarImage', e.target.result);
            });

            reader.readAsDataURL(file); // This triggers the 'load' event
        }
    });

    // Tab functionality (remains the same)
    const tabs = document.querySelectorAll('.tab');
    const profileContent = document.querySelector('.profile-content');
    const editContent = document.querySelector('.edit-content');
    const passwordContent = document.querySelector('.password-content');

    tabs.forEach(tab => {
        tab.addEventListener('click', (event) => {
            event.preventDefault();

            // Remove active class from all tabs
            tabs.forEach(t => t.classList.remove('active'));

            // Hide all content divs
            profileContent.style.display = 'none';
            editContent.style.display = 'none';
            passwordContent.style.display = 'none';

            // Add active class to the clicked tab
            tab.classList.add('active');

            // Show the corresponding content div
            const tabId = tab.dataset.tab;
            if (tabId === 'profile') {
                profileContent.style.display = 'block';
            } else if (tabId === 'edit') {
                editContent.style.display = 'block';
            } else if (tabId === 'password') {
                passwordContent.style.display = 'block';
            }
        });
    });
});