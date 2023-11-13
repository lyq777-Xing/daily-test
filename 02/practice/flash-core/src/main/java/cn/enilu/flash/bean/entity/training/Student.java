package cn.enilu.flash.bean.entity.training;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @ClassName : Student
 * @Description : 学生信息
 * @Author : PKL
 * @Date: 2022-06-15 09:41
 */

@Entity(name = "t_training_student")
@Table(appliesTo = "t_training_student", comment = "学生信息")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Student extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(32) COMMENT '姓名'")
    private String name;
    @Column(columnDefinition = "INT COMMENT '年龄'")
    private Integer age;
    @Column(columnDefinition = "VARCHAR(12) COMMENT '生日'")
    private String birthday;

}
