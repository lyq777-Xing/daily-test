package cn.enilu.flash.bean.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author lyq
 * @time 2023/11/13 1:53
 */
@Data
public class TypeDto {
    private Long id;
    private Long createBy;
    private Date createTime;
    private Long modifyBy;
    private Date modifyTime;
    private String name;
    private Integer num;
}
