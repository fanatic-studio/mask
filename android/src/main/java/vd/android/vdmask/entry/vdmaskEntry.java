package vd.android.vdmask.entry;

import android.content.Context;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import app.vd.framework.extend.annotation.ModuleEntry;
import vd.android.vdmask.component.VDMask;
import vd.android.vdmask.component.VDPop;

@ModuleEntry
public class vdmaskEntry {

    /**
     * APP启动会运行此函数方法
     * @param content Application
     */
    public void init(Context content) {

        //1、注册weex模块
        try {
            WXSDKEngine.registerComponent("vdmask", VDMask.class);
            WXSDKEngine.registerComponent("vdpop", VDPop.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }

}
