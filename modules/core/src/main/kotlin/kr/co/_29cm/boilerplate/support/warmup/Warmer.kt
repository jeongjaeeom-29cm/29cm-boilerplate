package kr.co._29cm.boilerplate.support.warmup

import kr.co._29cm.boilerplate.support.logging.logger
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.availability.AvailabilityChangeEvent
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class Warmer(
    private val applicationContext: ApplicationContext,
    private val warmerDelegators: List<WarmerDelegator>
): InitializingBean {
    private val logger = logger()

    override fun afterPropertiesSet() {
        try {
            warmerDelegators.forEach { it.warmup() }
            AvailabilityChangeEvent.publish(applicationContext, WarmupState.READY)
        } catch (e: Exception) {
            logger.error(e) { "웜업 코드를 실행 중 오류가 발생하였습니다. message=${e.message}" }
            AvailabilityChangeEvent.publish(applicationContext, WarmupState.NOT_READY)
        }
    }
}