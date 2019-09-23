package com.wwhy.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertyUtil {

    private static Properties property = null;

    public static String getProperty(String key) {
        if (property == null) {
            initProperty();
        }
        return property.getProperty(key);
    }

    private static void initProperty() {
        String path = PropertyUtil.class.getClassLoader().getResource("").getPath() + "application.properties";
        try {
            path = URLDecoder.decode(path, "utf-8");//处理路径中还有空格问题
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            Properties p = new Properties();
            p.load(in);
            property = p;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
