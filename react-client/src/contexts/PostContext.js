import React from "react";

const PostContext = React.createContext({
  numFound: 0,
  list: []
});

export default PostContext;
