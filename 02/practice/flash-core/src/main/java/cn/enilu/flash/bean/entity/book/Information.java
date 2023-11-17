package cn.enilu.flash.bean.entity.book;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lyq
 * @time 2023/11/12 21:48
 */
@Entity(name="t_book_info")
@Table(appliesTo = "t_book_info",comment = "信息管理")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Information extends BaseEntity {
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
    @Column(columnDefinition = "VARCHAR(64) COMMENT '作者'")
    private String author;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '描述'")
    private String description;
    @Column(columnDefinition = "INT COMMENT '总数'")
    private Integer inventory;
    @Column(columnDefinition = "INT COMMENT '语言1:中文，2：英文，3其它'")
    private Integer language;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '书名'")
    private String name;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '编号'")
    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "VARCHAR(32) COMMENT '出版日期'")
    private String publicationDate;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '出版社'")
    private String publishingHouse;
    @Column(columnDefinition = "BIGINT COMMENT '类别ID'")
    private Long typeid;
    @Column(columnDefinition = "INT COMMENT '借阅状态:1、借出，2、未借出'")
    private Integer status;
    @Column(columnDefinition = "BIGINT COMMENT '借阅人'")
    private Long lendid;


}
