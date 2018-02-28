package com.myuan.web.utils;
/*
 * @author liuwei
 * @date 2018/2/27 10:59
 * 转换工具类
 */

import com.google.common.collect.Maps;
import java.util.Map;

public class SwitchUtil {

    private static Map<String, String> columnMap = null;


    public static final String switchColumn(String column) {
        if (columnMap == null) {
            columnMap = Maps.newHashMap();
            columnMap.put("all", "全部");
            columnMap.put("quiz", "提问");
            columnMap.put("share", "分享");
            columnMap.put("discuss", "讨论");
            columnMap.put("suggest", "建议");
            columnMap.put("notice", "公告");
        }
        return columnMap.get(column);
    }
}
