package com.permissionx.zhengzemingdev

import android.support.v4.app.FragmentActivity

/**
 *@author:zoom
 *@date:2020/5/12
 */
object PermissionX {
    @JvmStatic
    val TAG = "InvisibleFragment"

    @JvmStatic
    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        request(activity, permissions.toList(), callback)
    }

    @JvmStatic
    fun request(
        activity: FragmentActivity,
        permissions: List<String>,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, permissions.toTypedArray())
    }
}