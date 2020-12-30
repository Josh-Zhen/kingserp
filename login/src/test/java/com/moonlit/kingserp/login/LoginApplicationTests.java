package com.moonlit.kingserp.login;

import com.moonlit.kingserp.entity.login.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginApplicationTests {

    @Test
    void contextLoads() {
        Goods goodsA = new Goods();
        goodsA.setId(1L);
        goodsA.setName("Iphone 10");
        goodsA.setDescription("128G/紫色");
        goodsA.setStatus(0);

        Goods goodsB = new Goods();
        goodsB.setId(2L);
        goodsB.setName("Iphone 10");
        goodsB.setDescription("256G/紅色");
        goodsB.setStatus(0b1100110);

        System.out.println(re(goodsB).toString());

    }

    public static Goods re(Goods goods) {
        String status = Integer.toBinaryString(goods.getStatus());
        /*
          /是否已審核/是否上架/是否熱點/是否推荐/是否包邮/是否精选/精选置顶/
          /    0    /   0   /   0   /   0   /   0   /   0  /   0   /
         */

        return goods;
    }

}
