package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface IDissertationService {
    RespVO upload(MultipartFile file, String name, int uid);
    ResponseEntity download(String path) throws FileNotFoundException;
    RespVO disserList(int index, int size, int uid);
    RespVO disserList(int index, int size);
    RespVO delete(int did, String path) throws FileNotFoundException;
    RespVO selectOne(int uid);
}
