package com.xux.fastdfs;

import org.junit.Test;

public class DownloadTest {

    @Test
    public void download() throws Exception {
        String url = "https://downloads.gradle.org/distributions/gradle-4.7-bin.zip";
        String filePath = "D://";
        int threadCount = 1;
        Download download = new Download(filePath,
                url, threadCount);
        long start = System.currentTimeMillis();
        download.download();
        while (!download.isDone()) {
            sleep(1500);
            // 控制台输出当前进度
            System.out.println(download.getCurrentPercentage());
        }
        long end = System.currentTimeMillis();
        System.out.println("========cost:"+(end-start)/1000);
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}