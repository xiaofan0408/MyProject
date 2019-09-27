package com.xiaofan0408.myprojectnew.common.controller;

import com.xiaofan0408.myprojectnew.common.core.Result;
import com.xiaofan0408.myprojectnew.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * @author xuzefan  2019/9/26 15:03
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Result> uploadPic(@RequestPart("file") FilePart file){
        return fileService.uploadPic(file);
    }
}
