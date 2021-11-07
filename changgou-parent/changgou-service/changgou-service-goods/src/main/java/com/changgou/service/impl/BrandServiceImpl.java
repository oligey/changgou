package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import error.ErrorCode;
import entity.MyPage;
import exception.commonException.ResourceNotFoundException;
import exception.commonException.ValidationException;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static error.ErrorCode.GOODS_BRAND_NOT_FOUND_ERROR;

@Service
public class BrandServiceImpl implements BrandService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        //品牌名 重名判断
        Example brandExample = new Example(Brand.class);
        brandExample.createCriteria().andEqualTo("name", brand.getName());
        Brand existBrand = brandMapper.selectOneByExample(brandExample);

        if (null != existBrand) {
            String msg = String.format(ErrorCode.GOODS_BRAND_NAME_EXIST_ERROR.getMsg(), brand.getName());
            logger.error(msg);
            throw new ValidationException(ErrorCode.GOODS_BRAND_NAME_EXIST_ERROR.getCode(), msg);
        }

        brandMapper.insert(brand);
    }

    @Override
    public void delete(Integer id) {
        int deleteCount = brandMapper.deleteByPrimaryKey(id);
        if (deleteCount < 1) {
            String msg = String.format(GOODS_BRAND_NOT_FOUND_ERROR.getMsg(), id);
            logger.error(msg);
            throw new ResourceNotFoundException(GOODS_BRAND_NOT_FOUND_ERROR.getCode(), msg);
        }
    }

    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);

        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findPage(MyPage myPage) {
        PageHelper.startPage(myPage.getPage(), myPage.getSize());
        return new PageInfo<Brand>(brandMapper.selectAll());
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, MyPage myPage) {
        PageHelper.startPage(myPage.getPage(), myPage.getSize());
        Example example = createExample(brand);
        return new PageInfo<Brand>(brandMapper.selectByExample(example));
    }


/**
     * 构建查询对象
     *
     * @param brand
     * @return
*/

    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            // 品牌名称
            if (StringUtils.isNotEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            // 品牌图片地址
            if (StringUtils.isNotEmpty(brand.getImage())) {
                criteria.andLike("image", "%" + brand.getImage() + "%");
            }
            // 品牌的首字母
            if (StringUtils.isNotEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
            // 品牌id
            if (StringUtils.isNotEmpty(brand.getLetter())) {
                criteria.andEqualTo("id", brand.getId());
            }
            // 排序
            if (null != brand.getSeq()) {
                criteria.andEqualTo("seq", brand.getSeq());
            }
        }
        return example;
    }


/**
     * 根据id判断是否存在brand
     *
     * @param id
*/
//test git
    public void checkBrandExistById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (null == brand) {
            String msg = String.format(GOODS_BRAND_NOT_FOUND_ERROR.getMsg(), id);
            logger.error(msg);
            throw new ResourceNotFoundException(GOODS_BRAND_NOT_FOUND_ERROR.getCode(), msg);
        }
    }

private List<String> strList = new ArrayList<>();

    public List<String> testThread(){

        synchronized (this){
            for (int i = 0; i < 5; i++) {
                CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        String s = "hello..";
                        return s;
                    }
                }).whenComplete((s,throwable)->strList.add(s));
            }
        }
        return strList;
    }



}
