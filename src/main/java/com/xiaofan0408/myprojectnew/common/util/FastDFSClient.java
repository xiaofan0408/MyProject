package com.xiaofan0408.myprojectnew.common.util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Consumer;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Schedulers;

@Component
public class FastDFSClient {

    @Autowired
    private FastFileStorageClient storageClient;

    @Value("${fdfs-image-base-url}")
    private String fdfsImageBaseUrl;

    /**
     * 上传文件
     *
     * @param file
     *            文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public Mono<String> uploadFile(FilePart file){
       return Mono.create(new Consumer<MonoSink<String>>() {
           @Override
           public void accept(MonoSink<String> stringMonoSink) {
               file.content().subscribe(new Consumer<DataBuffer>() {
                   @Override
                   public void accept(DataBuffer dataBuffer) {
                       try {
                           StorePath storePath = storageClient.uploadFile(dataBuffer.asInputStream(), dataBuffer.readableByteCount(),
                                   FilenameUtils.getExtension(file.filename()), null);
                           stringMonoSink.success(getResAccessUrl(storePath));
                       } catch (Exception e) {
                           stringMonoSink.error(e);
                       }
                   }
               });

           }
       }).publishOn(Schedulers.elastic());
    }

    public Mono<String> uploadFile2(MultipartFile file) {

        return Mono.create(new Consumer<MonoSink<String>>() {
            @Override
            public void accept(MonoSink<String> monoSink) {
                try {
                    StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                            FilenameUtils.getExtension(file.getOriginalFilename()), null);
                    monoSink.success(getResAccessUrl(storePath));
                } catch (IOException e) {
                    monoSink.error(e);
                }
            }
        }).publishOn(Schedulers.elastic());
    }

    public Mono<String> uploadQRCode(MultipartFile file) {
        return Mono.create(new Consumer<MonoSink<String>>() {
            @Override
            public void accept(MonoSink<String> stringMonoSink) {
                try {
                    StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                            "png", null);
                    stringMonoSink.success(getResAccessUrl(storePath));
                } catch (IOException e) {
                    stringMonoSink.error(e);
                }
            }
        }).publishOn(Schedulers.elastic());
    }

    public Mono<String> uploadFace(MultipartFile file){

        return uploadImageAndCrtThumbImage(file);
    }


    public Mono<String> uploadBase64(MultipartFile file){
        return uploadImageAndCrtThumbImage(file);
    }

    private Mono<String> uploadImageAndCrtThumbImage(MultipartFile file) {
        return Mono.create(new Consumer<MonoSink<String>>() {
            @Override
            public void accept(MonoSink<String> stringMonoSink) {
                try {
                    StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                            "png", null);
                    stringMonoSink.success(getResAccessUrl(storePath));
                } catch (IOException e) {
                    stringMonoSink.error(e);
                }
            }
        }).publishOn(Schedulers.elastic());
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content
     *            文件内容
     * @param fileExtension
     * @return
     */
    public Mono<String> uploadFile(String content, String fileExtension) {
        return Mono.create(new Consumer<MonoSink<String>>() {
            @Override
            public void accept(MonoSink<String> stringMonoSink) {
                byte[] buff = content.getBytes(Charset.forName("UTF-8"));
                ByteArrayInputStream stream = new ByteArrayInputStream(buff);
                StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
                stringMonoSink.success(getResAccessUrl(storePath));
            }
        }).publishOn(Schedulers.elastic());

    }

    // 封装图片完整URL地址
	private String getResAccessUrl(StorePath storePath) {
		String fileUrl = fdfsImageBaseUrl + "/" + storePath.getFullPath();
		return fileUrl;
	}

    /**
     * 删除文件
     *
     * @param fileUrl
     *            文件访问地址
     * @return
     */
    public Mono<Boolean> deleteFile(String fileUrl) {
        return Mono.create(new Consumer<MonoSink<Boolean>>() {
            @Override
            public void accept(MonoSink<Boolean> booleanMonoSink) {
                if (StringUtils.isEmpty(fileUrl)) {
                    booleanMonoSink.success(false);
                    return;
                }
                try {
                    StorePath storePath = StorePath.praseFromUrl(fileUrl);
                    storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
                    booleanMonoSink.success(true);
                } catch (FdfsUnsupportStorePathException e) {
                    booleanMonoSink.error(e);
                }
            }
        }).publishOn(Schedulers.elastic());
    }
}
