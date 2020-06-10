package com.online.edu.eduservice.controller;


import com.online.edu.commonLx.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import com.online.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //上传excel文件
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file")MultipartFile file){
        //获取上传文件
        List<String> msg=subjectService.importSubject(file);
        if(msg.size()==0){
            return R.ok();
        }else{
            return R.error().data("msgList",msg);
        }

    }
    //返会所有分类数据，返会要求json数据格式
    @GetMapping
    public R getAllSubjectList(){
        List<OneSubjectDto> list = subjectService.getSubjectList();
        return R.ok().data("OneSubjectDto",list);
    }
    //删除分类
    @DeleteMapping("{id}")
    public R deleteSubjectId(@PathVariable String id){

       boolean flag=subjectService.delteSubjectById(id);
       if (flag){
           return R.ok();
       }else{
           return R.error();
       }
    }
    //添加一级分类
    @PostMapping("addOneLevel")
    public R addOneLevel(@RequestBody EduSubject eduSubject){
        boolean flag=subjectService.saveOneLevel(eduSubject);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //添加二级分类
    @PostMapping("addtwoLevel")
    public R addTwoLevel(@RequestBody EduSubject eduSubject){
        boolean flag=subjectService.saveTwoLevel(eduSubject);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


}

