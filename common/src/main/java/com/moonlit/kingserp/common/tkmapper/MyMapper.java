package com.moonlit.kingserp.common.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 *
 * @author Joshua
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
