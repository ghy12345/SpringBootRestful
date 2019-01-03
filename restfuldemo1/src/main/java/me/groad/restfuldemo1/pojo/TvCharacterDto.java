package me.groad.restfuldemo1.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @BelongsProject: restfuldemo1
 * @BelongsPackage: me.groad.restfuldemo1.pojo
 * @Author: Groad
 * @CreateTime: 2019-01-03 23:13
 * @Description: 电视剧中的人物
 * @Version: 1.0
 */
@Getter
@Setter
public class TvCharacterDto implements Serializable
{
    private Integer id;
    private int tvSeriesId;
    @NotNull
    private String name;
}
