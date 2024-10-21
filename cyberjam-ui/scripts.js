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
                software: document.getElementById('weightSoftware').value,
                hardware: document.getElementById('weightHardware').value,
                visualArt: document.getElementById('weightVisualArt').value,
                music: document.getElementById('weightMusic').value,
                wildCard: document.getElementById('weightWildCard').value
            },
            themeWeightage: {
                ai: document.getElementById('weightAi').value,
                fashion: document.getElementById('weightFashion').value,
                governance: document.getElementById('weightGovernance').value,
                sportsGaming: document.getElementById('weightSportsGaming').value,
                securityPrivacy: document.getElementById('weightSecurityPrivacy').value
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
                throw new Error('Network response was not ok');
            }
            const data = await response.text(); // Handle response as text
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
                software: document.getElementById('updateWeightSoftware').value,
                hardware: document.getElementById('updateWeightHardware').value,
                visualArt: document.getElementById('updateWeightVisualArt').value,
                music: document.getElementById('updateWeightMusic').value,
                wildCard: document.getElementById('updateWeightWildCard').value
            },
            themeWeightage: {
                ai: document.getElementById('updateWeightAi').value,
                fashion: document.getElementById('updateWeightFashion').value,
                governance: document.getElementById('updateWeightGovernance').value,
                sportsGaming: document.getElementById('updateWeightSportsGaming').value,
                securityPrivacy: document.getElementById('updateWeightSecurityPrivacy').value
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