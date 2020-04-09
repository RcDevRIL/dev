import React from 'react';
import { Text, View, StyleSheet, Button } from 'react-native';
import { Actions } from 'react-native-router-flux';
import { TextInput } from 'react-native-gesture-handler';
export class Home extends React.Component {

    state = {
        user: '',
        room: '',
    }

    handleUserChange = user => {
        this.setState({ user });
    }

    handleRoomChange = room => {
        this.setState({ room });
    }

    handleChatPress = e => {
        const { user, room } = this.state;
        Actions.chat({ user: user, title: `Salon "${room}"` });
    }

    render() {
        const { user, room } = this.state;
        return (
            <View style={styles.container}>
                <Text >Welcome !</Text>
                <TextInput
                    value={user}
                    placeholder="Nom d'utilisateur"
                    style={styles.input}
                    onChangeText={this.handleUserChange}
                    ref={input => { this.textInput = input }}
                />
                <Text >Room name: </Text>
                <TextInput
                    value={room}
                    placeholder="Room name"
                    style={styles.input}
                    onChangeText={this.handleRoomChange}
                    ref={input => { this.textInput = input }}
                />
                <Button
                    title="Let's chat!"
                    color='teal'
                    onPress={this.handleChatPress}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#ddd',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 16
    },
    h1: {
        fontSize: 16
    },
    textBlue: {
        color: 'blue'
    },
    input: {
        backgroundColor: 'white',
        borderStyle: 'solid',
        borderWidth: 1,
        borderColor: 'black',
        margin: 8,
        width: '100%',
        padding: 10
    }
});
