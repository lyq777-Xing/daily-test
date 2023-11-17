package cn.enilu.flash.api.controller.book;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.book.Information;
import cn.enilu.flash.bean.entity.book.Type;
import cn.enilu.flash.bean.entity.training.Student;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.book.TypeService;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyq
 * @time 2023/11/13 1:56
 */
@RestController
@RequestMapping("/book/type")
public class TypeController extends BaseController {

    @Autowired
    private TypeService typeService;


    @GetMapping(value = "/list")
    public Ret list(@RequestParam(required = false) Long id) {
        Page<Type> page = new PageFactory<Type>().defaultPage();
        page.addFilter("id",id);
        page = typeService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 修改分类信息
     * @param type
     * @return
     */
    @PutMapping
    @BussinessLog(value = "修改分类信息", key = "name")
    public Ret update(@ModelAttribute Type type){
//      如果分类进行了修改 则需要判断修改后的分类名是否已经存在
        Type type1 = typeService.get(type.getId());
        if(!type1.getName().equals(type.getName())){
            if(typeService.count(new SearchFilter("name",SearchFilter.Operator.EQ, type.getName())) > 0){
//                说明修改后的分类名称已经存在
                return Rets.failure("分类名称已经存在");
            }else {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                Long idUser = getIdUser(request);
                type.setCreateBy(idUser);
                typeService.update(type);
            }
        }
        return Rets.success();
    }

    /**
     * 添加分类信息
     * @param type
     * @return
     */
    @PostMapping
    @BussinessLog(value = "新增分类信息", key = "name")
    public Ret add(@ModelAttribute Type type){
        if(typeService.count(new SearchFilter("name",SearchFilter.Operator.EQ, type.getName())) > 0){
//                说明添加的分类名称已经存在
            return Rets.failure("分类名称已经存在");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            Long idUser = getIdUser(request);
            type.setModifyBy(idUser);
            typeService.insert(type);
        }
        return Rets.success();
    }

    /**
     * 删除分类信息
     * @param id
     * @return
     */
    @DeleteMapping
    @BussinessLog(value = "删除分类信息", key = "id")
    public Ret remove(Long id){
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        typeService.delete(id);
        return Rets.success();
    }
}
