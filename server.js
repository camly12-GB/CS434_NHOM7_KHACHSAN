const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const mongoose = require('mongoose');
require('dotenv').config();

const app = express();

mongoose.connect('mongodb+srv://cuctadangyeu:QZILlDeMHHdqAokR@cluster0.unvaorl.mongodb.net/booking_app?retryWrites=true&w=majority&appName=Cluster0')
  .then(() => console.log('Connected to MongoDB'))
  .catch(err => console.error('MongoDB connection error:', err));

const bookingSchema = new mongoose.Schema({
  roomType: String,
  location: String,
  days: Number,
  dateRange: String,
  amount: Number,
  createdAt: { type: Date, default: Date.now }
});

const Booking = mongoose.model('Booking', bookingSchema);

app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/images', express.static('images'));
// Serve HTML file
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.post('/api/book', async (req, res) => {
  console.log('Request body:', req.body); 
  
  try {
    const { days, dateRange } = req.body;
    
    if (!days || isNaN(days)) {
      return res.status(400).json({
        success: false,
        message: 'Số ngày không hợp lệ'
      });
    }
    
    if (!dateRange || !dateRange.includes('to')) {
      return res.status(400).json({
        success: false,
        message: 'Khoảng thời gian không hợp lệ'
      });
    }
    
    const newBooking = new Booking({
      roomType: 'Phòng gia đình',
      location: 'Gaila, Sri Lanka',
      days: parseInt(days),
      dateRange,
      amount: 400,
      status: 'pending'
    });
    
    const savedBooking = await newBooking.save();
    console.log('Booking saved:', savedBooking);
    
    return res.json({
      success: true,
      message: 'Đặt phòng thành công',
      booking: savedBooking
    });
    
  } catch (error) {
    console.error('❌ Error saving booking:', error);
    return res.status(500).json({
      success: false,
      message: 'Lỗi server: ' + error.message,
      errorDetails: process.env.NODE_ENV === 'development' ? error.stack : undefined
    });
  }
});

app.get('/api/bookings', async (req, res) => {
  try {
    const bookings = await Booking.find().sort({ createdAt: -1 });
    res.json({ success: true, bookings });
  } catch (error) {
    res.status(500).json({ success: false, message: 'Lỗi khi lấy danh sách đặt phòng' });
  }
});

app.get('/admin', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'admin.html'));
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});