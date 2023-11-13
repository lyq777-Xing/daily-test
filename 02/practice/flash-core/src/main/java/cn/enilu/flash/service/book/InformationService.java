package cn.enilu.flash.service.book;

import cn.enilu.flash.bean.entity.book.Information;
import cn.enilu.flash.dao.book.InformationRepository;
import cn.enilu.flash.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @time 2023/11/12 23:13
 */
@Service
public class InformationService extends BaseService<Information,Long, InformationRepository> {
    private Logger logger = LoggerFactory.getLogger(InformationService.class);

    @Autowired
    private InformationRepository informationRepository;


}
