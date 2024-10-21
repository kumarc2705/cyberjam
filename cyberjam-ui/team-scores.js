import config from './config.js';
const apiUrl = `${config.apiUrl}/results`;

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("fetchScores").addEventListener("click", function() {
        const teamName = document.getElementById("teamNameInput").value;
        if (!teamName) {
            document.getElementById("error").textContent = 'Please enter a team name.';
            return;
        }
        const url = `http://${apiUrl}/${teamName}/all-scores`;

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                document.getElementById("teamName").textContent = teamName;
                document.getElementById("totalScore").textContent = data.totalScore;

                const roleScoresList = document.getElementById("roleScores");
                roleScoresList.innerHTML = ''; // Clear previous results
                for (const [role, score] of Object.entries(data.roleScores)) {
                    const listItem = document.createElement("li");
                    listItem.textContent = `${role}: ${score}`;
                    roleScoresList.appendChild(listItem);
                }

                const themeScoresList = document.getElementById("themeScores");
                themeScoresList.innerHTML = ''; // Clear previous results
                for (const [theme, score] of Object.entries(data.themeScores)) {
                    const listItem = document.createElement("li");
                    listItem.textContent = `${theme}: ${score}`;
                    themeScoresList.appendChild(listItem);
                }

                document.getElementById("error").textContent = ''; // Clear any previous error
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                document.getElementById("error").textContent = 'Error fetching data: ' + error.message;
            });
    });
});