package cn.enilu.flash.api.controller.lend;

import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.entity.lend.Lend;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.service.Lend.LendService;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @time 2023/11/13 11:06
 */
@RestController
@RequestMapping("/lend/lendlist")
public class LendController {
    @Autowired
    private LendService lendService;
    @GetMapping(value = "/list")
    public Ret list(@RequestParam(required = false) Long id) {
        Page<Lend> page = new PageFactory<Lend>().defaultPage();
        page.addFilter("id",id);
        page = lendService.queryPage(page);
        return Rets.success(page);
    }
}
