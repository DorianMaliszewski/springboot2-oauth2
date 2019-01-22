import { AUTH_TOKEN, OAUTH_SERVER_URL, OAUTH_CREDENTIAL } from "../constants";
import { ajax } from "rxjs/ajax";
import { map } from "rxjs/operators";

const login = (username, password) => {
  return ajax.post(
    OAUTH_SERVER_URL + "/oauth/token",
    {
      grant_type: "password",
      username,
      password
    },
    {
      Authorization: "Basic " + OAUTH_CREDENTIAL
    }
  );
};

const getUserInfo = () => {
  return ajax
    .get(OAUTH_SERVER_URL + "/api/admin/users/current", {
      Authorization: "Bearer " + JSON.parse(localStorage.getItem(AUTH_TOKEN)).access_token
    })
    .pipe(map(res => res.response));
};

const isAuthenticated = () => {
  return localStorage.getItem(AUTH_TOKEN) ? true : false;
};

const logout = () => {
  localStorage.removeItem(AUTH_TOKEN);
};

const state = {
  user: {
    tokens: 50
  }
};

export default {
  getUserInfo,
  isAuthenticated,
  login,
  logout,
  state
};
