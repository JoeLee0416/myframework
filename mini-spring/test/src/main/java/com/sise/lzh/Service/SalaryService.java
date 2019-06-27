package com.sise.lzh.Service;

import com.sise.lzh.beans.Bean;

/**
 * <p>Title: SalaryService</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 16:08
 */
@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience * 5000;
    }
}
