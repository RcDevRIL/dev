import { chatService } from '../services';

export const chatActions = {
    join,
    sendMessage
}

function join(user, room) {
    return dispatch => {
        dispatch(request(user, room));
        chatService.join(user, room)
            .then(messages => dispatch(success(messages)))
            .catch(e => { dispatch(error(e)) });
    };
}
function request(user, room) { return { type: 'JOIN_REQUEST', user, room }; }
function success(messages) { return { type: 'JOIN_SUCCESS', messages }; }
function error(e) { return { type: 'FAILURE', e }; }

function sendMessage(message) {
    return dispatch => {
        dispatch(requestSend(message));
        chatService.sendMessage(message)
            .then(message => dispatch(successSend(message)))
            .catch(e => { dispatch(error(e)) });
    };
}
function requestSend(message) { return { type: 'SEND_MESSAGE_REQUEST', message }; }
function successSend(message) { return { type: 'SEND_MESSAGE_SUCCESS', message }; }