import {chatService} from '../services';

export const chatActions = {
    join
}

function join(user, room) {
    return { type: 'JOIN_REQUEST', user, room};
}