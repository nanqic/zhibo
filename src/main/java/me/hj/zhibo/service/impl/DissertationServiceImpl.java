package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Dissertation;
import me.hj.zhibo.mapper.DissertationMapper;
import me.hj.zhibo.mapper.UserInfoMapper;
import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class DissertationServiceImpl implements IDissertationService {
    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;
    @Autowired
    private DissertationMapper mapper;
    @Autowired
    private UserInfoMapper infoMapper;
    @Override
    public RespVO upload(MultipartFile file, String name,int uid) {
        // 获取初始文件名
        String oldName = file.getOriginalFilename();
        // 限制文件大小为2M
        if (file.isEmpty()) return RespVO.error("未选择文件！");
        if(file.getSize()>2*1024*1024) return RespVO.error("文件大小超过限制");
        // 限定文件后缀
        if(!oldName.endsWith(".pdf")) return RespVO.error("文件格式不支持");
        // 以日期格式命名文件夹
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
        String format = dtf.format(ld);
        File folder = new File(uploadPath + format);

        // 创建文件夹
        if (!folder.exists()){
            folder.mkdirs();
        }
        // 创建uuid文件名
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."));
        String dbPath = format+newName;
        // 查询出题老师名字
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("name").eq("uid", uid);
        String teacher = infoMapper.selectOne(wrapper).getName();
        try {
            file.transferTo(new File(folder,newName));
            // 讲文件路径存入数据库
            Dissertation disser = new Dissertation()
                    .setName(name)
                    .setPath(dbPath)
                    .setTeacher(teacher);
            mapper.insert(disser);
        }catch (IOException e){
            e.printStackTrace();
            return RespVO.ok("上传失败！");
        }
        return RespVO.ok("ok");
    }

    @Override
    public ResponseEntity download(String path) throws FileNotFoundException{
        // 文件路径
        File file = new File(uploadPath+path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        // 构建响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add ( "Content-Disposition",String.format("attachment;filename=\"%s",file.getName()));
        headers.add ( "Cache-Control","no-cache,no-store,must-revalidate" );
        headers.add ( "Pragma","no-cache" );
        headers.add ( "Expires","0" );

        // 设置响应类型
        ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

            return responseEntity;
    }

    @Override
    public RespVO disserList(int index, int size) {
        IPage<Dissertation> iPage = new Page<>(index,size );
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("did", "name", "path");
        IPage<Dissertation> resPage = mapper.selectPage(iPage, null);
        return RespVO.ok("查询成功", resPage);
    }
    @Override
    public RespVO disserList(int index, int size, int uid) {
        // 根据uid查询老师的名字
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.select("name");
        wrapper1.eq("uid", uid);
        String teacher = infoMapper.selectOne(wrapper1).getName();
        // 查寻指定老师的题目
        IPage<Dissertation> iPage = new Page<>(index,size );
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("did", "name", "path");
        wrapper.eq("teacher", teacher);
        IPage<Dissertation> resPage = mapper.selectPage(iPage, wrapper);
        return RespVO.ok("查询成功", resPage);
    }
    @Override
    public RespVO delete(int did, String path) {
        // 删除数据索引
        mapper.deleteById(did);
//        删除文件
        File file = new File(uploadPath+path);
        if(file.exists()){
            file.delete();
        }
        return RespVO.ok("删除成功！");
    }

    @Override
    public RespVO selectOne(int uid) {
        Dissertation d = mapper.selectByUid(uid);
        return RespVO.ok("ok",d);
    }
}
