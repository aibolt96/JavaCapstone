// Assuming you have a div element with the id "entry-list" to display journal entries
const entryList = document.getElementById('entry-list');

// Function to create a card for a journal entry
function createEntryCard(journalEntry, rating) {
    const card = document.createElement('div');
    card.classList.add('entry-card'); // You can define a CSS class for styling

    const entryText = document.createElement('p');
    entryText.textContent = `Journal Entry: ${journalEntry}`;

    const entryRating = document.createElement('p');
    entryRating.textContent = `Rating: ${rating}`;

    card.appendChild(entryText);
    card.appendChild(entryRating);

    entryList.appendChild(card);
}

// Event listener for form submission
const journalForm = document.getElementById('journal-form');
journalForm.addEventListener('submit', function (e) {
    e.preventDefault();

    const journalEntry = document.getElementById('journal-entry').value;
    const rating = document.getElementById('entry-rating').value;

    console.log("Journal Entry:", journalEntry);
    console.log("Rating:", rating);

    // Check if both journal entry and rating are provided
    if (journalEntry.trim() === '' || rating.trim() === '') {
        alert('Please provide both a journal entry and a rating.');
        return;
    }

    // Call your API to save the journal entry on the server
    // Use the correct endpoint here ("/api/entries/add")
    fetch('http://localhost:8080/api/entries/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            journalEntry: journalEntry,
            dayRating: rating,
        }),
    })
        .then((response) => response.json())
        .then((data) => {
             console.log("Response from server:", data);
            // Assuming the API responds with the saved journal entry data
            const savedJournalEntry = data.journalEntry;
            const savedRating = data.rating;

            // Create a card for the saved journal entry
            createEntryCard(savedJournalEntry, savedRating);

            // Clear the form inputs
            document.getElementById('journal-entry').value = '';
            document.getElementById('entry-rating').value = '';
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('An error occurred while saving the journal entry.');
        });
});