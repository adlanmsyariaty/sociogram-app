import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Home from "../screens/Home.js";
import Comment from "../screens/Comment.js";
import Create from "../screens/Create.js";
import Login from "../screens/Login.js";
import SignUp from "../screens/SignUp.js";
import React, { useContext, useState } from "react";
import AuthContext from "../context/AuthContext.js";
import TabNav from "./TabNav.js";
import Ionicons from 'react-native-vector-icons/Ionicons';

function MainStack() {
  const Stack = createNativeStackNavigator();
  const { isSignedIn } = useContext(AuthContext)

  return (
    <Stack.Navigator>
      {!isSignedIn ? (
        <>
          <Stack.Screen
            name="Login"
            component={Login}
            options={{ headerShown: false }}
          />
          <Stack.Screen
            name="SignUp"
            component={SignUp}
            options={{ headerShown: false }}
          />
        </>
      ) : (
        <>
          <Stack.Screen
            name="TabNav"
            component={TabNav}
            options={{ headerShown: false }}
          />
          <Stack.Screen
            name="Home"
            component={Home}
            options={{ headerShown: false }}
          />
          <Stack.Screen
            name="Comment"
            component={Comment}
            options={{ headerShown: false }}
          />
          <Stack.Screen
            name="Create"
            component={Create}
            options={{ headerShown: false }}
          />
        </>
      )}
    </Stack.Navigator>
  );
}

export default MainStack;
