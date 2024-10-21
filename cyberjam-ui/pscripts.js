import config from './config.js';

const apiUrl = `${config.apiUrl}/participant-admin`;

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('getAllParticipantsBtn').addEventListener('click', async () => {
        try {
            const response = await fetch(`${apiUrl}/participants`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            document.getElementById('participantsOutput').textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            console.error('Error fetching participants:', error);
            document.getElementById('participantsOutput').textContent = 'Error fetching participants';
        }
    });

    document.getElementById('addParticipantForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const participant = {
            participantId: document.getElementById('participantId').value,
            name: document.getElementById('name').value,
            experience: document.getElementById('experience').value,
            role: document.getElementById('role').value,
            description: document.getElementById('description').value
        };
        try {
            const response = await fetch(`${apiUrl}/add-participant`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(participant)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            document.getElementById('addParticipantOutput').textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            console.error('Error adding participant:', error);
            document.getElementById('addParticipantOutput').textContent = 'Error adding participant';
        }
    });

    document.getElementById('removeParticipantBtn').addEventListener('click', async () => {
        const participantId = document.getElementById('removeParticipantId').value;
        try {
            const response = await fetch(`${apiUrl}/remove-participant/${participantId}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            document.getElementById('removeParticipantOutput').textContent = data;
        } catch (error) {
            console.error('Error removing participant:', error);
            document.getElementById('removeParticipantOutput').textContent = 'Error removing participant';
        }
    });

    document.getElementById('updateParticipantForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const participant = {
            participantId: document.getElementById('updateParticipantId').value,
            name: document.getElementById('updateName').value,
            experience: document.getElementById('updateExperience').value,
            role: document.getElementById('updateRole').value,
            description: document.getElementById('updateDescription').value
        };
        try {
            const response = await fetch(`${apiUrl}/update-participant/${participant.participantId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(participant)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            document.getElementById('updateParticipantOutput').textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            console.error('Error updating participant:', error);
            document.getElementById('updateParticipantOutput').textContent = 'Error updating participant';
        }
    });
});

// Add this function at the end of the file
function initializeEventListeners() {
    document.getElementById('getAllParticipantsBtn').addEventListener('click', getAllParticipants());
    document.getElementById('removeParticipantBtn').addEventListener('click', removeParticipant());
    // Add other event listeners here as needed
}

// Call the initialization function when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', initializeEventListeners);