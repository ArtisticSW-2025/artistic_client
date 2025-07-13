package com.snacks.nuvo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// 로그 출력에 사용
const val TAG: String = "NUVO"

// Hilt 의존성 주입을 위한 앱 설정
@HiltAndroidApp
class NuvoApp : Application()