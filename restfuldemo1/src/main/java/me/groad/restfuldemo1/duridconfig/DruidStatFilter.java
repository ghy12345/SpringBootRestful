package me.groad.restfuldemo1.duridconfig;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @BelongsProject: moff
 * @BelongsPackage: cn.purvavideha.moff.config
 * @Author: Groad
 * @CreateTime: 2019-01-02 16:31
 * @Description:
 * @Version: 1.0
 */
@WebFilter(filterName="druidWebStatFilter",
        urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
        })
public class DruidStatFilter extends WebStatFilter
{
}
