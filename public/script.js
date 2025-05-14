const pricePerDay = 200;
const dayCountEl = document.getElementById('dayCount');
const totalEl = document.getElementById('total');
const summaryDaysEl = document.getElementById('summaryDays');
const daysInput = document.getElementById('days');
const dateRangeInput = document.getElementById('date-range');

let days = 2;

function updateDisplay() {
  dayCountEl.textContent = `${days} Days`;
  totalEl.textContent = `$${days * pricePerDay} USD`;
  summaryDaysEl.textContent = `${days} Days`;
  daysInput.value = days;
}

document.getElementById('increase').addEventListener('click', () => {
  days++;
  updateDisplay();
});

document.getElementById('decrease').addEventListener('click', () => {
  if (days > 1) {
    days--;
    updateDisplay();
  }
});

flatpickr("#date-range", {
  mode: "range",
  dateFormat: "d M Y",
  minDate: "today",
  locale: "vn",
  onChange: function (selectedDates) {
    if (selectedDates.length === 2) {
      const start = selectedDates[0];
      const end = selectedDates[1];
      const timeDiff = Math.abs(end - start);
      days = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)) + 1; // Bao gồm cả ngày bắt đầu và kết thúc
      updateDisplay();
    }
  }
});

document.getElementById('payment-btn').addEventListener('click', async function () {
  const btn = this;
  const originalText = btn.textContent;

  try {
    btn.disabled = true;
    btn.textContent = 'Đang xử lý...';

    const days = parseInt(document.getElementById('days').value);
    const dateRange = document.getElementById('date-range').value;

    const response = await fetch('/api/book', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify({
        days,
        dateRange
      })
    });

    const data = await response.json();

    if (!response.ok) {
      const errorMsg = data.message || `HTTP error! status: ${response.status}`;
      throw new Error(errorMsg);
    }

    alert(`Đặt phòng thành công! Mã: ${data.booking._id}`);

  } catch (error) {
    console.error('Error:', error);

    let errorMessage = error.message;

    if (error.message.includes('Failed to fetch')) {
      errorMessage = 'Không thể kết nối đến server. Vui lòng kiểm tra kết nối mạng.';
    }

    alert(`Lỗi: ${errorMessage}`);
  } finally {
    btn.disabled = false;
    btn.textContent = originalText;
  }
});

// Gọi khi khởi tạo để hiển thị đúng số tiền
updateDisplay();