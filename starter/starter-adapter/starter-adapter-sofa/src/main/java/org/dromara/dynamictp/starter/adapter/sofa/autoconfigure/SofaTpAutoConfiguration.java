package org.dromara.dynamictp.starter.adapter.sofa.autoconfigure;

import org.dromara.dynamictp.adapter.sofa.SofaDtpAdapter;
import org.dromara.dynamictp.core.spring.DtpBaseBeanConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SofaTpAutoConfiguration related
 *
 * @author yanhom
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(name = "com.alipay.sofa.rpc.config.UserThreadPoolManager")
@ConditionalOnBean({DtpBaseBeanConfiguration.class})
@AutoConfigureAfter({DtpBaseBeanConfiguration.class})
public class SofaTpAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SofaDtpAdapter sofaDtpAdapter() {
        return new SofaDtpAdapter();
    }
}
