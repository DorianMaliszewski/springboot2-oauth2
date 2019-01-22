import { ajax } from "rxjs/ajax";
import { map } from "rxjs/operators";
import { RESOURCE_SERVER_URL, AUTH_TOKEN } from "../constants";

const findAllPosts = () => {
  return ajax
    .get(RESOURCE_SERVER_URL + "/api/posts", {
      Authorization: "Bearer " + JSON.parse(localStorage.getItem(AUTH_TOKEN)).access_token
    })
    .pipe(map(res => res.response));
};

export default {
  findAllPosts
};
