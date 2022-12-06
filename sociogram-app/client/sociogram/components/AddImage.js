import React, { useContext, useState } from "react";
import {
  Text,
  TouchableOpacity,
  Image,
  ActivityIndicator,
  View,
} from "react-native";
import { Feather } from "@expo/vector-icons";
import * as DocumentPicker from "expo-document-picker";
import AuthContext from "../context/AuthContext.js";
import { Dimensions } from "react-native";
import { useFocusEffect } from "@react-navigation/native";

const window = Dimensions.get("window");
const screen = Dimensions.get("screen");

function AddImage({ imageUrl, setImageUrl, setLoading, loading }) {
  const { accessToken } = useContext(AuthContext);
  const [imageScale, setImageScale] = useState(0);

  const pickDocument = async () => {
    let result = await DocumentPicker.getDocumentAsync({
      type: "*/*",
      copyToCacheDirectory: true,
    })
      .then((response) => {
        let options = {};
        if (response.type == "success") {
          let { name, size, uri } = response;
          let nameParts = name.split(".");
          let fileType = nameParts[nameParts.length - 1];
          var fileToUpload = {
            name: name,
            size: size,
            uri: uri,
            type: "application/" + fileType,
          };
          let formData = new FormData();
          formData.append("image", fileToUpload);
          options = {
            method: "POST",
            body: formData,
            headers: {
              Accept: "application/json",
              "Content-Type": "multipart/form-data",
              Authorization: `Bearer ${accessToken}`,
            },
          };
        } else {
          throw { message: response.type };
        }
        setLoading(true);
        return fetch(
          "http://127.0.0.1:3001/sociogram-app/posts/imageUpload",
          options
        );
      })
      .then((res) => res.json())
      .then((res) => {
        if (res.statusCode == 201) {
          Image.getSize(
            res.data.imageUrl,
            (width, height) => {
              setImageScale(width / height);
              setImageUrl(res.data.imageUrl);
            },
            (error) => {
              console.error(`Couldn't get the image size: ${error.message}`);
            }
          );
        } else {
          throw { message: "Internal Server Error" };
        }
      })
      .catch((err) => console.log(err))
      .finally(() => setLoading(false));
  };

  if (loading) {
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

  if (imageUrl && imageScale) {
    return (
      <Image
        source={{
          uri: imageUrl,
        }}
        style={{ width: screen.width, height: screen.width / imageScale }}
      />
    );
  }
  return (
    <TouchableOpacity
      onPress={() => pickDocument()}
      style={{ justifyContent: "center", alignItems: "center" }}
    >
      <Feather name="plus" size={80} color="black" />
      <Text style={{ fontSize: 20, textAlign: "center" }}>
        ADD YOUR AMAZING IMAGE
      </Text>
    </TouchableOpacity>
  );
}

export default AddImage;
