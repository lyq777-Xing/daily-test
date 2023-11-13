package cn.enilu.flash.service.training;


import cn.enilu.flash.bean.entity.training.Student;
import cn.enilu.flash.dao.training.StudentRepository;
import cn.enilu.flash.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student, Long, StudentRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StudentRepository studentRepository;

}

