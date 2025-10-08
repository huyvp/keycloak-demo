import { StrictMode, useEffect, useState } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter as Router } from 'react-router-dom'
import './index.css'
import { Box, CircularProgress } from '@mui/material'
import App from './App.jsx'
import keycloak from './configurations/keycloak.js'


export function Main() {
  const [keycloakInitialized, setKeycloakInitialized] = useState(false);

  useEffect(() => {
    keycloak.init({ onLoad: 'check-sso' })
      .then(() => {
        setKeycloakInitialized(true);
      })
      .catch(() => {
        console.error('Authenticated Failed')
      });
  }, [])

  if (!keycloakInitialized) {
    return (
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
        }}
      >
        <CircularProgress></CircularProgress>
      </Box>
    )
  }
  return <App />
}

createRoot(document.getElementById('root')).render(
  <Router>
    <Main />
  </Router>
)
