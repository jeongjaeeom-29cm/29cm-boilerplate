package kr.co._29cm.boilerplate.config.http

import kr.co._29cm.boilerplate.http.HttpModule
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackageClasses = [HttpModule::class])
class HttpClientConfig