import React from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import Home from "../screens/Home";
import Ionicons from "react-native-vector-icons/Ionicons";
import Create from "../screens/Create";
import SignOut from "../screens/SignOut";

const Tab = createBottomTabNavigator();

function TabNav() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ focused, color, size }) => {
          let iconName;

          if (route.name === "Home") {
            iconName = focused ? "ios-home-sharp" : "ios-home-outline";
          } else if (route.name === "Create") {
            iconName = focused ? "add-circle" : "add-circle-outline";
          } else if (route.name === "Sign Out") {
            iconName = focused ? "log-out" : "log-out-outline";
          }

          // You can return any component that you like here!
          return <Ionicons name={iconName} size={30} color={color} />;
        },
        tabBarHideOnKeyboard: true,
        tabBarShowLabel: false,
        tabBarStyle: {
          backgroundColor: "#9f1239",
          borderTopWidth: 0,
          paddingHorizontal: 5,
          height: 60,
          paddingTop: 10,
          paddingBottom: 10,
        },
        tabBarActiveTintColor: "white",
        tabBarInactiveTintColor: "white",
      })}
    >
      <Tab.Screen
        name="Home"
        component={Home}
        options={{ headerShown: false }}
      />
      <Tab.Screen
        name="Create"
        component={Create}
        options={{ headerShown: false }}
      />
      <Tab.Screen
        name="Sign Out"
        component={SignOut}
        options={{ headerShown: false }}
      />
    </Tab.Navigator>
  );
}

export default TabNav;
