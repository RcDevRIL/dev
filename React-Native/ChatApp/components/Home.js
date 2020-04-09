import React from 'react';
import { connect } from 'react-redux';
import { Text, View, StyleSheet, Button, TextInput } from 'react-native';
import { Actions } from 'react-native-router-flux';
import { chatActions } from '../actions';

@connect(() => ({}))
export class Home extends React.Component {

    state = {
        user: '',
        room: ''
    }

    handleUserChange = user => {
        this.setState({ user });
    }

    handleRoomChange = room => {
        this.setState({ room });
    }

    handleChatPress = e => {
        const { dispatch } = this.props;
        const { user, room } = this.state;
        dispatch(chatActions.join(user, room));
        Actions.chat({ title: `Salon "${room}"` });
    }

    render() {
        const { user } = this.state;
        const { room } = this.state;
        return (
            <View style={styles.container}>
                <Text style={styles.h1}>Bienvenue !</Text>
                <Text style={styles.label}>Nom d'utilisateur</Text>
                <TextInput
                    value={user}
                    onChangeText={this.handleUserChange}
                    style={styles.input}
                    placeholder="Choississez un pseudo"
                />
                <Text style={styles.label}>Salon</Text>
                <TextInput
                    value={room}
                    onChangeText={this.handleRoomChange}
                    style={styles.input}
                    placeholder="Choississez un salon de chat"
                />
                <Button
                    title="Let's chat !"
                    onPress={this.handleChatPress}
                />
            </View>

        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#CCC',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 8
    },
    h1: {
        fontSize: 20
    },
    label: {
        alignSelf: 'flex-start',
        marginTop: 16,
        textAlign: 'left'
    },
    big: {
        fontSize: 20,
        color: 'blue'
    },
    input: {
        backgroundColor: "white",
        borderStyle: "solid",
        borderWidth: 1,
        borderColor: "black",
        borderRadius: 8,
        margin: 8,
        padding: 4,
        width: '100%'
    },
});