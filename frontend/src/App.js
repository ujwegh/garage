import React from 'react';
import logo from './logo.svg';
import './App.css';

import {Route, Switch} from 'react-router-dom';

import Layout from "./layout/Layout";
// import Home from "./pages/Home";
import Login from "./pages/Login";
// import Lk from "./pages/Lk";
// import Tables from "./pages/Tables";


const App = () => (

    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>

    <Layout>
        <Switch>
            {/*<Route path='/tables' component={Tables}/>*/}
            {/*<Route path='/lk' component={Lk}/>*/}
            <Route path='/' exact component={Login}/>
        </Switch>
    </Layout>

);

export default App;
