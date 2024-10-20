import config from './config.js';

const apiUrl = `${config.apiUrl}/participant-admin`;

async function getAllParticipants() {
    try {
        const response = await fetch(`${apiUrl}/participants`);
        const data = await response.json();
        document.getElementById('participantsOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('participantsOutput').textContent = 'Error fetching participants';
    }
}

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
        const data = await response.json();
        document.getElementById('addParticipantOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('addParticipantOutput').textContent = 'Error adding participant';
    }
});

async function removeParticipant() {
    const participantId = document.getElementById('removeParticipantId').value;
    try {
        const response = await fetch(`${apiUrl}/remove-participant/${participantId}`, {
            method: 'DELETE'
        });
        const data = await response.text();
        document.getElementById('removeParticipantOutput').textContent = data;
    } catch (error) {
        document.getElementById('removeParticipantOutput').textContent = 'Error removing participant';
    }
}

document.getElementById('updateParticipantForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const participantId = document.getElementById('updateParticipantId').value;
    const updatedParticipant = {
        participantId: participantId,
        name: document.getElementById('updateName').value,
        experience: document.getElementById('updateExperience').value,
        role: document.getElementById('updateRole').value,
        description: document.getElementById('updateDescription').value
    };
    try {
        const response = await fetch(`${apiUrl}/update-participant/${participantId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedParticipant)
        });
        const data = await response.json();
        document.getElementById('updateParticipantOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('updateParticipantOutput').textContent = 'Error updating participant';
    }
});