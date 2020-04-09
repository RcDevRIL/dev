const initState = {
    user: "Robert",
    room: null,
    messages: [
        {
            content: 'Message nul 1',
            author: 'Robert',
            created_at: new Date()
        }, {
            content: 'Message trop long pour tenir sur une ligne, enfin, sauf si t\'as un écran de ouf',
            author: 'Amelie',
            created_at: new Date()
        }, {
            content: 'Message nul 2',
            author: 'Robert',
            created_at: new Date()
        }
    ]
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
        default:
            return state;
    }

    return state;
}