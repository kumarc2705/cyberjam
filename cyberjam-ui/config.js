const defaultApiUrl = 'http://localhost:8080';

const config = {
    get apiUrl() {
        return localStorage.getItem('apiUrl') || defaultApiUrl;
    }
};

export default config;