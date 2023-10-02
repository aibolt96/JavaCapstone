const cookieArr = document.cookie.split("=")
const userId = cookieArr[1]
console.log(userId);
const entryList = document.getElementById('entry-list');


function createEntryCard(entryId, journalEntry, rating) {
    const card = document.createElement('div');
    card.classList.add('entry-card');
    card.setAttribute('data-entry-id', entryId)

    const entryText = document.createElement('p');
    entryText.textContent = `Journal Entry: ${journalEntry}`;

    const entryRating = document.createElement('p');
    entryRating.textContent = `Rating: ${rating}`;

    const moodInput = document.createElement('input');
    moodInput.type = 'text';
    moodInput.placeholder = 'Mood';

    const moodReasonInput = document.createElement('input');
    moodReasonInput.type = 'text';
    moodReasonInput.placeholder = 'Mood Reason';

    const updateButton = document.createElement('button');
    updateButton.textContent = 'Update';
    updateButton.addEventListener('click', function () {
        const mood = moodInput.value;
        const moodReason = moodReasonInput.value;
        const entryId = card.getAttribute('data-entry-id');

        fetch(`/api/emotions/entries/${entryId}/emotions/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                emotionsDtoSet: [
                    {
                        mood: mood,
                        moodReason: moodReason,
                    },
                ],
            }),
        })
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to add emotions to the entry');
            }
        })
        .then((data) => {

            const moodText = document.createElement('p');
            moodText.textContent = `Mood: ${mood}`;

            const moodReasonText = document.createElement('p');
            moodReasonText.textContent = `Mood Reason: ${moodReason}`;

            card.appendChild(moodText);
            card.appendChild(moodReasonText);
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('An error occurred while adding emotions to the entry.');
        });


        moodInput.value = '';
        moodReasonInput.value = '';
    });

    card.appendChild(entryText);
    card.appendChild(entryRating);
    card.appendChild(moodInput);
    card.appendChild(moodReasonInput);
    card.appendChild(updateButton);

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


            createEntryCard(data.entryId, journalEntry, rating);

            
            document.getElementById('journal-entry').value = '';
            document.getElementById('entry-rating').value = '';
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('An error occurred while saving the journal entry.');
        });
});