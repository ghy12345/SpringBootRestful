package me.groad.restfuldemo1.Controller;

import me.groad.restfuldemo1.pojo.TvSeriesDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @BelongsProject: restfuldemo1
 * @BelongsPackage: me.groad.restfuldemo1.Controller
 * @Author: Groad
 * @CreateTime: 2018-12-23 22:37
 * @Description:
 * @Version: 1.0
 */

@RestController
@RequestMapping("/tvseries")
public class TvSeriesController
{

    /**  
      * @description 日志
      * @author Groad
      * @date 2018/12/25 23:01
      */  
    private static final Log log = LogFactory.getLog(TvSeriesController.class);



    /**  
      * @description get方法
      * @author Groad
      * @date 2018/12/25 23:01
      * @params []
      * @return java.util.List<me.groad.restfuldemo1.pojo.TvSeriesDto>
      */  
    @GetMapping
    public List<TvSeriesDto> getAll()
    {
        if (log.isTraceEnabled())
        {
            log.trace("getAll()被调用了");
        }
        List<TvSeriesDto> list = new ArrayList<>();
        //对日期进行操作
        Calendar instance = Calendar.getInstance();
        instance.set(2015, Calendar.OCTOBER, 16, 0, 0);
        list.add(new TvSeriesDto(1, "大好时光", instance.getTime()));
        instance.set(2005, Calendar.JANUARY, 31, 0, 0);
        list.add(new TvSeriesDto(2, "仙剑奇侠传", instance.getTime()));
        instance.set(2015, Calendar.AUGUST, 31, 0, 0);
        list.add(new TvSeriesDto(3, "伪装者", instance.getTime()));
        return list;
    }

    /**  
      * @description 根据id取值
      * @author Groad
      * @date 2018/12/25 23:28
      * @params [id]
      * @return me.groad.restfuldemo1.pojo.TvSeriesDto
      */  
    @GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable int id)
    {
        if (log.isTraceEnabled())
        {
            log.trace("getOne" + id);
        }
        if (id == 1)
        {
            return caerteTime();
        }
        else if (id == 2)
        {
            return createSword();
        }else
        {
            throw new ResourceNotFoundException();
        }

    }


    /**  
      * @description 前端传递过来的参数
      * @author Groad
      * @date 2018/12/25 23:37
      * @params [tvSeriesDto]
      * @return me.groad.restfuldemo1.pojo.TvSeriesDto
      */
    @PostMapping
    public TvSeriesDto insertOne(@RequestBody TvSeriesDto tvSeriesDto)
    {
        if (log.isTraceEnabled())
        {
            log.trace("这里应该写新增tvSeriesDto到数据库的代码，传递进来的参数是:" + tvSeriesDto);
        }
        tvSeriesDto.setId(9999);
        return tvSeriesDto;
    }
    
    /**  
      * @description update(PUT)
      * @author Groad
      * @date 2018/12/26 0:02
      * @params [id, tvSeriesDto]
      * @return me.groad.restfuldemo1.pojo.TvSeriesDto
      */  
    @PutMapping("/{id}")
    public TvSeriesDto updateOne(@PathVariable int id, @RequestBody TvSeriesDto tvSeriesDto)
    {
        if (log.isTraceEnabled())
        {
            log.trace("updateOne" + id);
        }
        if (id == 1)
        {
            return caerteTime();
        }
        else if (id == 2)
        {
            return createSword();
        }else
        {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * 删除资源的例子；如果方法声明了HttpServletRequest request参数，spring会自动把当前的request传给方法。
     * 类似声明即可得到还有 HttpServletResponse，Authentication、Locale等
     *
     * @RequestParam(value="delete_reason", required=false) String deleteReason 表示deleteReason参数的值
     * 来自Request的参数delete_reason（等同于request.getParameter("delete_reason")，可以是URL中Querystring，
     * 也可以是form post里的值），required=false表示不是必须的。默认是required=true，required=true时，如果请求
     * 没有传递这个参数，会被返回400错误。
     * 类似的注解还有@CookieValue @RequestHeader等。
     */
    @DeleteMapping("/{id}")
    public Map<String, String> deleteOne(@PathVariable int id, HttpServletRequest request, @RequestParam(value = "delete_reason", required = false) String deleteReason)
    {
        if (log.isTraceEnabled())
        {
            log.trace("deleteOne" + id);
        }
        Map<String, String> result = new HashMap<>();
        if (id == 1)
        {
            result.put("message", "#1被" + request.getRemoteAddr() + "删除(原因:" + deleteReason + ")");
        }
        else if (id == 2)
        {
            throw new RuntimeException("#2不能被删除");
        }else
        {
            //不存在
            throw new ResourceNotFoundException();
        }
        return result;
    }

    /**  
      * @description 创建两个电视剧
      * @author Groad
      * @date 2018/12/25 23:08
      * @params []
      * @return me.groad.restfuldemo1.pojo.TvSeriesDto
      */  
    private TvSeriesDto caerteTime()
    {
        Calendar c = Calendar.getInstance();
        c.set(2015, Calendar.OCTOBER, 16, 0, 0);
        return new TvSeriesDto(1, "大好时光", c.getTime());
    }

    private TvSeriesDto createSword()
    {
        Calendar c = Calendar.getInstance();
        c.set(2005, Calendar.JANUARY, 31, 0, 0);
        return new TvSeriesDto(2, "仙剑奇侠传", c.getTime());
    }

}
