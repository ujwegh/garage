import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';

import {Route, Switch} from 'react-router-dom';

import Layout from "./layout/Layout";
import Home from "./pages/Home";
import Login from "./pages/Login";
// import Lk from "./pages/Lk";
// import Tables from "./pages/Tables";


const App = () => {
    const [id, setId] = useState(null);

    useEffect(() => {
        setId('хуууййй')
    },[])

    return (
        <Layout>
            {id
                ?(<Switch>
                    <Route path='/' exact component={Login}/>
                    <Route path='/lk' component={Home}/>
                </Switch>)
                :(<Switch>
                    <Route path='/' exact component={Login}/>
                </Switch>)
            }
        </Layout>
    )
}

export default App;
