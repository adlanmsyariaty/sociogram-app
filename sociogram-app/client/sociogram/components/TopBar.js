import React, { useState, useEffect, useContext } from 'react';
import { Text, View, Image, StatusBar, TouchableOpacity } from 'react-native'
import { Ionicons, AntDesign, Foundation  }  from '@expo/vector-icons';
import logo from '../assets/cool-profile-pic-matheus-ferrero.jpeg';
import AsyncStorage from '@react-native-async-storage/async-storage';
import AuthContext from '../context/AuthContext';
import { useNavigation } from '@react-navigation/native';

function TopBar() {
  const { setIsSignedIn, setAccessToken, screen } = useContext(AuthContext)
  const navigation = useNavigation()
  return (
    <View style={{
      paddingHorizontal: 10,
      paddingTop: StatusBar.currentHeight,
      paddingBottom: 10,
      backgroundColor: "white",
      borderBottomWidth: 1,
      borderBottomColor: '#e2e8f0' //after scroll border show
    }}>
      <View style={{ flexDirection: "row", alignItems: "center" }}>
        <View style={{flex: 1, justifyContent: screen == 'Home' ? "space-between" : "flex-start", flexDirection: "row", alignItems: "center"}}>
          {screen == 'Home' && <Text style={{ fontSize: 30, fontWeight: "bold", textAlign: "center", textAlignVertical: "center", color: '#9d174d', backgroundColor: 'white'}}>Sociogram</Text>}
          {screen != 'Home' && (
            <>
              <TouchableOpacity style={{ marginRight: 20 }} onPress={() => navigation.goBack()}>
                <Foundation name="arrow-left" size={24} color="black" />
              </TouchableOpacity>
              <Text style={{ fontSize: 24, fontWeight: "bold", textAlign: "center", textAlignVertical: "center", color: 'black', backgroundColor: 'white'}}>{screen == 'Comment' ? screen : 'New Post'}</Text>
            </>
          )}

          <View style={{justifyContent: "center"}}>
            <View style={{flexDirection: "row", justifyContent: "space-between", width: 140, alignItems: "center"}}>

              {screen == 'Home' &&
                <>
                  <View style={{ padding: 5, backgroundColor: "white" }}>
                    <View style={{ position: "absolute", right: 0, backgroundColor: "#ec4899", borderRadius: 18, alignItems: "center", justifyContent: "center", width: 18, height: 18, zIndex: 2, borderColor: 1, borderWidth: 2 }}>
                      <Text style={{ fontSize: 10, color: "white", fontWeight: "bold"}}>5</Text>
                    </View>
                    <Ionicons name="notifications-outline" size={24} color="black" />
                  </View>

                  <View style={{ padding: 5, backgroundColor: "white" }}>
                    <View style={{ position: "absolute", right: 0, backgroundColor: "#ec4899", borderRadius: 18, alignItems: "center", justifyContent: "center", width: 18, height: 18, zIndex: 2, borderColor: 1, borderWidth: 2 }}>
                      <Text style={{ fontSize: 10, color: "white", fontWeight: "bold"}}>2</Text>
                    </View>
                    <AntDesign name="message1" size={24} color="black" />
                  </View>

                  <Image source={logo} style={{ width: 32, height: 32, borderRadius: 40 }} />
                </>
              }

            </View>
          </View>
        </View>
      </View>
    </View>
  )
}

export default TopBar