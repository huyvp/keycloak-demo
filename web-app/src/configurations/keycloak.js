import Keycloak from 'keycloak-js'

export const KEYCLOACK_CONFIG = {
    url: "http://localhost:8180",
    realm: "web-app",
    clientId: "huyvp_www",
};

const keycloak = new Keycloak({
    url: KEYCLOACK_CONFIG.url,
    realm: KEYCLOACK_CONFIG.realm,
    clientId: KEYCLOACK_CONFIG.clientId
})

export const keycloakLogout = () => {
    keycloak.logout();
}

export default keycloak;