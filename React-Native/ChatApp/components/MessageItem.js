import React from 'react';
import { Text, View, StyleSheet } from 'react-native';
import moment from 'moment';

export class MessageItem extends React.Component {
    render() {
        const { user, message } = this.props;
        const { id, content, author, created_at } = message;

        const isAuthor = user == author;

        return (
            <View style={[styles.root, {
                alignSelf: isAuthor ? 'flex-end' : 'flex-start',
                backgroundColor: isAuthor ? 'darkcyan' : 'lightgrey',
                borderBottomLeftRadius: isAuthor ? 16 : 0,
                borderBottomRightRadius: isAuthor ? 0 : 16,
            }]}>
                <Text style={[styles.content, { color: isAuthor ? 'white' : 'black' }]}>{content}</Text>
                <Text style={[styles.timestamp, {
                    color: isAuthor ? '#DDD' : '#555'
                }]}>
                    {moment(created_at).fromNow()}
                </Text>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    root: {
        padding: 8,
        backgroundColor: '#CCC',
        borderRadius: 16,
        margin: 8,
        alignSelf: 'flex-start',
        maxWidth: '70%'
    },
    content: {
        marginBottom: 4
    },
    timestamp: {
        fontStyle: 'italic',
        fontSize: 10,
        textAlign: 'right'
    }
});