/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rayhahah.easyworld.bridge.callback

import androidx.lifecycle.ViewModel
import com.rayhahah.easyworld.architecture.binding.UnPeekLiveData
import java.util.*

/**
 * callbackViewModel 的职责仅限于 页面通信，不建议在此处理 UI 逻辑，
 * UI 逻辑只适合在 Activity/Fragment 等视图控制器中完成，是 “数据驱动” 的一部分，
 * 将来升级到 Jetpack Compose 更是如此。
 */
class SharedViewModel : ViewModel() {
    val openOrCloseDrawer = UnPeekLiveData<Boolean>()
    val exceptionData = UnPeekLiveData<Exception>()
    val navMainData = UnPeekLiveData<Int>()

    companion object {
        // TODO tip 1：此处演示通过 UnPeekLiveData 配合 SharedViewModel 来发送 生命周期安全的、事件源可追溯的 通知。
        // 并且，使用 Application 级的 SharedViewModel，而不是单例，来负责 全局的页面消息通信，是为了 约束作用域，
        // 以免视图控制器间的消息 污染到 视图控制器之外的领域。
        val TAG_OF_SECONDARY_PAGES: List<String> = ArrayList()
    }

}
