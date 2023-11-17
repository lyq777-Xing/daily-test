package cn.enilu.flash.api.controller.book;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.book.Information;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.book.InformationService;
import cn.enilu.flash.utils.factory.Page;
import com.google.common.base.CharMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author lyq
 * @time 2023/11/12 23:25
 */

@RestController
@RequestMapping("/book/information")
public class InformationController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InformationService informationService;

    /**
     * 获取所有书籍（实现模糊查询）
     * @param authorOrName
     * @return
     */
    @GetMapping(value = "/list")
    public Ret list(@RequestParam(required = false) String authorOrName) {
        Page<Information> page = new PageFactory<Information>().defaultPage();
       if(authorOrName != null && !authorOrName.equals("")){
           page.addFilter(new SearchFilter("author", SearchFilter.Operator.LIKE, authorOrName, SearchFilter.Join.or));
           page.addFilter(new SearchFilter("name", SearchFilter.Operator.LIKE, authorOrName, SearchFilter.Join.or));
       }
        page = informationService.queryPage(page);
        return Rets.success(page);
    }


    /**
     * 修改书籍信息
     * @param information
     * @return
     */
    @PutMapping
    @BussinessLog(value = "修改书籍信息", key = "name")
    public Ret update(@ModelAttribute Information information){
//       todo:如果书名进行了修改 如果该书名已经存在 则要判断作者是否也一样 若都一样则说明书籍重复(逻辑待完善)
/*
        Information information1 = informationService.get(information.getId());
        if(!information1.getName().equals(information.getName())){
            Information information2 = informationService.get(new SearchFilter("name", SearchFilter.Operator.EQ, information1.getName()));
            if(information2.getAuthor().equals(information.getAuthor())){
                return Rets.failure("该书籍已经存在");
            }else {
                informationService.update(information);
            }
        }*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long idUser = getIdUser(request);
        information.setModifyBy(idUser);
        informationService.update(information);
        return Rets.success();
    }

    /**
     * 添加书籍信息
     * @param information
     * @return
     */
    @PostMapping
    @BussinessLog(value = "新增书籍信息", key = "name")
    public Ret add(@ModelAttribute Information information){
//        随机生成编码
        UUID uuid = UUID.randomUUID();
        String uuidStr = CharMatcher.is('-').removeFrom(uuid.toString());
//        System.out.println("UUID：" + uuidStr);
        information.setNumber("N" + uuidStr);
//        添加创建人
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long idUser = getIdUser(request);
        information.setCreateBy(idUser);
        informationService.insert(information);
        return Rets.success();
    }

    /**
     * 删除书籍信息
     * @param id
     * @return
     */
    @DeleteMapping
    @BussinessLog(value = "删除书籍信息", key = "id")
    public Ret remove(Long id){
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        informationService.delete(id);
        return Rets.success();
    }

}
