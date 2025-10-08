import { Box, Toolbar } from '@mui/material'
export default function Scene({ children }) {

    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'row'
            }}
        >
            <Box
                component="main"
            >
                <Toolbar />
                {children}
            </Box>

        </Box>
    )

}