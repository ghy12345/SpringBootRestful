package me.groad.restfuldemo1.Controller;

import me.groad.restfuldemo1.pojo.TvSeriesDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
public class TvSerisController
{
    @GetMapping
    public List<TvSeriesDto> getAll()
    {
        List<TvSeriesDto> list = new ArrayList<>();
        //对日期进行操作
        Calendar instance = Calendar.getInstance();
        instance.set(2015, Calendar.OCTOBER, 16, 0, 0);
        list.add(new TvSeriesDto(1, "大好时光", instance.getTime()));
        return list;
    }
}
