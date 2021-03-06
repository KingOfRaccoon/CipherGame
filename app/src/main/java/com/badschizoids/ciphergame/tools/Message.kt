package com.badschizoids.ciphergame.tools

import com.example.chat.MemberData

class Message(// message body
        val text: String, // data of the user that sent this message
        var memberData: MemberData,
        var belongsToCurrentUser: Boolean,// is this message sent by us?
        var needWork: Boolean = false
) {
    constructor(message: StoryMessage, memberData: MemberData, belongsToCurrentUser: Boolean)
            :this(message.message, memberData, belongsToCurrentUser, message.needWork)
}