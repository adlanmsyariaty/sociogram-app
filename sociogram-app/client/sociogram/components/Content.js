import React, { useState } from "react";
import { Text, View, Image, TouchableOpacity } from "react-native";
import logo from "../assets/cool-profile-pic-matheus-ferrero.jpeg";
import { Dimensions } from 'react-native';
import { useFocusEffect, useNavigation } from '@react-navigation/native';
import moment from 'moment'

const window = Dimensions.get('window');
const screen = Dimensions.get('screen');

function Content({ post }) {
  const [dimensions, setDimensions] = useState({ window, screen });
  const [imageScale, setImageScale] = useState(0)
  const navigation = useNavigation()

  Image.getSize(post.imageUrl, (width, height) => {
    setImageScale(width/height)
  }, (error) => {
    console.error(`Couldn't get the image size: ${error.message}`);
  });

  useFocusEffect(
    React.useCallback(() => {
      const subscription = Dimensions.addEventListener('change', ({ window, screen }) => {
        setDimensions({ window, screen });
      });
      return () => subscription?.remove();
    }, [])
  );
  return (
    <View style={{ marginBottom: 20 }}>
      <View style={{ flexDirection: "row", padding: 10 }}>
        <Image
          source={logo}
          style={{ width: 20, height: 20, borderRadius: 40, marginRight: 10 }}
        />
        <Text style={{ fontWeight: "bold" }}>{post.userId.username}</Text>
      </View>
      {imageScale > 0 && (
        <Image
          source={{
            uri: post.imageUrl,
          }}
          style={{
            width: dimensions.screen.width,
            height: dimensions.screen.width / imageScale,
          }}
        />
      )}
      <View style={{ flexDirection: "row", paddingHorizontal: 10, paddingTop: 5 }}>
        <Text>
          <Text style={{ fontWeight: "bold" }}>{post.userId.username} </Text>
          <Text style={{ lineHeight: 20 }}>
            {post.caption}{" "}
          </Text>
        </Text>
      </View>
      <View style={{ flexDirection: "row", paddingHorizontal: 10, paddingTop: 5 }}>
        <TouchableOpacity onPress={() => navigation.navigate('Comment', { postId: String(post._id), comments: post.comments, caption: post.caption, username: post.userId.username })}>
          <Text style={{ color: 'gray' }}>View All {post.comments.length} Comments</Text>
        </TouchableOpacity>
      </View>
      <View style={{ flexDirection: "row", paddingHorizontal: 10, paddingTop: 5 }}>
        <Text style={{ color: 'gray,', fontSize: 9 }}>{moment(post.createdAt).format('MMMM Do YYYY, h:mm:ss a')}</Text>
      </View>
    </View>
  );
}

export default Content;
