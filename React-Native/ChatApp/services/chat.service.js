import PouchDB from 'pouchdb-react-native';

class Chat {

    user = null;
    room = null;
    db = null;

    async join(user, room) {
        this.user = user || 'Anonymous';
        this.room = room || 'général';
        try {
            this.db = new PouchDB(this.room);
            const response = await this.db.allDocs({
                include_docs: true,
            });

            return response.rows.map(row => row.doc);
        } catch (e) {
            console.error(e);
        }
    }
}

export const chatService = new Chat();