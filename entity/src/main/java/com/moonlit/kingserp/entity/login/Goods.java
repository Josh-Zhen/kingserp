package com.moonlit.kingserp.entity.login;

import lombok.Data;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 9/10/2020 23:09
 * @Version 1.0
 */
@Data
public class Goods {

    private Long id;
    /**
     * 名稱
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * /是否已審核/是否上架/是否熱點/是否推荐/是否包邮/是否精选/精选置顶
     */
    private Integer status;
}
