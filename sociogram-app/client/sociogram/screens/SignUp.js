import { useNavigation } from "@react-navigation/native";
import React, { useState } from "react";
import { Text, TextInput, TouchableOpacity, View } from "react-native";

function SignUp() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const navigation = useNavigation();

  const emailInput = (text) => {
    setEmail(text);
  };

  const passwordInput = (text) => {
    setPassword(text);
  };

  const nameInput = (text) => {
    setName(text);
  };

  const usernameInput = (text) => {
    setUsername(text);
  };

  const signUp = () => {
    fetch("http://54.251.82.169/sociogram-app/users/signup", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name,
        username,
        email,
        password,
      }),
    })
      .then((res) => res.json())
      .then((res) => {
        if (res.statusCode == 201) {
          setEmail("");
          setPassword("");
          setUsername("");
          setName("");
          alert("Success to create account");
          navigation.navigate("Login");
        } else {
          throw { message: res.message };
        }
      })
      .catch((err) => {
        alert(err.message.join("\n"));
      });
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
      <View style={{ flex: 2, justifyContent: "center" }}>
        <Text style={{ fontSize: 50, fontWeight: "bold", color: "white" }}>
          Sociogram
        </Text>
      </View>
      <View style={{ flex: 3, width: "60%" }}>
        <TextInput
          placeholder="Name"
          style={{
            backgroundColor: "white",
            paddingVertical: 5,
            paddingHorizontal: 10,
            marginBottom: 20,
            borderRadius: 8,
          }}
          onChangeText={(text) => nameInput(text)}
        />
        <TextInput
          placeholder="Username"
          style={{
            backgroundColor: "white",
            paddingVertical: 5,
            paddingHorizontal: 10,
            marginBottom: 20,
            borderRadius: 8,
          }}
          onChangeText={(text) => usernameInput(text)}
        />
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
        <TouchableOpacity onPress={() => signUp()}>
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
            <Text style={{ color: "white", fontSize: 20 }}>Sign Up</Text>
          </View>
        </TouchableOpacity>
      </View>
    </View>
  );
}

export default SignUp;
