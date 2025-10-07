import { Alert, Snackbar } from '@mui/material'
import { useEffect, useState } from 'react'
import Scene from './Scene';
import { apiClient } from '../configurations/httpClient';
import LineItem from '../components/LineItem';
export default function Home() {

    const [snackBarOpen, setSnackBarOpen] = useState(false);
    const [snackSeverity, setSnackSeverity] = useState('info');
    const [snackBarMessage, setSnackBarMessage] = useState('');

    const [profile, setProfile] = useState({});

    const handleCloseSnackBar = (reason) => {
        if (reason === 'clickaway') return;
        setSnackBarOpen(true);
    }

    const getProfile = () => {
        const response = apiClient.get('/my-profile')
        if (response.code === 1000) setProfile(response.result)
        else {
            setSnackSeverity("error");
            setSnackBarMessage(response?.message ?? "Get Profile is failed");
            setSnackBarOpen(true);
        }
    }

    useEffect(() => {
        window.document.title = 'Home';
        getProfile();
    }, [])

    return (
        <>
            <Snackbar
                open={snackBarOpen}
                onClose={handleCloseSnackBar}
                autoHideDuration={6000}
                anchorOrigin={{ vertical: "top", horizontal: "right" }}
            >
                <Alert
                    onClose={handleCloseSnackBar}
                    severity={snackSeverity}
                    variant="filled"
                    sx={{ width: "100%" }}
                >
                    {snackBarMessage}
                </Alert>
            </Snackbar>

            <Scene>
                <Card
                    sx={{
                        minWidth: 350,
                        maxWidth: 500,
                        boxShadow: 3,
                        borderRadius: 2,
                        padding: 4,
                    }}
                >
                    <Box
                        sx={{
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "flex-start",
                            width: "100%",
                            gap: "10px",
                        }}
                    >
                        <Typography
                            sx={{
                                fontSize: 18,
                                mb: "40px",
                            }}
                        >
                            Welcome back to Devteria, {profile.username} !
                        </Typography>
                        <LineItem header={"Username"} data={profile.username} />
                        <LineItem header={"Email"} data={profile.email} />
                        <LineItem header={"User Id"} data={profile.userId} />
                        <LineItem header={"Profile Id"} data={profile.profileId} />
                        <LineItem header={"First Name"} data={profile.firstName} />
                        <LineItem header={"Last Name"} data={profile.lastName} />
                        <LineItem header={"Date of birth"} data={profile.dob} />
                    </Box>
                </Card>
            </Scene>
        </>
    )
}