import Keycloak from 'keycloak-js';

const keycloakConfig = {
  url: import.meta.env.VITE_KEYCLOAK_URL,
  realm: 'templateizer_auth_service', // Название Realm
  clientId: 'templateizer_react_app', // Client ID
};

const keycloak = new Keycloak(keycloakConfig);

export default keycloak;
