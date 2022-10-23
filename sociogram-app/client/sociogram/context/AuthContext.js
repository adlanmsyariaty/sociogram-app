import React from "react";

const AuthContext = React.createContext({
  isSignedIn: '',
  setIsSignedIn: () => {},
  accessToken: '',
  setAccessToken: () => {},
  screen: '',
  setScreen: () => {}
});

export default AuthContext