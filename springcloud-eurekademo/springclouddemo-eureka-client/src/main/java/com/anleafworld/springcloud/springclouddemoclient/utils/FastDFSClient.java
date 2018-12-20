package com.anleafworld.springcloud.springclouddemoclient.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }

        ClientGlobal.initByTrackers(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileName 文件全路径
     * @param extName  文件扩展名，不包含（.）
     * @param metas    文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    /**
     * 上传文件,传fileName
     *
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg
     * @return null为失败
     */
    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    /**
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg
     * @param extName  文件的扩展名 如 txt jpg等
     * @return null为失败
     */
    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    /**
     * 上传文件
     *
     * @param fileContent 文件的字节数组
     * @return null为失败
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    /**
     * 上传文件
     *
     * @param fileContent 文件的字节数组
     * @param extName     文件的扩展名 如 txt  jpg png 等
     * @return null为失败
     */
    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    /**
     * 文件下载到磁盘
     *
     * @param path   图片路径
     * @param output 输出流 中包含要输出到磁盘的路径
     * @return -1失败,0成功
     */
    public int download_file(String path, BufferedOutputStream output) {
        int result = -1;
        try {
            byte[] b = storageClient.download_file1(path);
            try {
                if (b != null) {
                    output.write(b);
                    result = 0;
                }
            } catch (Exception e) {
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取文件数组
     *
     * @param path 文件的路径 如group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    public byte[] download_bytes(String path) throws IOException, MyException {
        return storageClient.download_file1(path);
    }


    /**
     * 删除文件
     * @param group 组名如group1
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败 0成功
     */
    public Integer deleteFile(String group, String storagePath) {
        int result = -1;
        try {
            result = storageClient.delete_file(group, storagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除文件
     * @param storagePath 文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    public Integer deleteFile(String storagePath) {
        int result = -1;
        try {
            result = storageClient.delete_file1(storagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
