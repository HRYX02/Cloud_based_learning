package com.sxx.vod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-16-14:42
 */

public class VodTest {
    /**
     * @description 根据视频ID获取视频播放地址
     */
    @Test
    public void getPlayUrl() throws ClientException {
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t6Mk4SiuysEEZySK1UW", "7hqAQYxxxgRkoFmZP2wqnxbiMDJ0cm");

        // 创建获取视频地址的request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        // 向request对象里面设置视频id
        request.setVideoId("f899224023a771eebfef0764a0fd0102");

        // 调用初始化对象里的方法，传递request获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList){
            System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL());
        }
        System.out.println("VideoBase.Title = " + response.getVideoBase().getTitle());
    }

    /**
     * @description 根据视频ID获取视频播放凭证
     */
    @Test
    public void getPlayAuth() throws ClientException {
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t6Mk4SiuysEEZySK1UW", "7hqAQYxxxgRkoFmZP2wqnxbiMDJ0cm");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        // 向request对象里面设置视频id
        request.setVideoId("925ead60239d71ee80ca6732b68f0102");
        response = client.getAcsResponse(request);
        System.out.println("PlayAuth:" + response.getPlayAuth());
    }

    /**
     *  @description 实现上传视频功能
     */
    @Test
    public void uploadVideo() {
        String accessKeyId = "LTAI5t6Mk4SiuysEEZySK1UW";
        String accessKeySecret = "7hqAQYxxxgRkoFmZP2wqnxbiMDJ0cm";
        // 上传后显示的名称
        String title = "上传测试";
        // 本地文件路径名称
        String fileName = "E:/project/atguigu/Cloud_based_learning/resources/TestMovie.mp4";

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
