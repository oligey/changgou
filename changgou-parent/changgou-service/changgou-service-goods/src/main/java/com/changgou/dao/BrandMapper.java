package com.changgou.dao;

import com.changgou.goods.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/*****
 * dao使用通用mapper 继承 tk.mybatis.mapper.common.Mapper<>类
 */
public interface BrandMapper extends Mapper<Brand> {
}
