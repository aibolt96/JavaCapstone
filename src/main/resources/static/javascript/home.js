const cookieArr = document.cookie.split("=")
const userId = cookieArr[1]
console.log(userId);
const entryList = document.getElementById('entry-list');


function createEntryCard(entryId, journalEntry, rating) {
    const card = document.createElement('div');
    card.classList.add('entry-card');
    card.setAttribute('data-entry-id', entryId)


    const entryText = document.createElement('p');
    entryText.textContent = `${journalEntry}`;

    const entryRating = document.createElement('p');
    entryRating.textContent = `${rating}/5`;

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
            if (!response.ok) {
                throw new Error('Failed to add emotions to the entry');
            }
        })
        .then((data) => {
            const moodText = document.createElement('p');
            moodText.textContent = `Mood: ${mood}`;

            const moodReasonText = document.createElement('p');
            moodReasonText.textContent = `Mood Reason: ${moodReason}`;

            const spacer = document.createElement('div');
            spacer.style.marginTop = '15px';

            card.appendChild(spacer);
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

      // Create the "Delete" button
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.style.position = 'absolute';
        deleteButton.style.top = '10px';
        deleteButton.style.right = '10px';

      deleteButton.addEventListener('click', function () {
          // Prompt the user for confirmation before deleting
          const confirmDelete = confirm('Are you sure you want to delete this entry?');
          if (confirmDelete) {
              deleteEntry(entryId); // Call the deleteEntry function
          }
      });

      const entryLabel = document.createElement('p');
          entryLabel.textContent = 'Journal Entry:';
          entryLabel.style.textAlign = 'center';
          entryLabel.style.fontWeight = 'bold';

      const ratingLabel = document.createElement('p');
          ratingLabel.textContent = 'Day Rating:';
          ratingLabel.style.textAlign = 'center';
          ratingLabel.style.fontWeight = 'bold';

    card.appendChild(entryLabel);
    card.appendChild(entryText);
    card.appendChild(ratingLabel)
    card.appendChild(entryRating);
    card.appendChild(moodInput);
    card.appendChild(moodReasonInput);
    card.appendChild(updateButton);
    card.appendChild(deleteButton);

    entryList.insertBefore(card, entryList.firstChild);

    const contentHeight = card.scrollHeight;
        card.style.height = `${contentHeight}px`;
}

function deleteEntry(entryId) {
    fetch(`/api/entries/${entryId}`, {
        method: 'DELETE',
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error('Failed to delete entry');
        }
    })
    .then(() => {
        // Entry deleted successfully, you can remove the card from the UI
        const cardToRemove = document.querySelector(`[data-entry-id="${entryId}"]`);
        if (cardToRemove) {
            cardToRemove.remove();
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('An error occurred while deleting the entry.');
    });
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
        .then((response) => response.json())
        .then((data) => {
            console.log("Response from server:", data);
            console.log(journalEntry);
            console.log(rating);

            createEntryCard(data.entryId, journalEntry, rating);

            document.getElementById('journal-entry').value = '';
            document.getElementById('entry-rating').value = '';
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('An error occurred while saving the journal entry.');
        });
});