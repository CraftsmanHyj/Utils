package com.hyj.lib.utils.demo.permission;

import android.Manifest;

import com.hyj.lib.permission.bean.IPermissionInfo;
import com.hyj.lib.permission.bean.PermConstant;

/**
 * <pre>
 * </pre>
 * Author：hyj
 * Date：2020/3/4 23:03
 */
public enum PermEnum implements IPermissionInfo {
    CAMER(Manifest.permission.CAMERA),
    LOCATION_CONTACTS(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS);

    private int code;
    private String[] perms;

    PermEnum(String... perms) {
        this.code = PermConstant.getReqeustCode();
        this.perms = perms;
    }

    @Override
    public int getRequestCode() {
        return code;
    }

    @Override
    public String[] getPermissions() {
        return perms;
    }
}