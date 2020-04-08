import React from 'react';
import { Text, View, StyleSheet, Button, TextInput } from 'react-native';
import { Actions } from 'react-native-router-flux';

export class Home extends React.Component {

    state = {
        user: '',
        room: ''
    }


    render() {
        const { user, room } = this.state
        return (
            <View style={styles.container}>
                <Text style={[styles.h1, styles.textBlue]}>Welcome on Home Page !</Text>
                <TextInput
                    style={styles.input}
                    value={user}
                    placeholder="Nom d'utilisateur"
                    onChangeText={(user) => this.setState({ user })}
                />
                <TextInput
                    style={styles.input}
                    value={room}
                    placeholder="Room"
                    onChangeText={(room) => this.setState({ room })}
                />
                <Button
                    title="Go to Chat"
                    color="#c18"
                    key="ChatButton"
                    accessibilityLabel="Chat Button"
                    onPress={() => Actions.chat({ user, room, title: "Chat: " + room })}
                />
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#ccc',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 8
    },
    h1: { fontSize: 16 },
    textBlue: { color: 'blue' },
    input: {
        borderStyle: "solid",
        borderWidth: 1,
        borderColor: "black",
        backgroundColor: "white",
        padding: 4,
        margin: 8,
    }
});