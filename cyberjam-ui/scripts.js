import config from './config.js';

const apiUrl = `${config.apiUrl}/judge-admin`;

async function getAllJudges() {
    try {
        const response = await fetch(`${apiUrl}/judges`);
        const data = await response.json();
        document.getElementById('judgesOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('judgesOutput').textContent = 'Error fetching judges';
    }
}

document.getElementById('addJudgeForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const judge = {
        judgeId: document.getElementById('judgeId').value,
        name: document.getElementById('name').value,
        experience: document.getElementById('experience').value,
        role: document.getElementById('role').value,
        description: document.getElementById('description').value,
        roleWeightage: {
            softwareWeight: parseFloat(document.getElementById('weightSoftware').value),
            hardwareWeight: parseFloat(document.getElementById('weightHardware').value),
            visualArtWeight: parseFloat(document.getElementById('weightVisualArt').value),
            musicWeight: parseFloat(document.getElementById('weightMusic').value),
            wildCardWeight: parseFloat(document.getElementById('weightWildCard').value)
        },
        themeWeightage: {
            aiWeight: parseFloat(document.getElementById('weightAi').value),
            fashionWeight: parseFloat(document.getElementById('weightFashion').value),
            governanceWeight: parseFloat(document.getElementById('weightGovernance').value),
            sportsGamingWeight: parseFloat(document.getElementById('weightSportsGaming').value),
            securityPrivacyWeight: parseFloat(document.getElementById('weightSecurityPrivacy').value)
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
        const data = await response.json();
        document.getElementById('addJudgeOutput').textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        document.getElementById('addJudgeOutput').textContent = 'Error adding judge';
    }
});

async function removeJudge() {
    const judgeId = document.getElementById('removeJudgeId').value;
    try {
        const response = await fetch(`${apiUrl}/remove-judge?id=${judgeId}`, {
            method: 'DELETE'
        });
        const data = await response.text();
        document.getElementById('removeJudgeOutput').textContent = data;
    } catch (error) {
        document.getElementById('removeJudgeOutput').textContent = 'Error removing judge';
    }
}

document.getElementById('updateJudgeForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const judgeId = document.getElementById('updateJudgeId').value;
    const updatedJudge = {
        judgeId: judgeId,
        name: document.getElementById('updateName').value,
        experience: document.getElementById('updateExperience').value,
        role: document.getElementById('updateRole').value,
        description: document.getElementById('updateDescription').value,
        roleWeightage: {
            softwareWeight: parseFloat(document.getElementById('updateWeightSoftware').value),
            hardwareWeight: parseFloat(document.getElementById('updateWeightHardware').value),
            visualArtWeight: parseFloat(document.getElementById('updateWeightVisualArt').value),
            musicWeight: parseFloat(document.getElementById('updateWeightMusic').value),
            wildCardWeight: parseFloat(document.getElementById('updateWeightWildCard').value)
        },
        themeWeightage: {
            aiWeight: parseFloat(document.getElementById('updateWeightAi').value),
            fashionWeight: parseFloat(document.getElementById('updateWeightFashion').value),
            governanceWeight: parseFloat(document.getElementById('updateWeightGovernance').value),
            sportsGamingWeight: parseFloat(document.getElementById('updateWeightSportsGaming').value),
            securityPrivacyWeight: parseFloat(document.getElementById('updateWeightSecurityPrivacy').value)
        }
    };
    try {
        const response = await fetch(`${apiUrl}/update-judge`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedJudge)
        });
        const data = await response.text();
        document.getElementById('updateJudgeOutput').textContent = data;
    } catch (error) {
        document.getElementById('updateJudgeOutput').textContent = 'Error updating judge';
    }
});