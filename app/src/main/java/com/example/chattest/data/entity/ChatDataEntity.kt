package com.example.chattest.data.entity

import com.example.chattest.data.constant.TestConst

data class ChatDataEntity(
    val roomId: Int,
    val chatInfoList: List<ChatInfoEntity>? = null
) {
    companion object {
        fun test1(): ChatDataEntity = ChatDataEntity(
            roomId = 1,
            chatInfoList = listOf(
                ChatInfoEntity(
                    messageFrom = 2222,
                    messageTo = TestConst.USER_ID,
                    text = "Lorem ipsum",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 2222,
                    text = "dolor sit",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 2222,
                    messageTo = TestConst.USER_ID,
                    text = " amet consectetur adipiscing elit",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 2222,
                    text = "sed do eiusmod tempor incididunt",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 2222,
                    messageTo = TestConst.USER_ID,
                    text = " ut labore et dolore magna aliqua.",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 2222,
                    text = "Ut enim ad minim veniam",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 2222,
                    messageTo = TestConst.USER_ID,
                    text = "quis nostrud exercitation ullamco",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 2222,
                    text = "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 2222,
                    messageTo = TestConst.USER_ID,
                    text = "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 2222,
                    messageTo = TestConst.USER_ID,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    createdDate = "",
                    unRead = false
                )
            )
        )

        fun test2(): ChatDataEntity = ChatDataEntity(
            roomId = 2,
            chatInfoList = listOf(
                ChatInfoEntity(
                    messageFrom = 3333,
                    messageTo = TestConst.USER_ID,
                    text = "Lorem ipsum",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 3333,
                    text = "dolor sit",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 3333,
                    messageTo = TestConst.USER_ID,
                    text = " amet consectetur adipiscing elit",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 3333,
                    text = "sed do eiusmod tempor incididunt",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 3333,
                    messageTo = TestConst.USER_ID,
                    text = " ut labore et dolore magna aliqua.",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = TestConst.USER_ID,
                    messageTo = 3333,
                    text = "Ut enim ad minim veniam",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 3333,
                    messageTo = TestConst.USER_ID,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    createdDate = "",
                    unRead = false
                )
            )
        )

        fun test3(): ChatDataEntity = ChatDataEntity(
            roomId = 3,
            chatInfoList = listOf(
                ChatInfoEntity(
                    messageFrom = 4444,
                    messageTo = TestConst.USER_ID,
                    text = "Lorem ipsum",
                    createdDate = "",
                    unRead = false
                ),

                ChatInfoEntity(
                    messageFrom = 4444,
                    messageTo = TestConst.USER_ID,
                    text = "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    createdDate = "",
                    unRead = false
                ),
                ChatInfoEntity(
                    messageFrom = 4444,
                    messageTo = TestConst.USER_ID,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    createdDate = "",
                    unRead = false
                )
            )
        )
    }

}
