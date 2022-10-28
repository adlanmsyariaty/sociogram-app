import { useFocusEffect, useNavigation } from "@react-navigation/native";
import React, { useContext, useState } from "react";
import {
  View,
  TextInput,
  Text,
  ScrollView,
  TouchableOpacity,
} from "react-native";
import AddImage from "../components/AddImage";
import AuthContext from "../context/AuthContext";

function Create() {
  const [imageUrl, setImageUrl] = useState("");
  const [caption, setCaption] = useState("");
  const [loading, setLoading] = useState(false);
  const navigation = useNavigation();
  const { accessToken, setScreen } = useContext(AuthContext);

  const captionInput = (text) => {
    setCaption(text);
  };

  useFocusEffect(
    React.useCallback(() => {
      setScreen("Create");
      setImageUrl("");
      setCaption("");
    }, [])
  );

  const submitPost = async () => {
    try {
      let data = {
        imageUrl: imageUrl,
        caption: caption,
      };
      let result = await fetch("http://54.251.82.169/sociogram-app/posts/add", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          access_token: accessToken,
        },
        body: JSON.stringify(data),
      });
      const res = await result.json();
      if (res.statusCode == 201) {
        setCaption("");
        setImageUrl("");
        navigation.navigate("Home");
      } else {
        throw { message: res.message };
      }
    } catch (error) {
      alert(error.message.join("\n"));
    }
  };

  return (
    <View
      style={{
        flex: 1,
        justifyContent: "space-between",
        backgroundColor: "white",
      }}
    >
      <ScrollView
        contentContainerStyle={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <View
          style={{
            flex: 2,
            alignItems: "center",
            justifyContent: "flex-end",
            marginTop: 50,
          }}
        >
          <AddImage
            imageUrl={imageUrl}
            setImageUrl={setImageUrl}
            loading={loading}
            setLoading={setLoading}
          />
        </View>
        <View
          style={{
            flex: 2,
            flexDirection: "row",
            minWidth: "100%",
            alignItems: "flex-start",
            justifyContent: "center",
            padding: 10,
            marginTop: 30,
          }}
        >
          <TextInput
            value={caption}
            onChangeText={(text) => captionInput(text)}
            style={{
              width: "90%",
              padding: 10,
              backgroundColor: "#e2e8f0",
            }}
            placeholder="Write your caption"
            multiline
            numberOfLines={5}
            textAlignVertical={"top"}
          />
        </View>
      </ScrollView>
      <View>
        <TouchableOpacity onPress={() => submitPost()}>
          <View
            style={{
              justifyContent: "center",
              alignItems: "center",
              padding: 10,
              backgroundColor: "#be123c",
            }}
          >
            <Text style={{ color: "white", fontWeight: "bold", fontSize: 20 }}>
              POST
            </Text>
          </View>
        </TouchableOpacity>
      </View>
    </View>
  );
}

export default Create;
