import keycloak from "./keycloak";

export const BASE_URL = "http://localhost:9002";

async function request(method, url, data = null, customHeaders = {}) {
    const token = keycloak.token;

    const headers = {
        "Content-Type": "application/json",
        ...(token ? { Authorization: `Bearer ${token}` } : {}),
        ...customHeaders,
    };

    const options = {
        method,
        headers,
    };

    if (data) options.body = JSON.stringify(data);


    try {
        const response = await fetch(`${BASE_URL}${url}`, options);

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            throw { status: response.status, data: errorData };
        }
        return await response.json();
    } catch (error) {
        console.error("❌ API error:", error);
        throw error;
    }
}

export const apiClient = {
    get: (url, headers) => request("GET", url, null, headers),
    post: (url, data, headers) => request("POST", url, data, headers),
    put: (url, data, headers) => request("PUT", url, data, headers),
    delete: (url, headers) => request("DELETE", url, null, headers),
};
