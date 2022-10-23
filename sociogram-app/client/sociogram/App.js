import React, { useEffect, useState } from "react";
import { StatusBar } from "expo-status-bar";
import { StyleSheet, View } from "react-native";
import { NavigationContainer, useFocusEffect } from "@react-navigation/native";
import TopBar from "./components/TopBar";
import MainStack from "./navigation/MainStack";
import Create from "./screens/Create";
import Login from "./screens/Login";
import SignUp from "./screens/SignUp";
import AsyncStorage from '@react-native-async-storage/async-storage';
import AuthContext from "./context/AuthContext";

export default function App() {
  const [isSignedIn, setIsSignedIn] = useState(false)
  const [accessToken, setAccessToken] = useState('')
  const [screen, setScreen] = useState('')
  const authContext = {
    isSignedIn,
    setIsSignedIn,
    accessToken,
    setAccessToken,
    screen,
    setScreen
  }

  useEffect(() => {
    async function tokenCheck() {
      try {
        let accessToken = await AsyncStorage.getItem('access_token')
        if (accessToken) {
          setIsSignedIn(true)
          setAccessToken(accessToken)
        }
      } catch (error) {
        alert(error)
      }
    }
    tokenCheck()
  }, [])

  return (
    <AuthContext.Provider value={authContext}>
      <NavigationContainer>
        <View style={styles.container}>
          <StatusBar style="auto" />
          {isSignedIn && <TopBar />}
          <MainStack />
        </View>
      </NavigationContainer>
    </AuthContext.Provider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
  },
});
