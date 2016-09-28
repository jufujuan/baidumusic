package com.jfjmusic.dllo.baidumusic.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dllo on 16/9/28.
 * 网络音乐下载工具
 */
public class HttpDownloader {
    private URL url = null;

    /**
     * 下载文本文件
     *
     * @param urlStr
     * @return
     */
    public String download(String urlStr) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new InputStreamReader(
                    getInputStreamFromUrl(urlStr)));
            //一行一行的读取
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * @param urlStr
     *            文件所在的网络地址
     * @param path
     *            存储的目录
     * @param fileName
     *            存放的文件名
     * @return 状态
     */
    public String download(String urlStr, String path, String fileName) {
        InputStream inputStream = null;
        try {
            FileUtil fileUtils = new FileUtil();
            //判断文件是否已存在
            if (fileUtils.isFileExist(path + fileName)) {
                return "fileExist";
            } else {
                inputStream = getInputStreamFromUrl(urlStr);
                File resultFile = fileUtils.writeToSDCard(path, fileName,
                        inputStream);
                //如果resultFile==null则下载失败
                if (resultFile == null) {
                    return "downloadError";
                }
            }
        } catch (Exception e) {
            //如果异常了，也下载失败
            e.printStackTrace();
            return "downloadError";
        } finally {
            try {
                if (inputStream!=null) {
                    //别忘了关闭流
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "downloadOk";

    }

    /**
     * 连接到网络（ 抽取的公共方法）
     *
     * @param urlStr
     *            文件所在的网络地址
     * @return InputStream
     */
    public InputStream getInputStreamFromUrl(String urlStr) {
        InputStream inputStream = null;
        try {
            // 创建一个URL对象
            url = new URL(urlStr);
            // 根据URL对象创建一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // IO流读取数据
            inputStream = urlConn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
