import React, { useContext, useState } from "react";
import PropTypes from "prop-types";
import { AUTH_TOKEN } from "../../constants";
import { ROUTES } from "../../constants/routes";
import { withRouter } from "react-router-dom";
import "./index.css";
import AuthContext from "../../contexts/AuthContext";
import PostContext from "../../contexts/PostContext";
import Post from "../Post";

const Home = props => {
  const authContext = useContext(AuthContext);
  const postContext = useContext(PostContext);
  const [username, setUsername] = useState("");
  const [posts, setPosts] = useState();
  const logout = e => {
    localStorage.removeItem(AUTH_TOKEN);
    props.history.push(ROUTES.LOGIN.path);
  };

  if (!username) {
    authContext.getUserInfo().subscribe(r => {
      setUsername(r.username);
    });
  }

  if (!posts) {
    postContext.findAllPosts().subscribe(r => {
      if (r) {
        postContext.list = r.items;
        postContext.numFound = r.numFound;
        setPosts(r.items);
      }
    });
  }

  return (
    <>
      <div id='top-banner'>
        <p className='logo'>Made by Dorian Maliszewski</p>
        <p style={{ paddingTop: "5px", marginLeft: "80px" }}>Logged as {username}</p>
        <button className='disconnect' onClick={logout}>
          Logout
        </button>
      </div>
      <main className='main'>
        <div>
          <div style={{ display: "flex", width: "100%", flexDirection: "row" }}>
            <h1 style={{ width: "80%" }}>Posts</h1>
            <p style={{ lineHeight: "50px" }}>({postContext.numFound})</p>
          </div>
          <hr />
          {posts && posts.map(p => <Post post={p} />)}
        </div>
      </main>
    </>
  );
};

Home.propTypes = {};

export default withRouter(Home);
