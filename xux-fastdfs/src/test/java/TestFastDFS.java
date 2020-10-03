import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TestFastDFS {

    @Test
    public void testuploadDirectyFile() throws IOException, MyException {
        StorageClient1 storageClient = getStorageClient1();

        NameValuePair[] meta_list = getNameValuePair();

        long start = System.currentTimeMillis();
        String path = uploadDirectyFile(storageClient, "D:\\270.rar", meta_list);
        System.out.println("========" + path);
        long end = System.currentTimeMillis();
        System.out.println("========cost:"+(end-start)/1000);
    }

    @Test
    public void testuploadBreakpointFile() throws IOException, MyException {
        StorageClient1 storageClient = getStorageClient1();

        NameValuePair[] meta_list = getNameValuePair();

        long start = System.currentTimeMillis();
        String[] path2 = uploadBreakpointFile(storageClient, "D:\\270.rar", meta_list);
        for (String pa : path2) {
            System.out.println("========" + pa);
        }
        long end = System.currentTimeMillis();
        System.out.println("========cost:"+(end-start)/1000);
    }

    public static NameValuePair[] getNameValuePair() {
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("filename","Koala.jpg");
        meta_list[1] = new NameValuePair("ext","jpg");
        meta_list[2] = new NameValuePair("auth","zj");
        return meta_list;
    }


    public static StorageClient1 getStorageClient1() throws IOException, MyException {
        String confName = "fdfs_client.conf";
        ClassLoader loader = TestFastDFS.class.getClassLoader();
        // 先从当前类所处路径的根目录中寻找属性文件
        URL url = loader.getResource(confName);
        //1. 加载配置文件
        ClientGlobal.init(url.getPath());
        //2. 创建管理端对象
        TrackerClient trackerClient = new TrackerClient();
        //3. 获取连接
        TrackerServer connection = trackerClient.getConnection();
        //4. 创建存储端对象
        StorageClient1 storageClient = new StorageClient1(connection, null);
        return storageClient;
    }

    public static String uploadDirectyFile(StorageClient1 storageClient, String fileName, NameValuePair[] meta_list) throws IOException, MyException {
        String path = storageClient.upload_file1(fileName, FilenameUtils.getExtension(fileName), meta_list);
        return path;
    }

    public static String[] uploadBreakpointFile(StorageClient1 storageClient, String fileName, NameValuePair[] meta) throws IOException, MyException {
        int defaultSize = 1024 * 1024 * 5;
        String[] results = null;
        File file = new File(fileName);
        long originalFileSize = file.length();

        byte[] file_buff;

        //NameValuePair[] vars = new NameValuePair[]{new NameValuePair("fileName", fileName), new NameValuePair("fileSize", String.valueOf(originalFileSize))};
        int number = (int) (originalFileSize / defaultSize), leftLength;
        number = originalFileSize % defaultSize == 0 ? number : number + 1;
        byte[] bytes;
        try {
            InputStream input = new FileInputStream(file);
            file_buff = new byte[input.available()];
            input.read(file_buff);
            if (originalFileSize > defaultSize) {
                // 如果文件块大，则实现分块上传，需要准备一个空的文件
                for (int i = 0; i < number; i++) {
                    if (originalFileSize - (i) * defaultSize < defaultSize) {
                        leftLength = (int) (originalFileSize - (i) * defaultSize);
                        leftLength = leftLength < 0 ? (int) originalFileSize : leftLength;
                        bytes = new byte[leftLength];
                        if (i == 0) {
                            results = storageClient.upload_appender_file(bytes, 0, leftLength, FilenameUtils.getExtension(fileName), meta);
                        } else {
                            /*采用追加的方式*/
                            storageClient.append_file(results[0], results[1], bytes, 0, leftLength);
                        }
                    } else {
                        bytes = new byte[(int) defaultSize];
                        leftLength = (int) defaultSize;
                        if (i == 0) {
                            results = storageClient.upload_appender_file(bytes, 0, leftLength, FilenameUtils.getExtension(fileName), meta);
                        } else {
                            /*采用追加的方式*/
                            storageClient.append_file(results[0], results[1], bytes, 0, leftLength);
                        }
                    }
                }
                //  写入内容
                storageClient.modify_file(results[0], results[1], 0, file_buff, 0, file_buff.length);
            } else {
                //  如果文件比默认的文件要小，则直接上传
                results = storageClient.upload_file(file_buff, FilenameUtils.getExtension(fileName), meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
