import React, { useContext, useEffect, useState } from "react";
import {
  ScrollView,
  Text,
  View,
  Image,
  TouchableOpacity,
  ActivityIndicator,
  FlatList,
} from "react-native";
import Content from "../components/Content";
import { AntDesign } from "@expo/vector-icons";
import { useFocusEffect, useNavigation } from "@react-navigation/native";
import AuthContext from "../context/AuthContext";

function Home() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const { setScreen } = useContext(AuthContext);
  const navigation = useNavigation();

  useFocusEffect(
    React.useCallback(() => {
      setScreen("Home");
      fetch("http://54.251.82.169/sociogram-app/posts", {
        method: "GET",
      })
        .then((res) => res.json())
        .then((res) => setPosts(res))
        .catch((err) => console.log(err))
        .finally(() => setLoading(false));
    }, [loading])
  );

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

  const renderItem = ({ item }) => <Content post={item} />;

  if (loading) {
    return (
      <View
        style={{
          flex: 1,
          justifyContent: "center",
          alignItems: "center",
          backgroundColor: "white",
        }}
      >
        <ActivityIndicator size="large" color="#be123c" />
      </View>
    );
  }
  return (
    <>
      {/* add comment bar each of post */}
      {posts.data && posts.data.length > 0 && (
        <FlatList
          style={{ backgroundColor: "white" }}
          data={posts.data}
          renderItem={renderItem}
          keyExtractor={(item) => item._id}
          showsVerticalScrollIndicator={false}
        />
      )}
      <View
        style={{
          position: "absolute",
          zIndex: 2,
          bottom: 50,
          width: "100%",
          alignItems: "center",
          elevation: 20,
          shadowColor: "#000",
          shadowOffset: { width: 0, height: 4 },
          shadowOpacity: 0.8,
          shadowRadius: 40,
        }}
      >
        {/* <TouchableOpacity onPress={() => navigation.navigate("Create")}>
          <View
            style={{
              backgroundColor: "#9f1239",
              padding: 15,
              borderRadius: 30,
            }}
          >
            <AntDesign name="plus" size={30} color="white" />
          </View>
        </TouchableOpacity> */}
      </View>
    </>
  );
}

export default Home;
