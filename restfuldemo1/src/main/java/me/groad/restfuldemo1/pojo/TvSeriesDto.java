package me.groad.restfuldemo1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: restfuldemo1
 * @BelongsPackage: me.groad.restfuldemo1.pojo
 * @Author: Groad
 * @CreateTime: 2018-12-23 22:34
 * @Description: pojo 使用Bean Validation注解进行校验数据
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TvSeriesDto
{
    /**
     * 验证是否为空，如果客户端传入id，则会出现异常(要求客户端不可传入id)
     */
    @Null
    private int id;

    /**
     * 非空，要求必须传入名称
     */
    @NotNull
    private String name;

    /**
     * JsonFormat
     * 如果想用long型的timestamp表示日期，可用： @JsonFormat(shape = JsonFormat.Shape.NUMBER)
     * Past:验证 Date 和 Calendar 对象是否在当前时间之前
     * 只接受过去的时间，比当前时间还晚的被认为不合格
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Past
    private Date originRelease;

    /**
     * Valid表示要级联校验；
     * Size(min=2)表示这个列表至少要有2项内容，否则通不过校验
     */
    @Valid
    @NotNull
    @Size(min = 2)
    private List<TvCharacterDto> tvCharacterDtos;

    public TvSeriesDto(@Null int id, @NotNull String name, @Past Date originRelease)
    {
        this.id = id;
        this.name = name;
        this.originRelease = originRelease;
    }
}
