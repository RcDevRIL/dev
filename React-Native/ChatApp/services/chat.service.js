import PouchDB from 'pouchdb-react-native';


const REMOTE_DB = 'http://gathor.org:5984/';

let currentUser = null;
let currentRoom = null;
let db = null;

function join(user, room, onSync, onFail) {
    if (/^_/.test(room)) {
        onFail('Le nom du salon ne peut pas commencer par "_"');
    }

    currentUser = user || 'Anonymous';
    currentroom = room.toLowerCase() || 'general';
    db = new PouchDB(currentroom);

    sync = db.sync(`${REMOTE_DB}/${currentroom}`, {
        live: true,
        retry: true,
        continuous: true
    });
    sync.on('change', handleChange(onSync));
    sync.on('error', handleError(onFail));
    return listMessages();
}

function listMessages() {
    return db.allDocs({
        include_docs: true,
    }).then(response => {
        return response.rows
            .map(row => row.doc)
            .sort((a, b) => a.created_at > b.created_at)
    });
}

function handleChange(callback) {
    return change => {
        console.log('change', change);
        listMessages()
            .then(messages => callback(messages))
            .catch(console.error);
    }
}

function handleError(callback) {
    return error => {
        callback(error);
    }
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

export const chatService = { join, sendMessage, listMessages }