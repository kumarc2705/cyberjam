const defaultApiUrl = 'http://localhost:8080';
const storedApiUrl = localStorage.getItem('apiUrl');
const config = {
    apiUrl: storedApiUrl || defaultApiUrl
};