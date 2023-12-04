package cn.enilu.flash.bean.entity.book;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lyq
 * @time 2023/11/13 1:50
 */
@Entity(name="t_book_type")
@Table(appliesTo = "t_book_type",comment = "分类管理")
@Data
public class Type extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "BIGINT COMMENT '创建人'")
    private Long createBy;
    @Column(columnDefinition = "DATETime COMMENT '创建时间/注册时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @Column(columnDefinition = "BIGINT COMMENT '最后更新人'")
    private Long modifyBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATETime COMMENT '最后更新时间'")
    private Date modifyTime;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '类别名称'")
    private String name;
    @Column(columnDefinition = "INTEGER COMMENT '排序'")
    private Integer num;
}
