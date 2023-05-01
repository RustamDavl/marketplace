package com.rustdv.marketplace.annotation;

import com.rustdv.marketplace.MarketplaceApplication;
import com.rustdv.marketplace.MarketplaceApplicationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = MarketplaceApplicationTest.class)
@ActiveProfiles("test")
@Transactional
public @interface IT {
}
