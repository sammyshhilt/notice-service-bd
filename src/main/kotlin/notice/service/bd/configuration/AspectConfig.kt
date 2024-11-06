package notice.service.bd.configuration

import notice.service.bd.aspect.AspectLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
class AspectConfig {

    @Bean
    fun aspectLogging(): AspectLogging{
        return AspectLogging()
    }
}