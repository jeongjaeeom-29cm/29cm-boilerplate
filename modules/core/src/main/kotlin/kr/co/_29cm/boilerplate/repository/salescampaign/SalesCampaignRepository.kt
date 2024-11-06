package kr.co._29cm.boilerplate.repository.salescampaign

import kr.co._29cm.boilerplate.entity.salescampaign.SalesCampaign
import org.springframework.data.jpa.repository.JpaRepository

interface SalesCampaignRepository: JpaRepository<SalesCampaign, Long>