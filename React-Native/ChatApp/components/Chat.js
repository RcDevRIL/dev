import React from 'react';
import { View, StyleSheet } from 'react-native';
import { MessageItem } from './MessageItem';
import { FlatList } from 'react-native-gesture-handler';

const messages = [
    {
        content: "Message 1",
        author: "Robert",
        created_at: new Date()
    },
    {
        content: "Un message un peu trop long mais c'est juste pour voir comment ça rend",
        author: "Amelie",
        created_at: new Date()
    },
    {
        content: "Message 3",
        author: "Robert",
        created_at: new Date()
    },
];


export class Chat extends React.Component {

    getData() {
        return messages.map((message, i) => ({ 
            ...message, key: `message_${i}`
        }));
    }

    render() {
        const { user } = this.props;
        
        return (
            <View style={styles.container}>
                <FlatList
                    data={this.getData()}
                    renderItem={({ item: message }) =>
                        <MessageItem user={user} message={message} /> 
                    }
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#eee',
        justifyContent: 'center',
    }
});
