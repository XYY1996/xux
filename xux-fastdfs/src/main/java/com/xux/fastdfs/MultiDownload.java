package com.xux.fastdfs;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiDownload {
    static int ThreadCount = 3;       //代表3个进程
    static int finishedThread = 0;   //记录文件下完的数字
    //确定下载地址
    static String path = "http://10.21.19.72:10080/group1/M00/39/B2/oYYBAF6BmlyAJt5bEHuco2dBl4U248.rar";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //发送get请求，请求这个地址的资源
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            if(conn.getResponseCode() == 200){
                //拿到所请求资源文件的长度
                int length = conn.getContentLength();

                File file = new File("D://123-down.rar");
                //生成临时文件
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //设置临时文件的大小
                raf.setLength(length);
                raf.close();
                //计算出每个线程应该下载多少字节
                int size = length / ThreadCount;

                for (int i = 0; i < ThreadCount; i++) {
                    //计算线程下载的开始位置和结束位置
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    //如果是最后一个线程，那么结束位置写死
                    if(i == ThreadCount - 1){
                        endIndex = length - 1;
                    }
//                  System.out.println("线程" + i + "的下载区间是：" + startIndex + "---" + endIndex);
                    new DownLoadThread(startIndex, endIndex, i).start();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("========cost:"+(end-start)/1000);
    }
}
