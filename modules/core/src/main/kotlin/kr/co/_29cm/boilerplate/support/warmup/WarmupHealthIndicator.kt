package kr.co._29cm.boilerplate.support.warmup

import org.springframework.boot.actuate.availability.AvailabilityStateHealthIndicator
import org.springframework.boot.actuate.health.Status
import org.springframework.boot.availability.ApplicationAvailability
import org.springframework.boot.availability.AvailabilityState
import org.springframework.stereotype.Component

@Component
class WarmupHealthIndicator(
    applicationAvailability: ApplicationAvailability
) : AvailabilityStateHealthIndicator(
    applicationAvailability,
    WarmupState::class.java,
    {
        it.add(WarmupState.READY, Status.UP)
        it.add(WarmupState.NOT_READY, Status.OUT_OF_SERVICE)
    }
) {
    override fun getState(applicationAvailability: ApplicationAvailability): AvailabilityState {
        return applicationAvailability.getState(WarmupState::class.java, WarmupState.NOT_READY)
    }
}