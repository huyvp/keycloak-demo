import { Box, Card, CardContent, Snackbar } from '@mui/material'

export default function Registration() {
    return (
        <>
            <Snackbar></Snackbar>
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
                    <CardContent>HIIIIII</CardContent>
                </Card>
            </Box>
        </>
    )
}