import { Box, Typography } from "@mui/material";

export default function LineItem({ header, data }) {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "flex-start",
        width: "100%",
      }}
    >
      <Typography
        sx={{
          fontSize: 14,
          fontWeight: 600,
        }}
      >
        {header}
      </Typography>
      <Typography
        sx={{
          fontSize: 14,
        }}
      >
        {data}
      </Typography>
    </Box>
  );
}