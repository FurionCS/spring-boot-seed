package ${basePackage}.web;
import RestfulResponse;
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
import javax.validation.Valid;
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
    public RestfulResponse add(@Valid @RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel},BindingResult result) {
        RestfulResponse restfulResponse=new RestfulResponse("${baseRequestMapping}/add");
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return restfulResponse;
    }

    @PostMapping("/delete")
    public RestfulResponse delete(@RequestParam Integer id) {
        RestfulResponse restfulResponse=new RestfulResponse("${baseRequestMapping}/delete");
        ${modelNameLowerCamel}Service.deleteById(id);
        return restfulResponse;
    }

    @PostMapping("/update")
    public RestfulResponse update(@Valid @RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel},BindingResult result) {
        RestfulResponse restfulResponse=new RestfulResponse("${baseRequestMapping}/update");
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return restfulResponse;
    }
    @PostMapping("/list")
    public RestfulResponse list(@RequestParam(defaultValue = "0" value="pageIndex") Integer pageIndex, @RequestParam(defaultValue = "0" value="pageSize") Integer pageSize) {
        RestfulResponse restfulResponse=new RestfulResponse("${baseRequestMapping}/list");
        PageHelper.startPage(pageIndex, pageSize);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        restfulResponse.setResult(pageInfo);
        return restfulResponse;
    }
}
