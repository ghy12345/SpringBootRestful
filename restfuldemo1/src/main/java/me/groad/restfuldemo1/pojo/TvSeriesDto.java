package me.groad.restfuldemo1.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @BelongsProject: restfuldemo1
 * @BelongsPackage: me.groad.restfuldemo1.pojo
 * @Author: Groad
 * @CreateTime: 2018-12-23 22:34
 * @Description: pojo
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TvSeriesDto
{
    private int id;
    private String name;
    private Date originRelease;
}
