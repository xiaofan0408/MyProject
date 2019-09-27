package com.xiaofan0408.myprojectnew.common.service;

import com.xiaofan0408.myprojectnew.common.core.Result;
import com.xiaofan0408.myprojectnew.common.core.ResultGenerator;
import com.xiaofan0408.myprojectnew.common.util.FastDFSClient;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author xuzefan  2019/9/26 15:05
 */

@Service
public class FileService {

    @Autowired
    private FastDFSClient fastDFSClient;

    public Mono<Result> uploadPic(FilePart file) {
        return  fastDFSClient.uploadFile(file).map(new Function<String, Result>() {
            @Override
            public Result apply(String s) {
                return ResultGenerator.genSuccessResult(s);
            }
        }).onErrorResume(new Function<Throwable, Mono<? extends Result>>() {
            @Override
            public Mono<? extends Result> apply(Throwable throwable) {
                return Mono.just(ResultGenerator.genFailResult(throwable.toString()));
            }
        });
    }

}
