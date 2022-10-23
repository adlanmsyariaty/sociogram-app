import React, { useContext, useState } from "react";
import { Text, TextInput, TouchableOpacity, View } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { useNavigation } from "@react-navigation/native";
import AuthContext from "../context/AuthContext";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigation = useNavigation();
  const { setIsSignedIn, setAccessToken, setScreen } = useContext(AuthContext);

  const emailInput = (text) => {
    setEmail(text);
  };

  const passwordInput = (text) => {
    setPassword(text);
  };

  const login = () => {
    fetch("http://54.255.134.36:3001/users/login", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email,
        password,
      }),
    })
      .then((res) => res.json())
      .then((res) => {
        if (res.statusCode == 200) {
          setIsSignedIn(true);
          setAccessToken(res.data.access_token);
          return AsyncStorage.setItem("access_token", res.data.access_token);
        } else {
          throw { message: res.message };
        }
      })
      .then(() => {
        navigation.navigate("TabNav");
        setEmail("");
        setPassword("");
      })
      .catch((err) => alert(err.message));
  };

  const signUpPage = () => {
    navigation.navigate("SignUp");
  };

  return (
    <View
      style={{
        flex: 1,
        minWidth: "100%",
        backgroundColor: "#fb7185",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <View style={{ flex: 1, justifyContent: "center" }}>
        <Text style={{ fontSize: 50, fontWeight: "bold", color: "white" }}>
          Sociogram
        </Text>
      </View>
      <View style={{ flex: 1 }}>
        <TextInput
          placeholder="Email"
          style={{
            backgroundColor: "white",
            paddingVertical: 5,
            paddingHorizontal: 10,
            marginBottom: 20,
            borderRadius: 8,
          }}
          onChangeText={(text) => emailInput(text)}
        />
        <TextInput
          placeholder="Password"
          style={{
            backgroundColor: "white",
            paddingVertical: 5,
            paddingHorizontal: 10,
            marginBottom: 20,
            borderRadius: 8,
          }}
          onChangeText={(text) => passwordInput(text)}
          secureTextEntry={true}
        />
        <TouchableOpacity onPress={() => login()}>
          <View
            style={{
              backgroundColor: "#881337",
              justifyContent: "center",
              alignItems: "center",
              paddingVertical: 5,
              borderRadius: 8,
              marginBottom: 10,
            }}
          >
            <Text style={{ color: "white", fontSize: 20 }}>Login</Text>
          </View>
        </TouchableOpacity>
        <View style={{ alignItems: "center", flexDirection: "row" }}>
          <Text
            style={{
              color: "black",
              fontSize: 17,
              alignItems: "center",
            }}
          >
            Don't have an account?{" "}
          </Text>
          <TouchableOpacity onPress={() => signUpPage()}>
            <Text style={{ color: "white", fontSize: 17, fontWeight: "bold" }}>
              Sign up
            </Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

export default Login;
