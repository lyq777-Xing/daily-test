package cn.enilu.flash.service.Lend;

import cn.enilu.flash.bean.entity.lend.Lend;
import cn.enilu.flash.dao.Lend.LendRepository;
import cn.enilu.flash.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @time 2023/11/13 11:23
 */
@Service
public class LendService extends BaseService<Lend,Long, LendRepository> {
    private Logger logger = LoggerFactory.getLogger(LendService.class);

}
