import React, { useContext, useState } from "react";
import {
  ScrollView,
  Text,
  View,
  Image,
  TextInput,
  TouchableOpacity,
} from "react-native";
import CommentCompo from "../components/CommentCompo";
import logo from "../assets/cool-profile-pic-matheus-ferrero.jpeg";
import AuthContext from "../context/AuthContext";
import { useFocusEffect } from "@react-navigation/native";

function Comment({ route }) {
  const [comment, setComment] = useState("");
  const [comments, setComments] = useState(route.params.comments);
  const [caption, setCaption] = useState(route.params.caption)
  const { accessToken, setScreen } = useContext(AuthContext);

  const commentInput = (text) => {
    setComment(text);
  };

  useFocusEffect(
    React.useCallback(() => {
      setScreen('Comment')
    }, [])
  );

  const submitComment = async () => {
    try {
      let data = {
        postId: route.params.postId,
        message: comment,
      };
      const response = await fetch("http://54.255.134.36:3001/comments/add", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          access_token: accessToken,
        },
      });
      const result = await response.json();
      let newComment = { ...result['data']._doc, userId: result['data'].userId  }
      setComments((old) => [...old, newComment]);
      setComment("");
    } catch (error) {
      alert(error);
    }
  };
  return (
    <>
      <ScrollView style={{ backgroundColor: "white", marginBottom: 60 }}>
        <View style={{ borderBottomWidth: 0.5 }}>
          <CommentCompo message={caption} userId={route.params.username}/>
        </View>
        {comments &&
          comments.length > 0 &&
          comments.map((el) => (
            <CommentCompo
              message={el.message}
              userId={el.userId.username}
              key={el._id}
            />
          ))}
      </ScrollView>
      <View
        style={{
          flex: 1,
          flexDirection: "row",
          position: "absolute",
          zIndex: 2,
          bottom: 0,
          backgroundColor: "#ffe4e6",
          width: "100%",
          padding: 10,
        }}
      >
        <View style={{ justifyContent: "center" }}>
          <Image
            source={logo}
            style={{ width: 40, height: 40, borderRadius: 40, marginRight: 15 }}
          />
        </View>
        <View
          style={{
            flex: 1,
            flexDirection: "row",
            alignItems: "center",
            justifyContent: "space-between",
            width: "100%",
          }}
        >
          <View style={{ flex: 8, justifyContent: "center" }}>
            <TextInput
              multiline
              placeholder="Add a comment..."
              style={{ paddingRight: 15 }}
              value={comment}
              onChangeText={(text) => commentInput(text)}
            />
          </View>
          <View style={{ flex: 1 }}>
            <TouchableOpacity onPress={() => submitComment()}>
              <Text style={{ fontSize: 16, fontWeight: "bold" }}>Post</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </>
  );
}

export default Comment;
