package com.xux.fastdfs;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadThread extends Thread{
    int startIndex;
    int endIndex;
    int threadId;

    public DownLoadThread(int startIndex, int endIndex, int threadId) {
        super();
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        //再次发送http请求，下载原文件
        try {
            File progressFile = new File(threadId + ".txt");
            //判断进度临时文件是否存在
            if(progressFile.exists()){
                FileInputStream fis = new FileInputStream(progressFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //从进度临时文件中读取出上一次下载的总进度，然后与原本的开始位置相加，得到新的开始位置
                startIndex += Integer.parseInt(br.readLine());
                fis.close();
            }
            System.out.println("线程" + threadId + "的下载区间是：" + startIndex + "---" + endIndex);
            HttpURLConnection conn;
            URL url = new URL(MultiDownload.path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //设置本次http请求所请求的数据的区间
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

            //请求部分数据，相应码是206
            if(conn.getResponseCode() == 206){
                //流里此时只有1/3原文件的数据
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                int total = 0;
                //拿到临时文件的输出流
                File file = new File("D://123-down.rar");
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //把文件的写入位置移动至startIndex
                raf.seek(startIndex);
                while((len = is.read(b)) != -1){
                    //每次读取流里数据之后，同步把数据写入临时文件
                    raf.write(b, 0, len);
                    total += len;
//                  System.out.println("线程" + threadId + "下载了" + total);

                    //生成一个专门用来记录下载进度的临时文件
                    RandomAccessFile progressRaf = new RandomAccessFile(progressFile, "rwd");
                    //每次读取流里数据之后，同步把当前线程下载的总进度写入进度临时文件中
                    progressRaf.write((total + "").getBytes());
                    progressRaf.close();
                }
                System.out.println("线程" + threadId + "下载完毕-------------------小志参上！");
                raf.close();

                //把记录进程的文件删除    必须3个进程全部都下载完成后才可以删除
                MultiDownload.finishedThread++;
                synchronized (MultiDownload.path) {  //有线程安全问题，静态变量为唯一的
                    if(MultiDownload.finishedThread == MultiDownload.ThreadCount){
                        for (int i = 0; i < MultiDownload.ThreadCount; i++) {
                            File f = new File(i + ".txt");
                            f.delete();
                        }
                        MultiDownload.finishedThread = 0;
                    }
                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
