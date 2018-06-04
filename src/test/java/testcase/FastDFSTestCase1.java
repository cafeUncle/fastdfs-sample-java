package testcase;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FastDFSTestCase1 {

    public String conf_filename = "./tracker.properties";

    public String local_filename = "C:\\Users\\admin\\Desktop\\image.jpg";

    @Test
    public void upload() throws IOException, MyException {
        // 1.先创建一个配置文件——tracker.properties，配置文件的内容就是指定TrackerServer的地址
        // 2.使用全局方法加载配置文件
        ClientGlobal.init(conf_filename);
        // 3.创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        // 4.通过TrackerClient对象获得TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5.创建StorageServer的引用，null就可以了
        StorageServer storageServer = null;
        // 6.创建一个StorageClient对象，其需要两个参数，一个是TrackerServer，一个是StorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7.使用StorageClient对象上传文件(图片)
        // 参数1：文件名，参数名：扩展名，不能包含"."，参数3：文件的元数据，保存文件的原始名、大小、尺寸等，如果没有可为null
        String[] strings = storageClient.upload_file(local_filename, local_filename.split("\\.")[1], null);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void download() {
        try {

            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] b = storageClient.download_file("group1", "M00/00/00/wKgAyFsKZRSAH2qZAACt0KxMhHQ885.jpg");
            System.out.println(b);
            IOUtils.write(b, new FileOutputStream("D:/"+UUID.randomUUID().toString()+".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFileInfo(){
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            FileInfo fi = storageClient.get_file_info("group1", "M00/00/00/wKgAyFsKZRSAH2qZAACt0KxMhHQ885.jpg");
            System.out.println(fi.getSourceIpAddr());
            System.out.println(fi.getFileSize());
            System.out.println(fi.getCreateTimestamp());
            System.out.println(fi.getCrc32());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFileMate(){
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            NameValuePair nvps [] = storageClient.get_metadata("group1", "M00/00/00/wKgAyFsKZRSAH2qZAACt0KxMhHQ885.jpg");
            for(NameValuePair nvp : nvps){
                System.out.println(nvp.getName() + ":" + nvp.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            int i = storageClient.delete_file("group1", "M00/00/00/wKgAyFsKZRSAH2qZAACt0KxMhHQ885.jpg");
            System.out.println( i==0 ? "删除成功" : "删除失败:"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
