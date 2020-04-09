import PouchDB from 'pouchdb-react-native';



let currentUser = null;
let currentRoom = null;
let db = null;

function join(user, room) {
    currentUser = user || 'Anonymous';
    currentRoom = room || 'général';
    db = new PouchDB(room);
    return db.allDocs({
        include_docs: true,
    }).then(response => response.rows.map(row => row.doc)
        .sort((a, b) => a.created_at > b.created_at));
}
function sendMessage(message) {
    message = {
        ...message,
        author: currentUser,
        created_at: new Date()
    };
    return db.post(message).then(({ id }) => ({
        ...message,
        id
    }));
}


export const chatService = { join, sendMessage }