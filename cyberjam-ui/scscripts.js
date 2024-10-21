import config from './config.js';

const apiUrl = `${config.apiUrl}/score-view`;

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('assignScoresForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const teamId = document.getElementById('assignTeamId').value;
        const judgeId = document.getElementById('assignJudgeId').value;
        const roleScores = {
            softwareScore: parseFloat(document.getElementById('assignSoftwareScore').value),
            hardwareScore: parseFloat(document.getElementById('assignHardwareScore').value),
            visualArtScore: parseFloat(document.getElementById('assignVisualArtScore').value),
            musicScore: parseFloat(document.getElementById('assignMusicScore').value),
            wildCardScore: parseFloat(document.getElementById('assignWildCardScore').value)
        };
        const themeScores = {
            aiScore: parseFloat(document.getElementById('assignAiScore').value),
            fashionScore: parseFloat(document.getElementById('assignFashionScore').value),
            governanceScore: parseFloat(document.getElementById('assignGovernanceScore').value),
            sportsGamingScore: parseFloat(document.getElementById('assignSportsGamingScore').value),
            securityPrivacyScore: parseFloat(document.getElementById('assignSecurityPrivacyScore').value)
        };
        try {
            const response = await fetch(`${apiUrl}/assign-score?teamId=${teamId}&judgeId=${judgeId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ roleScores, themeScores })
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            document.getElementById('assignScoresOutput').textContent = data;
        } catch (error) {
            console.error('Error assigning scores:', error);
            document.getElementById('assignScoresOutput').textContent = 'Error assigning scores';
        }
    });

    document.getElementById('updateRoleScoreForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const teamId = document.getElementById('updateRoleTeamId').value;
        const judgeId = document.getElementById('updateRoleJudgeId').value;
        const roleScores = {
            softwareScore: parseFloat(document.getElementById('updateSoftwareScore').value),
            hardwareScore: parseFloat(document.getElementById('updateHardwareScore').value),
            visualArtScore: parseFloat(document.getElementById('updateVisualArtScore').value),
            musicScore: parseFloat(document.getElementById('updateMusicScore').value),
            wildCardScore: parseFloat(document.getElementById('updateWildCardScore').value)
        };
        try {
            const response = await fetch(`${apiUrl}/update-role-score?teamId=${teamId}&judgeId=${judgeId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(roleScores)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            document.getElementById('updateRoleScoreOutput').textContent = data;
        } catch (error) {
            console.error('Error updating role scores:', error);
            document.getElementById('updateRoleScoreOutput').textContent = 'Error updating role scores';
        }
    });

    document.getElementById('updateThemeScoreForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const teamId = document.getElementById('updateThemeTeamId').value;
        const judgeId = document.getElementById('updateThemeJudgeId').value;
        const themeScores = {
            aiScore: parseFloat(document.getElementById('updateAiScore').value),
            fashionScore: parseFloat(document.getElementById('updateFashionScore').value),
            governanceScore: parseFloat(document.getElementById('updateGovernanceScore').value),
            sportsGamingScore: parseFloat(document.getElementById('updateSportsGamingScore').value),
            securityPrivacyScore: parseFloat(document.getElementById('updateSecurityPrivacyScore').value)
        };
        try {
            const response = await fetch(`${apiUrl}/update-theme-score?teamId=${teamId}&judgeId=${judgeId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(themeScores)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            document.getElementById('updateThemeScoreOutput').textContent = data;
        } catch (error) {
            console.error('Error updating theme scores:', error);
            document.getElementById('updateThemeScoreOutput').textContent = 'Error updating theme scores';
        }
    });
});