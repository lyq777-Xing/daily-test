package cn.enilu.flash.api.controller.lend;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.book.Information;
import cn.enilu.flash.bean.entity.lend.Lend;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.Lend.LendService;
import cn.enilu.flash.utils.factory.Page;
import com.google.common.base.CharMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author lyq
 * @time 2023/11/13 11:06
 */
@RestController
@RequestMapping("/lend/lendlist")
public class LendController extends BaseController {
    @Autowired
    private LendService lendService;
    @GetMapping(value = "/list")
    public Ret list(@RequestParam(required = false) Long id) {
        Page<Lend> page = new PageFactory<Lend>().defaultPage();
        page.addFilter("id",id);
        page = lendService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 添加借阅信息
     * @param lend
     * @return
     */
    @PostMapping
    @BussinessLog(value = "新增借阅信息", key = "name")
    public Ret add(@ModelAttribute Lend lend){
//        添加创建人
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long idUser = getIdUser(request);
        lend.setCreateBy(idUser);
        lendService.insert(lend);
        return Rets.success();
    }

    /**
     * 修改借阅信息
     * @param id
     * @return
     */
    @PutMapping
    @BussinessLog(value = "修改借阅信息", key = "name")
    public Ret update(Long id){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long idUser = getIdUser(request);
        Lend lend = lendService.get(new SearchFilter("bookid", SearchFilter.Operator.EQ, id));
        lend.setModifyBy(idUser);
        lend.setReturnTime(new Date());
        lendService.update(lend);
        return Rets.success();
    }
}
