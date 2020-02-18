import React from "react";

// import MainMenu from "../components/MainMenu";
// import {Col, Container, Row} from "react-bootstrap";
// import SideMenu from "../components/SideMenu";
// import useGetAllTable from "../hooks/useGetAllTable";
import Login from "../pages/Login";
const Layout = ({children}) => {
    // const isOnline = useGetAllTable();
    return (
        <>
            {/*<MainMenu/>*/}
            {/*<Container fluid='md'>*/}
            {/*    <Row>*/}
            {/*        <Col style={{marginRight: 100}}><SideMenu/></Col>*/}
            {/*        <Col md={8} style={{marginTop: 20}}>{children}</Col>*/}
            {/*        <Col>{isOnline}</Col>*/}
            {/*    </Row>*/}
            {/*</Container>*/}
            <Login/>
        </>
    )
};

export default Layout;