// Sample data
const medicines = [
    { name: 'Paracetamol', price: 100 },
    { name: 'Aspirin', price: 150 },
    { name: 'Ibuprofen', price: 200 },
    { name: 'Amoxicillin', price: 250 },
    { name: 'Cough Syrup', price: 120 }
];

const doctors = [
    { name: 'Dr. John Doe', specialty: 'General Medicine', fee: 500 },
    { name: 'Dr. Jane Smith', specialty: 'Cardiology', fee: 1000 },
    { name: 'Dr. Emily White', specialty: 'Pediatrics', fee: 700 },
    { name: 'Dr. David Brown', specialty: 'Dermatology', fee: 800 }
];

const bloodTypes = [
    { type: 'A+', price: 5000 },
    { type: 'O+', price: 6000 },
    { type: 'B+', price: 5500 },
    { type: 'AB+', price: 6500 },
    { type: 'O-', price: 7000 }
];

const ambulances = [
    { name: 'Karim', number: '01704440791' },
    { name: 'Johir', number: '01726482698' },
    { name: 'Malek', number: '01957258925' },
    { name: 'Kashem', number: '01936735882' },
    { name: 'Amirul', number: '01836746926' }
];

let cart = [];

// Handle login
document.getElementById('login-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (email && password) {
        document.getElementById('auth-section').style.display = 'none';
        document.getElementById('meds-section').style.display = 'block';
    }
});

// Button event listeners
document.getElementById('search-medicine-btn').addEventListener('click', function() {
    toggleSection('medicine-section');
});
document.getElementById('doctor-btn').addEventListener('click', function() {
    toggleSection('doctor-section');
    displayDoctors();  // Ensure doctors list loads
});
document.getElementById('blood-btn').addEventListener('click', function() {
    toggleSection('blood-section');
    displayBloodTypes();  // Ensure blood types list loads
});
document.getElementById('ambulance-btn').addEventListener('click', function() {
    toggleSection('ambulance-section');
    displayAmbulanceNumbers();  // Ensure ambulance list loads
});
document.getElementById('helpline-btn').addEventListener('click', function() {
    const helplineDiv = document.getElementById('helpline');
    helplineDiv.style.display = helplineDiv.style.display === 'none' ? 'block' : 'none';
});

// Toggle section visibility function
function toggleSection(sectionId) {
    const sections = ['medicine-section', 'doctor-section', 'blood-section', 'ambulance-section'];
    sections.forEach(id => {
        document.getElementById(id).style.display = (id === sectionId) ? 'block' : 'none';
    });
}

// Search and display medicines
function searchMedicines() {
    const query = document.getElementById('search-bar').value.toLowerCase();
    const filteredMedicines = medicines.filter(medicine =>
        medicine.name.toLowerCase().includes(query)
    );
    displayMedicines(filteredMedicines);
}

function displayMedicines(filteredMedicines) {
    const medsList = document.getElementById('meds-list');
    medsList.innerHTML = '';
    filteredMedicines.forEach(medicine => {
        const div = document.createElement('div');
        div.classList.add('medicine-item');
        div.innerHTML = `
            <strong>${medicine.name}</strong><br>
            Price: ৳${medicine.price}<br>
            <input type="number" id="quantity-${medicine.name}" value="1" min="1" /><br>
            <button onclick="addToCart('${medicine.name}', ${medicine.price}')">Add to Cart</button>
        `;
        medsList.appendChild(div);
    });
}

// Add medicine to cart
function addToCart(name, price) {
    const quantity = document.getElementById(`quantity-${name}`).value;
    cart.push({ name, price, quantity });
    updateCart();
}

// Update cart display
function updateCart() {
    const cartSummary = document.getElementById('cart-summary');
    cartSummary.innerHTML = '';
    cart.forEach(item => {
        cartSummary.innerHTML += `<p>${item.name} - ৳${item.price} x ${item.quantity}</p>`;
    });
    const totalPrice = cart.reduce((total, item) => total + (item.price * item.quantity), 0);
    cartSummary.innerHTML += `<strong>Total: ৳${totalPrice}</strong>`;
    document.getElementById('checkout-btn').style.display = cart.length > 0 ? 'block' : 'none';
}

// Checkout (demo action)
document.getElementById('checkout-btn').addEventListener('click', function() {
    alert('Proceeding to checkout...');
});

// Display doctor information
function displayDoctors() {
    const doctorList = document.getElementById('doctor-list');
    doctorList.innerHTML = '';
    doctors.forEach(doctor => {
        const div = document.createElement('div');
        div.classList.add('doctor-item');
        div.innerHTML = `
            <strong>${doctor.name}</strong><br>
            Specialty: ${doctor.specialty}<br>
            Fee: ৳${doctor.fee}<br>
            <button onclick="bookAppointment('${doctor.name}')">Book Appointment</button>
        `;
        doctorList.appendChild(div);
    });
}

function bookAppointment(doctorName) {
    alert(`Appointment booked with ${doctorName}`);
}

// Display blood types
function displayBloodTypes() {
    const bloodList = document.getElementById('blood-list');
    bloodList.innerHTML = '';
    bloodTypes.forEach(blood => {
        const div = document.createElement('div');
        div.classList.add('blood-item');
        div.innerHTML = `
            <strong>${blood.type}</strong><br>
            Price: ৳${blood.price} per donation
        `;
        bloodList.appendChild(div);
    });
}

// Display ambulance numbers
function displayAmbulanceNumbers() {
    const ambulanceList = document.getElementById('ambulance-list');
    ambulanceList.innerHTML = '';
    ambulances.forEach(ambulance => {
        const div = document.createElement('div');
        div.classList.add('ambulance-item');
        div.innerHTML = `
            <strong>${ambulance.name}</strong>: ${ambulance.number}
        `;
        ambulanceList.appendChild(div);
    });
}
