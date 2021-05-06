package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.DisserStu;
import me.hj.zhibo.entity.Dissertation;
import me.hj.zhibo.mapper.DisserStuMapper;
import me.hj.zhibo.mapper.DissertationMapper;
import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.utils.UserUtil;
import me.hj.zhibo.vo.DisserStatusVO;
import me.hj.zhibo.vo.DissertationVO;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class DissertationServiceImpl implements IDissertationService {


    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;
    @Autowired
    private DissertationMapper mapper;
    @Autowired
    private DisserStuMapper duMapper;
    @Autowired
    private DissertationMapper dissertationMapper;
    @Autowired
    private UserMapper userMapper;

    // 搜索
    @Override
    public RespVO search(String words) {
        words = "%" + words + "%";
        Page<DissertationVO> page = new Page<>(1, 20);
        IPage<DissertationVO> vo = mapper.search(words, page);
        return RespVO.ok("ok", vo);
    }

    @Override
    public RespVO getAuditList(int index, int size) {
        Page<Dissertation> page = new Page<>(index, size);
        IPage<Dissertation> iPage = mapper.getAuditList(getUid(), page);
        return RespVO.ok("ok", iPage);
    }

    @Override
    public RespVO reject(int did, int status, String advice) {
        status++;
        Dissertation dissertation = new Dissertation()
                .setDid(did).setStatus(status).setAdvice(advice);
        mapper.updateById(dissertation);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO pass(int did, int status) {
        status += 2;
        Dissertation dissertation = new Dissertation()
                .setDid(did).setStatus(status).setAdvice("");
        mapper.updateById(dissertation);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO submitAudit(MultipartFile file) {
        String dbPath = writeFile(file).getMsg();
        int did = mapper.getDidByUid(getUid());
        // 检测是否已提交过修改
        Dissertation d = mapper.selectById(did);
        String auditPath = d.getAuditPath();
        // 如果文件路径不为空，先删除原文档
        if (auditPath != null) {
            removeFile(auditPath);
        }
        Dissertation disser = new Dissertation();
        disser.setAuditPath(dbPath)
                .setDid(did);
        // 根据审核状态提交
        int status = d.getStatus();
        switch (status) {
            case 1: case 3:
                disser.setStatus(2);
                break;
            case 4: case 6:
                disser.setStatus(5);
                break;
            case 7: case 9:
                disser.setStatus(8);
                break;
            default:
                disser.setStatus(1);
        }

        mapper.updateById(disser);
        return RespVO.ok("ok");
    }


    @Override
    public RespVO getStatus() {
        int did = mapper.getDidByUid(getUid());
        DisserStatusVO vo = mapper.getStatusByDid(did);
        return RespVO.ok("ok", vo);
    }

    // 发布新的论文题目
    @Override
    public RespVO newDisser(MultipartFile file, String name) {
        String dbPath = writeFile(file).getMsg();
        // 将文件路径存入数据库
        Dissertation disser = new Dissertation()
                .setName(name)
                .setPath(dbPath)
                .setUid(userMapper.getUid(UserUtil.getCurrentUser().getUsername()));
        mapper.insert(disser);
        return RespVO.ok("ok");
    }

    @Override
    public ResponseEntity download(String path) throws FileNotFoundException {
        // 文件路径
        File file = new File(uploadPath + path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        // 构建响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s", file.getName()));
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        // 设置响应类型
        ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

        return responseEntity;
    }

    @Override
    public RespVO myDissers(int index, int size) {
        // 查寻当前老师出的题目
        Page<DissertationVO> page = new Page<>(index, size);
        IPage<DissertationVO> resPage = mapper.getMyDissertation(getUid(), page);
        return RespVO.ok("查询成功", resPage);
    }

    @Override
    public RespVO disserList(int index, int size) {
        // 查寻所有论文题目
        Page<DissertationVO> page = new Page<>(index, size);
        IPage<DissertationVO> resPage = mapper.getDissers(page);
        return RespVO.ok("查询成功", resPage);
    }

    @Override
    public RespVO delete(int did, String path) {
        // 删除数据索引
        mapper.deleteById(did);
//        删除文件
        removeFile(path);
        return RespVO.ok("删除成功！");
    }

    @Transactional(rollbackFor = UnexpectedRollbackException.class)
    @Override
    public RespVO abort(int did) {
        int row = mapper.updateStatus(did);
        if(row != 1) return RespVO.error("已提交过审核，不可退选！");
        int row2 = duMapper.deleteById(did);
        if (row2 != 1) {
            throw new UnexpectedRollbackException("数据库异常！");
        }
        return RespVO.ok("ok");

    }

    @Override
    public RespVO saveDisser(Integer did) {
        int uid = getUid();
        // 一个学生对应1个选题，先查一下有没有选
        List<DisserStu> existDu = duMapper.isSelected(did, uid);
        if (!existDu.isEmpty()) return RespVO.error("已选择过");
        DisserStu du = new DisserStu()
                .setDid(did)
                .setUid(uid);
        duMapper.insert(du);
        Dissertation dissertation = new Dissertation()
                .setDid(did).setStatus(1);
        dissertationMapper.updateById(dissertation);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO myAspiration() {
        DissertationVO d = mapper.myAspiration(getUid());
        return RespVO.ok("ok", d);
    }

    private int getUid() {
        int uid = userMapper.getUid(UserUtil.getCurrentUser().getUsername());
        return uid;
    }

    private RespVO writeFile(MultipartFile file) {
        // 获取初始文件名
        String oldName = file.getOriginalFilename();
        // 限制文件大小为2M
        if (file.isEmpty()) return RespVO.error("未选择文件！");
        if (file.getSize() > 2 * 1024 * 1024) return RespVO.error("文件大小超过限制");
        // 限定文件后缀
        if (!oldName.endsWith(".pdf")) return RespVO.error("文件格式不支持");
        // 以日期格式命名文件夹
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
        String format = dtf.format(ld);
        File folder = new File(uploadPath + format);

        // 创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 创建uuid文件名
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."));
        String dbPath = format + newName;
        try {
            file.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
            return RespVO.error("上传失败！");
        }
        return RespVO.ok(dbPath);
    }

    private void removeFile(String path) {
        File file = new File(uploadPath + path);
        if (file.exists()) {
            file.delete();
        }
    }
}
