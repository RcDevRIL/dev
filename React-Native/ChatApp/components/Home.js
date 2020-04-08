import React from 'react';
import { Text, View, StyleSheet, Button } from 'react-native';
import { Actions } from 'react-native-router-flux';

export class Home extends React.Component {
    render() {
        return (
            <View style={styles.container}>
                <Text style={[styles.h1, styles.textBlue]}>Welcome on Home Page !</Text>
                <Button style={[styles.h1, styles.textBlue]}
                    title="Go to Chat"
                    color="#0FF"
                    key="ChatButton"
                    accessibilityLabel="ChatButton"
                    onPress={() => { Actions.chat(); }}
                />
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