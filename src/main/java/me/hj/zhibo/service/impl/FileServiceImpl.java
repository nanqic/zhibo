package me.hj.zhibo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.FileEntity;
import me.hj.zhibo.mapper.FileMapper;
import me.hj.zhibo.service.IFileService;
import me.hj.zhibo.vo.FileListVO;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class FileServiceImpl implements IFileService {
    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;
    @Autowired
    private FileMapper mapper;
    @Override
    public RespVO uploadFile(MultipartFile file, int uid) throws IOException {
        // 获取初始文件名
        String oldName = file.getOriginalFilename();
        // 限制文件大小为2M
        if (file.isEmpty()) return RespVO.error("未选择文件！");
        if(file.getSize()>2*1024*1024) return RespVO.error("文件大小超过限制");
        // 限定文件后缀
        if(!oldName.endsWith(".doc")) return RespVO.error("文件格式不支持");
        // 以日期格式命名文件夹
        File folder = new File(uploadPath + "docs/");

        // 创建文件夹
        if (!folder.exists()){
            folder.mkdirs();
        }
        // 创建系统时间戳拼接的文件名
        String newName = oldName.substring(0,oldName.lastIndexOf('.'))+"-"+System.currentTimeMillis()+oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder,newName));
        // 将文件路径存入数据库
        FileEntity fileEntity = new FileEntity()
                .setPath(newName)
                .setUid(uid);
        mapper.insert(fileEntity);

        return RespVO.ok("ok");
    }

    @Override
    public RespVO fileList(int index) {
        IPage<FileListVO> resPage = mapper.selectFileList(new Page<FileListVO>(index,10));

        return RespVO.ok("ok",resPage);
    }
}
