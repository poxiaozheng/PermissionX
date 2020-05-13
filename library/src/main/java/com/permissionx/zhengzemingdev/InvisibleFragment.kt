package com.permissionx.zhengzemingdev

import android.content.pm.PackageManager
import android.support.v4.app.Fragment

/**
 *@author:zoom
 *@date:2020/5/12
 */

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {
    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, permissions: Array<out String>) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            val deniedList = grantResults
                .zip(permissions)
                .filter { (status, _) -> status != PackageManager.PERMISSION_GRANTED }
                .map { (_, permissionName) -> permissionName }

            callback?.invoke(deniedList.isEmpty(), deniedList)
        }
    }
}