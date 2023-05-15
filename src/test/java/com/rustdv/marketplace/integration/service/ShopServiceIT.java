package com.rustdv.marketplace.integration.service;

import com.rustdv.marketplace.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;


@RequiredArgsConstructor
@Sql(
        value = "classpath:sql/data.sql",
        config = @SqlConfig(encoding = "utf-8")
)
public class ShopServiceIT extends IntegrationTestBase {


}
