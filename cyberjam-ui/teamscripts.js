const apiUrl = 'http://localhost:8080/team-admin';

async function getAllTeams() {
    try {
        const response = await fetch(`${apiUrl}/get-teams`);
        const data = await response.json();
        document.getElementById('teamsOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('teamsOutput').textContent = 'Error fetching teams';
    }
}

document.getElementById('addTeamForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const teamId = document.getElementById('teamId').value;
    const teamName = document.getElementById('teamName').value;
    const participantIds = document.getElementById('participantIds').value.split(',').map(id => id.trim());
    try {
        const response = await fetch(`${apiUrl}/add-team?teamId=${teamId}&teamName=${teamName}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(participantIds)
        });
        const data = await response.text();
        document.getElementById('addTeamOutput').textContent = data;
    } catch (error) {
        document.getElementById('addTeamOutput').textContent = 'Error adding team';
    }
});

async function getTeamInfoById() {
    const teamId = document.getElementById('getTeamId').value;
    try {
        const response = await fetch(`${apiUrl}/get-team-info-with-id?id=${teamId}`);
        const data = await response.json();
        document.getElementById('getTeamInfoOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('getTeamInfoOutput').textContent = 'Error fetching team info';
    }
}

document.getElementById('updateTeamForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const updatedTeam = {
        id: document.getElementById('updateTeamId').value,
        name: document.getElementById('updateTeamName').value,
        members: []
    };
    try {
        const response = await fetch(`${apiUrl}/update-team-with-id?id=${updatedTeam.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedTeam)
        });
        const data = await response.text();
        document.getElementById('updateTeamOutput').textContent = data;
    } catch (error) {
        document.getElementById('updateTeamOutput').textContent = 'Error updating team';
    }
});

async function deleteTeamById() {
    const teamId = document.getElementById('deleteTeamId').value;
    try {
        const response = await fetch(`${apiUrl}/delete-team-with-id?id=${teamId}`, {
            method: 'DELETE'
        });
        const data = await response.text();
        document.getElementById('deleteTeamOutput').textContent = data;
    } catch (error) {
        document.getElementById('deleteTeamOutput').textContent = 'Error deleting team';
    }
}

document.getElementById('addMemberToTeamForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const teamId = document.getElementById('addMemberTeamId').value;
    const participantId = document.getElementById('addMemberParticipantId').value;
    try {
        const response = await fetch(`${apiUrl}/add-member-to-team?teamId=${teamId}&participantId=${participantId}`, {
            method: 'POST'
        });
        const data = await response.text();
        document.getElementById('addMemberToTeamOutput').textContent = data;
    } catch (error) {
        document.getElementById('addMemberToTeamOutput').textContent = 'Error adding member to team';
    }
});

document.getElementById('removeMemberFromTeamForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const teamId = document.getElementById('removeMemberTeamId').value;
    const participantId = document.getElementById('removeMemberParticipantId').value;
    try {
        const response = await fetch(`${apiUrl}/remove-member-from-team?id=${teamId}&participantId=${participantId}`, {
            method: 'DELETE'
        });
        const data = await response.text();
        document.getElementById('removeMemberFromTeamOutput').textContent = data;
    } catch (error) {
        document.getElementById('removeMemberFromTeamOutput').textContent = 'Error removing member from team';
    }
});