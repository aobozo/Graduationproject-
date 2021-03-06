package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.DepartmentType;
import com.wbg.sums.service.DepartmentTypeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departmentType")
public class DepartmentTypeController {
    @Autowired
    private DepartmentTypeService departmentTypeService;

    /**
     * 根据dId删除
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    @RequiresRoles("admin")
    public Result deleteByPrimaryKey(DepartmentType departmentType) {
        try {
            return departmentTypeService.deleteByPrimaryKey(departmentType.getdId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("移除失败,该部门还有人，清理后重试！");
        }
    }

    /**
     * 添加对象DepartmentType
     *
     * @param departmentType
     * @return
     */
    @PostMapping("/insert")
    @RequiresPermissions({"insert"})
    public Result insert(@RequestBody DepartmentType departmentType) {
        try {
            return departmentTypeService.insert(departmentType) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("添加失败,请重试！");
        }

    }

    /**
     * 根据dId查找对象  最多只能返回一个对象
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(DepartmentType departmentType) {
        try {
            DepartmentType departmentType1 = departmentTypeService.selectByPrimaryKey(departmentType.getdId());
            if (departmentType1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(departmentType1);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {

            Subject subject = SecurityUtils.getSubject();
            PageHelper pageHelper = new PageHelper();
            pageHelper.startPage(pageNum, pageSize);
            List<DepartmentType> list = departmentTypeService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, departmentTypeService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param departmentType
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    @RequiresPermissions({"update"})
    public Result updateByPrimaryKey(@RequestBody DepartmentType departmentType) {
        try {
            return departmentTypeService.updateByPrimaryKey(departmentType) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据d_id修改顺序
     *
     * @param departmentType
     * @return
     */
    @PostMapping("/updateSort")
    public Result updateSort(@RequestBody DepartmentType departmentType) {
        try {
            return departmentTypeService.updateSort(departmentType.getdId(), departmentType.getdSort()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改状态
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/updateStatus")
    @RequiresPermissions({"update"})
    public Result updateStatus(DepartmentType departmentType) {
        try {
            return departmentTypeService.updateStatus(departmentType.getdId(), departmentType.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result("出错,请重试！");
        }
    }

    /**
     * 根据dName查询是否存在该名称
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/selectName")
    public Result selectName(DepartmentType departmentType) {
        try {
            return departmentTypeService.selectName(departmentType.getdName()) > 0 ? new Result("名称已存在") : new Result().success("可以添加");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据Status查询全部
     *
     * @param status
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(String status, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            if ("all".equals(status)) {
                status = "";
            }
            PageHelper pageHelper = new PageHelper();
            pageHelper.startPage(pageNum, pageSize);
            List<DepartmentType> list = departmentTypeService.selectAllStatus(status);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, departmentTypeService.count(status));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据dName模糊查询
     *
     * @param dName
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(String dName) {
        try {
            List<DepartmentType> list = departmentTypeService.selectAllVague(dName);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, 10);
            }
        } catch (Exception ex) {
            return new Result("出错,请重试！");
        }
    }

    /**
     * 根据Status查询状态，只返回两个字段
     *
     * @return
     */
    @GetMapping("/iselectAllStatus")
    public Result iSelectAllStatus() {
        try {
            List<DepartmentType> list = departmentTypeService.iselectAllStatus("true");
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

}
