package cn.enilu.flash.service.book;

import cn.enilu.flash.bean.entity.book.Type;
import cn.enilu.flash.dao.book.TypeRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.utils.factory.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @time 2023/11/13 1:55
 */
@Service
public class TypeService extends BaseService<Type,Long, TypeRepository> {
    private Logger logger = LoggerFactory.getLogger(TypeService.class);


}
