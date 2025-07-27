package com.snacks.nuvo

import androidx.annotation.DrawableRes

// BottomNavBar Items 정의
enum class TopLevelDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val label: String,
    val route: String,
) {
    Home(
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
        label = "홈",
        route = Routes.Home.ROUTE
    ),
    Script(
        selectedIcon = R.drawable.ic_document_filled,
        unselectedIcon = R.drawable.ic_document_outlined,
        label = "스크립트",
        route = Routes.Script.ROUTE
    ),
    Ranking(
        selectedIcon = R.drawable.ic_flag_filled,
        unselectedIcon = R.drawable.ic_flag_outlined,
        label = "랭킹",
        route = Routes.Ranking.ROUTE
    ),
    Challenge(
        selectedIcon = R.drawable.ic_star_filled,
        unselectedIcon = R.drawable.ic_star_outlined,
        label = "챌린지",
        route = Routes.Challenge.ROUTE
    ),
    Profile(
        selectedIcon = R.drawable.ic_user_filled,
        unselectedIcon = R.drawable.ic_user_outlined,
        label = "프로필",
        route = Routes.Profile.ROUTE
    ),
}