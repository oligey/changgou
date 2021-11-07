package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import error.ErrorCode;
import entity.MyPage;
import entity.Result;
import entity.StatusCode;
import exception.commonException.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/brand")
@CrossOrigin    //跨域
public class BrandController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();

        //TODO 通过code.value获取msg
        return new Result(true, StatusCode.OK, "查询品牌集合成功!", brandList);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id ){//TODO @valid + BindingResult入参校验
        Brand brand = brandService.findById(id);
        if (null == brand){
            String msg = String.format(ErrorCode.GOODS_BRAND_NOT_FOUND_ERROR.getMsg(), id);
            logger.error(msg);
            throw new ResourceNotFoundException(ErrorCode.GOODS_BRAND_NOT_FOUND_ERROR.getCode(), msg);
        }
        return new Result<Brand>(true, StatusCode.OK, "查询成功", brand);
    }

    @PostMapping()
    public Result add(@RequestBody Brand brand){
        //todo 字段校验
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

/**
     * 根据ID删除品牌数据
     * @param id
     * @return
*/

    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

/**
     * 多条件搜索品牌数据
     * @param brand
     * @return
*/

    @PostMapping(value = "/search" )
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand){
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"查询成功",list);
    }

/**
     * 品牌分页查询
     * @param page
     * @param size
     * @return
*/

    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size) {
        PageInfo<Brand> pageInfo = brandService.findPage(new MyPage(page, size));
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

/**
     * 分页搜索实现
     * @param brand
     * @param page
     * @param size
     * @return
*/

    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) Brand brand, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Brand> pageInfo = brandService.findPage(brand, new MyPage(page, size));
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }


    @GetMapping(value = "/test" )
    public List<String> testThread() {
        return brandService.testThread();
    }

}
