package com.ych.hidebugtools

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Handler
import android.os.Process
import android.widget.Toast
import com.ych.hidebugtools.utils.AppGlobals

/**
 * TODO：创建DebugTools工具类
 *   1.该类提供我们工具需要的一些功能方法。
 *   2.最终通过反射来获取该类中的所有方法，并执行方法内的方法体。
 *     注意：如果方法没有返回值，那么我们可以通过注解的方式来实现Tools上显示方法名称
 */
class DebugTools {
    /**
     * TODO：显示项目的构建版本号
     *   1.0.1
     */
    fun buildVersion():String{
        return "构建版本：${BuildConfig.VERSION_CODE}.${BuildConfig.VERSION_NAME}"
    }

    /**
     * TODO：显示项目的构建时间
     *  星期二 22:14:54
     */
    fun buildTime():String{
        //new date() 当我们在运行时拿到的时间。也就是这个包打出来的时间
        return "构建时间：${BuildConfig.BUILD_TIME}"
    }

    /**
     * TODO：显示项目的构建环境
     *   通过BuildConfig.Debug
     */
    fun buildEnvironment():String{
        return "构建环境：${if (BuildConfig.DEBUG) "测试环境" else "正式环境"}"
    }


    /**
     * TODO：降级Https为Http
     *   此时是需要具体的实现，并非是一个String类型的返回值，所以我们此时通过HiDebug注解来实现Tools上显示的名称。
     */
    @HiDebug(name = "一键开启Https降级",desc = "将继承Http,可以使用抓包工具明文抓包")
    fun degradeHttp(){
        //此时我们可以通过MMKV（https://github.com/Tencent/MMKV）来存储我们的baseUrl,将其修改为http的。如果需要重启应用生效，那么我们此时直接重启应用
        //...

        val content = AppGlobals.get()?.applicationContext?:return
        //获取需要启动应用的Intent的意图
        val intent = content.packageManager.getLaunchIntentForPackage(content.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        content.startActivity(intent)
        //杀死当前进程，并主动启动新的启动页，完成重启动作。
        Process.killProcess(Process.myPid())
    }

}