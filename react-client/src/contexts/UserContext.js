import React from "react";

const UserContext = React.createContext({
  numFound: 0,
  list: []
});

export default UserContext;
