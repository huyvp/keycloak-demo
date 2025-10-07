import { Route, Routes } from "react-router-dom";
import Registration from "../pages/Registration";
import { Navigate, Outlet } from "react-router-dom";
import Login from "../pages/Login";
import keyloak from "../configurations/keycloak";
import Home from "../pages/Home";

const ProtectedRoute = () => {
    return keyloak.authenticated ? <Outlet /> : <Navigate to="/login" />;
};


export default function AppRouter() {
    return (

        <Routes>
            <Route path="/registration" element={<Registration />} />
            <Route path="/login" element={<Login />} />
            <Route path="/" element={<ProtectedRoute />}>
                <Route path="/" element={<Home />} />
            </Route>
        </Routes>

    );
};

