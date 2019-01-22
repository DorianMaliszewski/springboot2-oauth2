import React, { useState, useContext } from "react";
import PropTypes from "prop-types";
import AuthContext from "../../contexts/AuthContext";
import "./index.css";
import { Redirect, withRouter } from "react-router-dom";
import { AUTH_TOKEN } from "../../constants";
import { ROUTES } from "../../constants/routes";

const Login = props => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);
  const authContext = useContext(AuthContext);

  const handleLogin = e => {
    e.preventDefault();
    if (username && password) {
      setError(false);
      authContext.login(username, password).subscribe(
        res => {
          if (res.response && res.response.access_token) {
            localStorage.setItem(
              AUTH_TOKEN,
              JSON.stringify({
                ...res.response,
                saveAt: new Date()
              })
            );
            props.history.push(ROUTES.HOME.path);
          }
          console.log("Login response", res.response);
        },
        err => {
          console.log("Une erreur est survenue lors de l'authentification", err);
          setError(true);
        }
      );
    }
  };

  if (authContext.isAuthenticated()) {
    return <Redirect to={"/"} />;
  }

  return (
    <div>
      <div className='login-block'>
        <h2>Login</h2>
        <form className='login-form' onSubmit={handleLogin}>
          <div>
            <label>Username</label>
            <input type='text' value={username} onChange={e => setUsername(e.target.value)} />
          </div>
          <div>
            <label>Password</label>
            <input type='password' value={password} onChange={e => setPassword(e.target.value)} />
          </div>
          <div style={{ justifyContent: "center", display: "flex" }}>
            <input type='submit' value='Login' />
          </div>
          {error ? <div className='error-message'>Bad Credentials</div> : null}
        </form>
      </div>
    </div>
  );
};

Login.propTypes = {};

export default withRouter(Login);
