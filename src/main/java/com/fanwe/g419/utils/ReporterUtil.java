package com.fanwe.g419.utils;

import com.fanwe.hybrid.app.App;
import com.fanwe.library.utils.SDDateUtil;
import com.fanwe.g419.common.CommonInterface;
import com.fanwe.g419.dao.UserModelDao;
import com.fanwe.g419.model.UserModel;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ReporterUtil
{
    public static void reportError(String msg)
    {
        UserModel user = UserModelDao.query();
        if (user != null)
        {
            msg = "(user:" + user.getUser_id() + ") " + msg;
        }

        String time = SDDateUtil.getNow_yyyyMMddHHmmss();
        msg = "Android " + time + " " + msg;

        MobclickAgent.reportError(App.getApplication(), msg);
        CommonInterface.reportErrorLog(msg);
    }

}
