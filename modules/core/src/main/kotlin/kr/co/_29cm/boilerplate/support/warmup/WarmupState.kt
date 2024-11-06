package kr.co._29cm.boilerplate.support.warmup

import org.springframework.boot.availability.AvailabilityState

enum class WarmupState : AvailabilityState {
    READY, NOT_READY,
}