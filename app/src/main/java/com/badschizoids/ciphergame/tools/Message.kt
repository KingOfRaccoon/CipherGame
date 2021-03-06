package com.badschizoids.ciphergame.tools

import com.example.chat.MemberData

class Message(// message body
        val text: String, // data of the user that sent this message
        var memberData: MemberData,
        var belongsToCurrentUser: Boolean,// is this message sent by us?
        var needWork: Boolean = false,
        val level: Level = Level.NO
) {
        constructor(storyMessage: StoryMessage, memberData: MemberData,
                    belongsToCurrentUser: Boolean)
                :this(storyMessage.message, memberData, belongsToCurrentUser,
                storyMessage.needWork, storyMessage.level)
}