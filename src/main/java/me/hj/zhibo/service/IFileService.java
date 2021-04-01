package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileService {
    RespVO uploadFile(MultipartFile file) throws IOException;
    RespVO fileList(int index);
}
