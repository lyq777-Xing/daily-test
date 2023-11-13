package cn.enilu.flash.bean.entity.lend;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lyq
 * @time 2023/11/13 11:06
 */
@Entity(name="t_borrowing_record")
@Table(appliesTo = "t_borrowing_record",comment = "借阅管理")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Lend extends BaseEntity {
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
    @Column(columnDefinition = "BIGINT COMMENT '图书ID'")
    private Long bookid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATETime COMMENT '归还时间'")
    private Date returnTime;
}
