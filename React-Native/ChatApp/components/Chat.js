import React from 'react';
import { connect } from 'react-redux';
import { Text, View, StyleSheet, FlatList, TextInput, Button } from 'react-native';
import { MessageItem } from './MessageItem';

@connect(({ chat:
    { user,
        room,
        messages }
}) => ({ user, room, messages }))
export class Chat extends React.Component {

    getData() {
        const { messages, error } = this.props;
        return messages.map((message, i) => ({
            ...message, key: `message_${i}`
        }));
    }

    render() {
        const { user, room } = this.props;

        return (
            <View style={styles.container}>
                {error &&
                    <Text>Error: {error.message}</Text>
                }
                <FlatList style={styles.list}
                    data={this.getData()}
                    renderItem={({ item: message }) =>
                        <MessageItem user={user} message={message} />
                    }
                />

                <View style={styles.composerContainer}>
                    <TextInput

                        style={styles.composerInput}
                        placeholder="Saisir un message"
                    />

                    <Button
                        title="Envoyer !"
                    />
                </View>

            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
    },
    list: {
        flex: 1
    },
    composerContainer: {
        flex: 0,
        flexDirection: 'row',


    },
    composerInput: {
        flex: 1,
        backgroundColor: 'white',
        paddingLeft: 8,
        paddingRight: 8
    }
});