package kr.co._29cm.boilerplate.config.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class SalesCampaignAuditorHolder: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of(AUDITOR)
    }

    companion object {
        const val AUDITOR = "boilerplate-INTERNAL-API"
    }
}