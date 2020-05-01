package com.moonlit.kingserp.entity.admin.dto;

import com.moonlit.kingserp.entity.admin.GoodsSku;
import com.moonlit.kingserp.entity.admin.GoodsSpu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-05-01 16:04
 * @Version 1.0
 */
@Data
@Table(name = "goods")
@ApiModel(value = "Goods", description = "商品")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品Spu", name = "goodsSpu")
    private GoodsSpu goodsSpu;

    @ApiModelProperty(value = "商品Skus", name = "goodsSkus")
    private List<GoodsSku> goodsSkus;
}
