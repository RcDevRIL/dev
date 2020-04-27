const initState = {
    user: "Romain",
    room: "Général",
    messages: []
}

export const chat = function (state = initState, action) {

    switch (action.type) {
        case 'JOIN_REQUEST':
            return {
                ...state,
                user: action.user,
                room: action.room,
                error: null
            };

        case 'JOIN_SUCCESS':
            return {
                ...state,
                messages: action.messages,
                error: null
            };
        case 'FAILURE':
        case 'SYNC_FAILURE':
            return {
                ...state,
                error: action.error
            };
        case 'SEND_MESSAGE_SUCCESS':
            return {
                ...state,
                messages: [
                    ...state.messages,
                    action.message,
                ],
            }
        case 'SYNC_SUCCESS':
            return {
                ...state,
                messages: action.messages
            }
        default:
            return state;
    }

    return state;
}