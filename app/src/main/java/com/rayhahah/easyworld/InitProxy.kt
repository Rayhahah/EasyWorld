package com.rayhahah.easyworld

import android.app.Application
import com.rayhahah.libbase.ProjectConst
import com.rayhahah.libbase.net.OkHttpManager
import com.rayhahah.libbase.net.RetrofitFactory
import me.jessyan.progressmanager.ProgressManager

/**
 *
 * @author rayhahah
 * @blog http://rayhahah.com
 * @time 2019/3/12
 * @tips 这个类是Object的子类
 * @fuction
 */
object InitProxy {

    /**
     * 需要在Application里面初始化
     */
    fun onApplicationInit(app: Application) {
        initHttpClient()
        //开启轮询任务
//        ScheduleTaskManager.getInstance().startAllTasks()

//        Gloading.debug(ProjectConst.DEBUG)
//        Gloading.initDefault(ContentLoadingAdapter())
    }

    private fun initHttpClient() {
        val progressClient =
            ProgressManager.getInstance().with(OkHttpManager.getInstance().create()).build()
        RetrofitFactory.getInstance().create(ProjectConst.HOST, progressClient)

//        // Glide 下载监听
//        ProgressManager.getInstance().addResponseListener(IMAGE_URL, getGlideListener());
//        // Okhttp/Retofit 下载监听
//        ProgressManager.getInstance().addResponseListener(DOWNLOAD_URL, getDownloadListener());
//        // Okhttp/Retofit 上传监听
//        ProgressManager.getInstance().addRequestListener(UPLOAD_URL, getUploadListener());
    }

    /**
     * 可以在Home界面再初始化的
     */
    fun onHomeInit() {

    }
}