package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface IDissertationService {
    RespVO newDisser(MultipartFile file, String name);

    ResponseEntity download(String path) throws FileNotFoundException;

    RespVO disserList(int index, int size);

    RespVO delete(int did, String path) throws FileNotFoundException;

    //    RespVO getOne(int uid);
    RespVO saveDisser(Integer did);
}
