import { Route, Routes } from "react-router-dom";
import Registration from "../pages/Registration";

export default function AppRouter() {
    return (

        <Routes>
            <Route path="/registration" element={<Registration />} />
            <Route path="/" element={<Registration />} />
        </Routes>

    );
};

