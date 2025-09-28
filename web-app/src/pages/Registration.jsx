import { Alert, Box, Button, Card, CardContent, Snackbar, TextField, Typography } from '@mui/material'
import { useState } from 'react'

export default function Registration() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");

    const [snackBarOpen, setSnackBarOpen] = useState(false);
    const [snackBarMessage, setSnackBarMessage] = useState("");
    const [snackSeverity, setSnackSeverity] = useState("info");

    const handleCloseSnackBar = (reason) => {
        if (reason === "clickaway") return;
        setSnackBarOpen(false);
    };
    const handleOnSubmit = async (event) => {
        event.preventDefault();
        const payload = {
            username: username,
            password: password,
            firstName: firstname,
            lastName: lastname,
            email: email,
        }

        // const response = fetch(
        //     'http://localhost:8080/profile',
        //     payload)
        //     .then()
    }

    return (
        <>
            <Snackbar
                open={snackBarOpen}
                onClose={handleCloseSnackBar}
                autoHideDuration={6000}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert
                    onClose={handleCloseSnackBar}
                    security={snackSeverity}
                    variant='filled'
                    sx={{ width: "100%" }}
                >
                    {setSnackBarMessage}
                </Alert>
            </Snackbar>
            <Box
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                height="100vh"
                bgcolor="#f0f2f5"
            >
                <Card
                    sx={{
                        minWidth: 400,
                        maxWidth: 500,
                        boxShadow: 3,
                        borderRadius: 3,
                        padding: 4,
                    }}
                >
                    <CardContent>
                        <Typography variant='h5' component='h1' gutterBottom>
                            Welcome, Let's create an account
                        </Typography>
                        <Box
                            component="form"
                            display="flex"
                            flexDirection="column"
                            alignItems="center"
                            justifyContent="center"
                            width="100%"
                            onSubmit={handleOnSubmit}
                        >
                            <TextField
                                label="Username"
                                variant='outlined'
                                fullWidth
                                margin='normal'
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />
                            <TextField
                                label="Password"
                                type='password'
                                variant='outlined'
                                fullWidth
                                margin='normal'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                            <TextField
                                label="Email"
                                type='email'
                                variant='outlined'
                                fullWidth
                                margin='normal'
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                            <TextField
                                label="Firstname"
                                variant='outlined'
                                fullWidth
                                margin='normal'
                                value={firstname}
                                onChange={(e) => setFirstname(e.target.value)}
                            />
                            <TextField
                                label="Lastname"
                                variant='outlined'
                                fullWidth
                                margin='normal'
                                value={lastname}
                                onChange={(e) => setLastname(e.target.value)}
                            />
                            <Button
                                type='submit'
                                variant='contained'
                                color='primary'
                                size='large'
                                onClick={handleOnSubmit}
                                fullWidth
                                sx={{ mt: '15px', mb: '15px' }}
                            >
                                Sign Up
                            </Button>
                        </Box>
                    </CardContent>
                </Card>
            </Box>
        </>
    )
}