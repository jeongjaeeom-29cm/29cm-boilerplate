package kr.co._29cm.boilerplate.config.web

import kr.co._29cm.boilerplate.config.page.OffsetPageRequestHandlerMethodArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(OffsetPageRequestHandlerMethodArgumentResolver())
    }
}