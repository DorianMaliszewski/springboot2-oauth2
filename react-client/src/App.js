import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import AuthenticatedSwitch from "./components/Core/AuthenticatedSwitch";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import AuthContext from "./contexts/AuthContext";
import PostContext from "./contexts/PostContext";
import authProvider from "./providers/authProvider";
import postProvider from "./providers/postProvider";
import Login from "./components/Login";
import { ROUTES } from "./constants/routes";

class App extends Component {
  render() {
    return (
      <div>
        <BrowserRouter>
          <AuthContext.Provider value={authProvider}>
            <PostContext.Provider value={postProvider}>
              <Switch>
                <Route path={ROUTES.LOGIN.path} component={Login} />
                <AuthenticatedSwitch>
                  <Route path={ROUTES.HOME.path} component={Home} />
                  <Route component={Home} />
                </AuthenticatedSwitch>
              </Switch>
            </PostContext.Provider>
          </AuthContext.Provider>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
