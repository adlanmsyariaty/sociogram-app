import { useFocusEffect, useNavigation } from "@react-navigation/native";
import React, { useContext } from "react";
import { ActivityIndicator, View } from "react-native";
import AuthContext from "../context/AuthContext";
import AsyncStorage from '@react-native-async-storage/async-storage';

function SignOut() {
  const { setIsSignedIn, setScreen, setAccessToken } = useContext(AuthContext)
  useFocusEffect(
    React.useCallback(() => {
      setIsSignedIn(false)
      setScreen('')
      setAccessToken('')
      removeStorage()
    }, [])
  )

  async function removeStorage() {
    await AsyncStorage.removeItem('access_token')
  }

  return (
    <View
      style={{
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <ActivityIndicator size="large" color="#be123c" />
    </View>
  );
}

export default SignOut;
