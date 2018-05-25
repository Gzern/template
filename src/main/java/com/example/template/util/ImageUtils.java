package com.example.template.util;


import org.apache.commons.lang3.StringUtils;

public class ImageUtils {

    /**
     * 判断是否是图片类型
     *
     * @param fileName
     * @return
     */
    public static boolean isImageFile(String fileName) {
        if (StringUtils.isBlank(fileName)) return false;
        String[] img_type = {".jpg", ".jepg", ".png", ".gif", ".bmp"};
        fileName = fileName.toLowerCase();
        for (String s : img_type) {
            if (fileName.endsWith(s)) return true;
        }
        return false;
    }


    /**
     * 获取文件名后缀
     */
    public static String getFileType(String fileName) {
        if (StringUtils.isNotBlank(fileName) && fileName.indexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }
}
