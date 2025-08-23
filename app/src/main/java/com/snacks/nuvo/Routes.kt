package com.snacks.nuvo

// 화면 별 라우트 정의
object Routes {
    object Home {
        const val ROUTE = "homeRoute"

    }

    object Script {
        const val ROUTE = "scriptRoute"
    }

    object Ranking {
        const val ROUTE = "rankingRoute"
    }

    object Challenge {
        const val ROUTE = "challengeRoute"
    }

    object Profile {
        const val ROUTE = "profileRoute"
    }

    object Call {
        const val ROUTE = "call"

        const val CALLING = "calling"
        const val ON_CALL = "on_call"
        const val RESULT = "result"
    }
}