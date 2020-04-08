import React from 'react';
import { Text, View, StyleSheet } from 'react-native';

export class Chat extends React.Component {
    render() {
        const { user, room } = this.props;
        return (
            <View style={styles.container}>
                <Text style={[styles.h1, styles.textBlue]}>Welcome on {room} Room, {user}!</Text>
            </View>
        )
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    h1: { fontSize: 16 },
    textBlue: { color: 'blue' }
});