import React from 'react';
import { connect } from 'react-redux';
import { Text, View, StyleSheet, FlatList, TextInput, Button } from 'react-native';
import { MessageItem } from './MessageItem';
import { chatActions } from '../actions';

@connect(({ chat:
    { user,
        room,
        messages,
        error }
}) => ({ user, room, messages, error }))
export class Chat extends React.Component {

    state = {
        content: ''
    }

    listRef = React.createRef();

    getData() {
        const { messages } = this.props;
        return messages.map((message, i) => ({
            ...message, key: `message_${i}`
        }));
    }
    handleChangeText = content => {
        this.setState({ content });
    }
    handleSendMsg = e => {
        const { dispatch } = this.props;
        const { content } = this.state;
        if (content != '') {
            dispatch(chatActions.sendMessage({ content }));
            this.setState({ content: '' });
        }
    }

    render() {
        const { user, error } = this.props;
        const { content } = this.state;
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
                    ref={ref => this.listRef = ref}
                    onContentSizeChange={() => this.listRef.scrollToEnd({ animated: true })}
                    onLayout={() => this.listRef.scrollToEnd({ animated: true })}
                />

                <View style={styles.composerContainer}>
                    <TextInput
                        value={content}
                        style={styles.composerInput}
                        onChangeText={this.handleChangeText}
                        placeholder="Saisir un message"
                    />

                    <Button
                        title="Envoyer !"
                        onPress={this.handleSendMsg}
                        disabled={content === ''}
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