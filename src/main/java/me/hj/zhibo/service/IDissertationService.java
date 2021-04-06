package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface IDissertationService {
    RespVO newDisser(MultipartFile file, String name);

    ResponseEntity download(String path) throws FileNotFoundException;

    RespVO myDissers(int index, int size);

    RespVO disserList(int index, int size);

    RespVO delete(int did, String path) throws FileNotFoundException;

    RespVO saveDisser(Integer did);

    RespVO myAspiration();

    RespVO abort(int did);

    RespVO search(String words);
}
