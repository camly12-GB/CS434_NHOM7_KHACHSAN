document.addEventListener('DOMContentLoaded', () => {
    // ==================== Avatar ====================
    const avatarImage = document.getElementById('avatar-image');
    const avatarUpload = document.getElementById('avatar-upload');
    const uploadButton = document.querySelector('.upload-button');

    const savedImage = sessionStorage.getItem('avatarImage');
    avatarImage.src = savedImage || "white.png";

    uploadButton.addEventListener('click', (event) => {
        uploadButton.style.opacity = 1;
        avatarUpload.click();
        event.stopPropagation();
    });

    avatarUpload.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.addEventListener('load', (e) => {
                avatarImage.src = e.target.result;
                sessionStorage.setItem('avatarImage', e.target.result);
            });
            reader.readAsDataURL(file);
        }
    });

    // ==================== Tabs ====================
    const tabs = document.querySelectorAll('.tab');
    const profileContent = document.querySelector('.profile-content');
    const editContent = document.querySelector('.edit-content');
    const passwordContent = document.querySelector('.password-content');

    tabs.forEach(tab => {
        tab.addEventListener('click', (event) => {
            event.preventDefault();
            tabs.forEach(t => t.classList.remove('active'));
            profileContent.style.display = 'none';
            editContent.style.display = 'none';
            passwordContent.style.display = 'none';
            tab.classList.add('active');

            const tabId = tab.dataset.tab;
            if (tabId === 'profile') profileContent.style.display = 'block';
            else if (tabId === 'edit') editContent.style.display = 'block';
            else if (tabId === 'password') passwordContent.style.display = 'block';
        });
    });

    // ==================== GỌI API lấy dữ liệu ====================
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(data => {
            // Gán vào phần xem thông tin
            document.getElementById('fullname').value = data.fullName;
            document.getElementById('tax-code').value = data.taxCode;
            document.getElementById('phone').value = data.phone;
            document.getElementById('email').value = data.email;
            document.getElementById('address').value = data.address;

            // Gán vào form chỉnh sửa
            document.getElementById('edit-fullname').value = data.fullName;
            document.getElementById('edit-tax-code').value = data.taxCode;
            document.getElementById('edit-phone').value = data.phone;
            document.getElementById('edit-email').value = data.email;
            document.getElementById('edit-address').value = data.address;
        })
        .catch(err => console.error("Lỗi khi lấy dữ liệu người dùng:", err));

    // ==================== GỬI dữ liệu khi lưu thay đổi ====================
    const saveButton = document.querySelector('.edit-content .save-button');
    saveButton.addEventListener('click', (event) => {
        event.preventDefault(); // Ngăn reload trang

        const userData = {
            fullName: document.getElementById('edit-fullname').value,
            taxCode: document.getElementById('edit-tax-code').value,
            phone: document.getElementById('edit-phone').value,
            email: document.getElementById('edit-email').value,
            address: document.getElementById('edit-address').value
        };

        fetch("http://localhost:8080/api/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        })
        .then(res => {
            if (res.ok) {
                alert("Cập nhật thành công!");
                location.reload(); // Tải lại trang để xem dữ liệu mới
            } else {
                alert("Cập nhật thất bại!");
            }
        })
        .catch(err => {
            console.error("Lỗi khi gửi dữ liệu:", err);
            alert("Lỗi kết nối server!");
        });
    });
});
