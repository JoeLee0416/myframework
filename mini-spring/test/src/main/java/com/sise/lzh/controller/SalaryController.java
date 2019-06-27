package com.sise.lzh.controller;

import com.sise.lzh.Service.SalaryService;
import com.sise.lzh.beans.AutoWired;
import com.sise.lzh.web.mvc.Controller;
import com.sise.lzh.web.mvc.RequestMapping;
import com.sise.lzh.web.mvc.RequestParam;

/**
 * <p>Title: SalaryController</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 14:00
 */
@Controller
public class SalaryController {
    @AutoWired
    private SalaryService salaryService;

    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name")String name,
                             @RequestParam("experience")String experience){
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
