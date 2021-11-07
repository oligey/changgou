package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import entity.MyPage;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据id查找品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 添加品牌
     * @param brand
     */
    void add(Brand brand);

    /***
     * 删除品牌
     * @param id
     */
    void delete(Integer id);

    /**
     * 品牌列表 条件查询
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页查询
     * @param myPage
     * @return
     */
    PageInfo<Brand> findPage(MyPage myPage);

    /**
     * 分页条件查询
     * @param brand
     * @param myPage
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, MyPage myPage);

    public List<String> testThread();
}
