package com.moonlit.kingserp.admin;

import com.moonlit.kingserp.common.util.BigDecimalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class AdminApplicationTests {

    @Test
    void contextLoads() {
        BigDecimalUtils bigDecimalUtils = new BigDecimalUtils();
        BigDecimal add = BigDecimalUtils.add("0.25", "1.25", null);

        System.out.println(add);
    }

}
