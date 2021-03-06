package me.groad.restfuldemo1.duridconfig;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @BelongsProject: moff
 * @BelongsPackage: cn.purvavideha.moff.config
 * @Author: Groad
 * @CreateTime: 2019-01-02 16:33
 * @Description:
 * @Version: 1.0
 */
@WebServlet(urlPatterns = "/druid/*", initParams={
        /*@WebInitParam(name="allow",value="172.16.35.24"),*/// IP白名单 (没有配置或者为空，则允许所有访问)
        /*@WebInitParam(name="deny",value="192.168.6.73"),*/// IP黑名单 (存在共同时，deny优先于allow)
                                                             @WebInitParam(name="loginUsername",value="admin"),// 用户名
                                                             @WebInitParam(name="loginPassword",value="admin"),// 密码
                                                             @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet
{
    private static final long serialVersionUID = 7359758657306626394L;
}
