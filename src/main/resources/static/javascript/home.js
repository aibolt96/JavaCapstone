const cookieArr = document.cookie.split("=")
const userId = cookieArr[1]
console.log(userId);
const entryList = document.getElementById('entry-list');


function createEntryCard(journalEntry, rating) {
    const card = document.createElement('div');
    card.classList.add('entry-card');

    const entryText = document.createElement('p');
    entryText.textContent = `Journal Entry: ${journalEntry}`;

    const entryRating = document.createElement('p');
    entryRating.textContent = `Rating: ${rating}`;

    card.appendChild(entryText);
    card.appendChild(entryRating);

    entryList.appendChild(card);
}


const journalForm = document.getElementById('journal-form');
journalForm.addEventListener('submit', function (e) {
    e.preventDefault();

    const journalEntry = document.getElementById('journal-entry').value;
    const rating = document.getElementById('entry-rating').value;

    console.log("Journal Entry:", journalEntry);
    console.log("Rating:", rating);


    if (journalEntry.trim() === '' || rating.trim() === '') {
        alert('Please provide both a journal entry and a rating.');
        return;
    }

    fetch(`http://localhost:8080/api/entries/add/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            journalEntry: journalEntry,
            dayRating: rating
        }),
    })
        .then((response) => response.status)
        .then((data) => {
            console.log("Response from server:", data);
            console.log(journalEntry);
            console.log(rating);
//             const savedJournalEntry = data.journalEntry;
//             const savedRating = data.rating;


            createEntryCard(journalEntry, rating);

            
//             document.getElementById('journal-entry').value = '';
//             document.getElementById('entry-rating').value = '';
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('An error occurred while saving the journal entry.');
        });
});