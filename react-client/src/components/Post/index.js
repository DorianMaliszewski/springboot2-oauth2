import React from "react";
import PropTypes from "prop-types";
import "./index.css";

const Post = props => {
  const { post } = props;
  return (
    <div className='post'>
      <h3>{post.title}</h3>
      <p>{post.body}</p>
      <p style={{ marginLeft: "80%" }} className='text-muted'>
        Created by {post.creator}
      </p>
    </div>
  );
};

Post.propTypes = {
  post: PropTypes.object.isRequired
};

export default Post;
