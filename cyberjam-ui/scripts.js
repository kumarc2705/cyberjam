import config from './config.js';

const apiUrl = `${config.apiUrl}/judge-admin`;

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('getAllJudgesBtn').addEventListener('click', async () => {
        try {
            const response = await fetch(`${apiUrl}/judges`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            document.getElementById('judgesOutput').textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            console.error('Error fetching judges:', error);
            document.getElementById('judgesOutput').textContent = 'Error fetching judges';
        }
    });

    document.getElementById('addJudgeForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const judge = {
            judgeId: document.getElementById('judgeId').value,
            name: document.getElementById('name').value,
            experience: document.getElementById('experience').value,
            role: document.getElementById('role').value,
            description: document.getElementById('description').value,
            roleWeightage: {
                softwareWeight: parseFloat(document.getElementById('softwareWeight').value),
                hardwareWeight: parseFloat(document.getElementById('hardwareWeight').value),
                visualArtWeight: parseFloat(document.getElementById('visualArtWeight').value),
                musicWeight: parseFloat(document.getElementById('musicWeight').value),
                wildCardWeight: parseFloat(document.getElementById('wildCardWeight').value)
            },
            themeWeightage: {
                aiWeight: parseFloat(document.getElementById('aiWeight').value),
                fashionWeight: parseFloat(document.getElementById('fashionWeight').value),
                governanceWeight: parseFloat(document.getElementById('governanceWeight').value),
                sportsGamingWeight: parseFloat(document.getElementById('sportsGamingWeight').value),
                securityPrivacyWeight: parseFloat(document.getElementById('securityPrivacyWeight').value)
            }
        };

        try {
            const response = await fetch(`${apiUrl}/add-judge`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(judge)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            const data = await response.text();
            document.getElementById('addJudgeOutput').textContent = data;
        } catch (error) {
            console.error('Error adding judge:', error);
            document.getElementById('addJudgeOutput').textContent = 'Error adding judge';
        }
    });

    document.getElementById('removeJudgeBtn').addEventListener('click', async () => {
        const judgeId = document.getElementById('removeJudgeId').value;
        try {
            const response = await fetch(`${apiUrl}/remove-judge/${judgeId}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            document.getElementById('removeJudgeOutput').textContent = data;
        } catch (error) {
            console.error('Error removing judge:', error);
            document.getElementById('removeJudgeOutput').textContent = 'Error removing judge';
        }
    });

    document.getElementById('updateJudgeForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const judge = {
            judgeId: document.getElementById('updateJudgeId').value,
            name: document.getElementById('updateName').value,
            experience: document.getElementById('updateExperience').value,
            role: document.getElementById('updateRole').value,
            description: document.getElementById('updateDescription').value,
            roleWeightage: {
                softwareWeight: parseFloat(document.getElementById('updateSoftwareWeight').value),
                hardwareWeight: parseFloat(document.getElementById('updateHardwareWeight').value),
                visualArtWeight: parseFloat(document.getElementById('updateVisualArtWeight').value),
                musicWeight: parseFloat(document.getElementById('updateMusicWeight').value),
                wildCardWeight: parseFloat(document.getElementById('updateWildCardWeight').value)
            },
            themeWeightage: {
                aiWeight: parseFloat(document.getElementById('updateAiWeight').value),
                fashionWeight: parseFloat(document.getElementById('updateFashionWeight').value),
                governanceWeight: parseFloat(document.getElementById('updateGovernanceWeight').value),
                sportsGamingWeight: parseFloat(document.getElementById('updateSportsGamingWeight').value),
                securityPrivacyWeight: parseFloat(document.getElementById('updateSecurityPrivacyWeight').value)
            }
        };
        try {
            const response = await fetch(`${apiUrl}/update-judge/${judge.judgeId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(judge)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            document.getElementById('updateJudgeOutput').textContent = data;
        } catch (error) {
            console.error('Error updating judge:', error);
            document.getElementById('updateJudgeOutput').textContent = 'Error updating judge';
        }
    });
});