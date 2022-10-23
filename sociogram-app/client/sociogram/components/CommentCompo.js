import React from "react";
import { Text, View, Image } from "react-native";
import logo from "../assets/cool-profile-pic-matheus-ferrero.jpeg";
import moment from "moment"

function CommentCompo(props) {
  return (
    <View style={{ flexDirection: "row", paddingTop: 10, paddingBottom: 10 }}>
      <View style={{ flex: 1, paddingHorizontal: 15 }}>
        <Image
          source={logo}
          style={{ width: 35, height: 35, borderRadius: 40, marginRight: 15 }}
        />
      </View>
      <View style={{ flex: 9, paddingRight: 15 }}>
        <Text>
          <Text style={{ fontWeight: "bold" }}>{props.userId} </Text>
          <Text style={{ lineHeight: 20 }}>
            {props.message}
          </Text>
        </Text>
        <Text style={{ color: "gray", fontWeight: "bold", marginTop: 5 }}>
          {moment(props.createdAt).format('MMMM Do YYYY, h:mm:ss a')}
        </Text>
      </View>
    </View>
  );
}

export default CommentCompo;
