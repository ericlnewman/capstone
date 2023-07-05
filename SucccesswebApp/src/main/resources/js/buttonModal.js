// buttonModal.js

// Function to open the modal
function openModal() {
  var modal = document.getElementById('modal');
  modal.style.display = 'block';
}

// Function to handle option 1
function handleOption1() {
  console.log('Option 1 selected');
  window.location.href = 'changePassword.html';
}

// Function to handle option 2
function handleOption2() {
  console.log('Option 2 selected');
  window.location.href = 'userInformationView.html';
}

// Function to delete the account
function deleteAccount() {
  console.log('Account deleted');
  // Add your code to delete the user's account here
}

// Function to close the modal
function closeModal() {
  var modal = document.getElementById('modal');
  modal.style.display = 'none';
}