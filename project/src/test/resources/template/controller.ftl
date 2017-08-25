package ${basePackage}.web;

import com.company.common.response.ObjectResponse;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.annotation.Resource;
import java.util.List;

/**
* @Description ${modelNameUpperCamel} 控制器
* @Author:${author}
* @Date:${date}
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public ObjectResponse add(@Validated @RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel},BindingResult result) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return new ObjectResponse("/user/add");
    }

    @PostMapping("/delete")
    public ObjectResponse delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return new ObjectResponse("/user/delete");
    }

    @PostMapping("/update")
    public ObjectResponse update(@Validated @RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel},BindingResult result) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return new ObjectResponse("/user/update");
    }
    @PostMapping("/list")
    public ObjectResponse list(@RequestParam(defaultValue = "0",value="pageIndex") Integer pageIndex, @RequestParam(defaultValue = "0",value="pageSize") Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return new ObjectResponse("/user/list",pageInfo);
    }
}
