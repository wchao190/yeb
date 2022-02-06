package com.xxx.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.http.server.HttpServerResponse;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IEmployeeEcService;
import com.xxx.server.service.impl.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private IEmployeeEcService employeeEcService;
    @Autowired
    private NationServiceImpl nationService;
    @Autowired
    private PoliticsStatusServiceImpl politicsStatusService;
    @Autowired
    private JoblevelServiceImpl joblevelService;
    @Autowired
    private PositionServiceImpl positionService;
    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private EmployeeServiceImpl employeeService;

    /**
     * 获取所有员工
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @GetMapping("/")
    public RespPageBean getEmployees(@RequestParam(defaultValue = "1")Integer currentPage,
                                     @RequestParam(defaultValue = "20")Integer size,
                                     Employee employee,
                                     LocalDate[] beginDateScope){
        return employeeEcService.getEmployees(currentPage,size,employee,beginDateScope);

    }
    /**
     * 获取所有名族
     */
    @GetMapping("/nations")
    public List<Nation> getNations(){
        return nationService.list();
    }
    /**
     * 获取所有政治面貌
     */
    @GetMapping("/policicsstatus")
    public List<PoliticsStatus> getPoliticsStatus(){
        return politicsStatusService.list();
    }
    /**
     * 获取所有职称
     */
    @GetMapping("/joblevels")
    public List<Joblevel> getJoblevels(){
        return joblevelService.list();
    }
    /**
     * 获取所有职位
     */
    @GetMapping("/positions")
    public List<Position> getPositions(){
        return positionService.list();
    }
    /**
     * 获取所有工号
     */
    @GetMapping("/maxWorkId")
    public ResultOV getWorkId(){
        return employeeEcService.getWorkId();
    }
    /**
     * 获取部门id
     */
    @GetMapping("/deps")
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }
    /**
     * 添加员工
     */
    @PostMapping("/")
    public ResultOV addEmployee(@RequestBody Employee employee){

        return employeeEcService.addEmployee(employee);
    }
    /**
     * 更新员工
     */
    @PutMapping("/")
    public ResultOV updateEmployee(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return ResultOV.success("更新成功!");
        }
        return ResultOV.success("更新失败!");
    }
    /**
     * 删除员工
     */
    @DeleteMapping("/{id}")
    public ResultOV deleteEmployee(@PathVariable("id") Integer id){
        if(employeeService.removeById(id)){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.success("删除失败!");
    }
    /**
     * 下载员工
     */
    @GetMapping(value = "/export",produces = "application/vnd.ms-excel")
    public void exportEmployee(HttpServletResponse response) {
        //获取要下载的数据
        List<Employee> list = employeeEcService.getEmployee(null);
        //导出的参数
        ExportParams params = new ExportParams("员工表","员工信息", ExcelType.XSSF);
        //导出工作簿
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream outputStream = null;
        try {
            //导出的文件类型
            response.setContentType("application/vnd.ms-excel");
            //response.setHeader("Content-Type","application/octet-stream");
            //防止乱码
            response.setHeader("Content-Disposition",
                    "attachment;filename="+ URLEncoder.encode("员工信息.xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/import")
    public ResultOV importEmployee(MultipartFile file){
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        //获取所有的民族
        List<Nation> nationList = nationService.list();
        //获取所有的政治面貌
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        //获取所有部门信息
        List<Department> departmentList = departmentService.list();
        //职位
        List<Joblevel> joblevelList = joblevelService.list();
        //职称
        List<Position> positionList = positionService.list();
        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, importParams);
            for (Employee emp : list) {
                //设置名族id，根据民族的名字在 list 中找到对应的id，然后找到对应的实体，在获取名族id
                emp.setNationId(nationList.get(nationList.indexOf(new Nation(emp.getNation().getName()))).getId());
                emp.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(emp.getPoliticsStatus().getName()))).getId());
                emp.setDepartmentId(departmentList.get(departmentList.indexOf(new Department(emp.getDepartment().getName()))).getId());
                emp.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(emp.getJoblevel().getName()))).getId());
                emp.setPosId(positionList.get(positionList.indexOf(new Position(emp.getPosition().getName()))).getId());
            }
            if(employeeService.saveBatch(list)){
                return ResultOV.success("导入成功!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultOV.error("导入失败!");
    }
}