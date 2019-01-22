import React, { useContext, useState } from "react";
import PropTypes from "prop-types";
import { Switch, Redirect } from "react-router-dom";
import AuthContext from "../../../contexts/AuthContext";

const AuthenticatedSwitch = props => {
  const authContext = useContext(AuthContext);
  if (!authContext.isAuthenticated()) {
    return <Redirect to='/login' />;
  }

  if (!authContext.user) {
    authContext.getUserInfo().subscribe(r => {
      authContext.user = r;
    });
  }

  return <Switch>{props.children}</Switch>;
};

AuthenticatedSwitch.propTypes = {
  children: PropTypes.arrayOf(PropTypes.element)
};

export default AuthenticatedSwitch;
