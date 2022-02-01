package com.xxx.server.controller;


import com.xxx.server.pojo.Joblevel;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.IJoblevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    /**
     * 获取所有职称
     * @return
     */
    @GetMapping("/")
    public List<Joblevel> getJonlevel(){
        return joblevelService.list();
    }
    /**
     * 添加职称
     */
    @PostMapping("/")
    public ResultOV addJoblevel(@RequestBody Joblevel joblevel){
        if(joblevelService.save(joblevel)){
            return ResultOV.success("添加成功!");
        }
        return ResultOV.error("添加失败!");
    }
    /**
     * 更新职位
     */
    @PutMapping("/")
    public ResultOV updateJoblevel(@RequestBody Joblevel joblevel){
        if(joblevelService.updateById(joblevel)){
            return ResultOV.success("更新成功!");
        }
        return ResultOV.error("更新失败!");
    }
    /**
     * 删除职位
     */
    @DeleteMapping("/{id}")
    public ResultOV deleteJoblevel(Integer id){
        if(joblevelService.removeById(id)){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
    /**
     * 批量删除
     */
    @DeleteMapping("/")
    public ResultOV deleteJoblevelIds(Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
}
