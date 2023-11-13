package cn.enilu.flash.api.controller.training;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.training.Student;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.service.training.StudentService;
import cn.enilu.flash.utils.factory.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/training/student")
public class StudentController extends BaseController {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/list")
	public Ret list(@RequestParam(required = false) Long id) {
		Page<Student> page = new PageFactory<Student>().defaultPage();
		page.addFilter("id",id);
		page = studentService.queryPage(page);
		return Rets.success(page);
	}
	@PostMapping
	@BussinessLog(value = "新增学生信息", key = "name")
	public Ret add(@ModelAttribute Student student){
		studentService.insert(student);
		return Rets.success();
	}
	@PutMapping
	@BussinessLog(value = "更新学生信息", key = "name")
	public Ret update(@ModelAttribute Student student){
		studentService.update(student);
		return Rets.success();
	}
	@DeleteMapping
	@BussinessLog(value = "删除学生信息", key = "id")
	public Ret remove(Long id){
		if (id == null) {
			throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
		}
		studentService.delete(id);
		return Rets.success();
	}
}
