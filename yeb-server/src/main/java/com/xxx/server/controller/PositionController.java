package com.xxx.server.controller;


import com.xxx.server.pojo.Position;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/system/config/position")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    /**
     * 获取职位信息
     * @return
     */
    @GetMapping("/")
    public List<Position> getPositions(){
        return positionService.list();
    }
    /**
     * 添加职位信息
     */
    @PostMapping("/")
    public ResultOV addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)){
            return ResultOV.success("添加成功!");
        }
        return ResultOV.error("添加失败!");
    }
    @PutMapping("/")
    public ResultOV updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return ResultOV.success("更新成功!");
        }
        return ResultOV.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public ResultOV deletePosition(@PathVariable("id") Integer id){
        if(positionService.removeById(id)){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }

    @DeleteMapping("/")
    public ResultOV deletePositionByIds(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
}
