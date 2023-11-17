package cn.enilu.flash.bean.dto;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lyq
 * @time 2023/11/12 21:48
 */
@Data
public class InformationDto extends BaseEntity {
    private Long id;
    private Long createBy;
    private Date createTime;
    private Long modifyBy;
    private Date modifyTime;
    private String author;
    private String description;
    private Integer inventory;
    private Integer language;
    private String name;
    private String number;
    private String publicationDate;
    private String publishingHouse;
    private Long typeid;
    private Integer status;
    private Long lendid;


}
