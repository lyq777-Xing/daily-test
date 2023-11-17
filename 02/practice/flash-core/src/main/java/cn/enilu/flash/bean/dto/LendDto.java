package cn.enilu.flash.bean.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lyq
 * @time 2023/11/13 11:17
 */
@Data
public class LendDto {
    private Long id;
    private Long createBy;
    private Date createTime;
    private Long modifyBy;
    private Date modifyTime;
    private Long bookid;
    private Date returnTime;
    private Long lendid;

}
