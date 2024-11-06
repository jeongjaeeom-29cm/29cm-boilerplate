package kr.co._29cm.boilerplate.controller.salescampaign

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co._29cm.boilerplate.config.page.InjectOffsetPageRequest
import kr.co._29cm.common.contract.StandardResponse
import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.contract.salescampaign.SalesCampaignStatus
import kr.co._29cm.boilerplate.usecase.salescampaign.CreateSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.DeleteSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.EnableSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.GetSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.ModifySalesCampaignUseCase
import kr.co._29cm.common.contract.OffsetPage
import kr.co._29cm.common.contract.OffsetPageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@Tag(name="sales-campaign", description = "세일즈캠페인")
@RestController
class SalesCampaignController(
    private val getSalesCampaignUseCase: GetSalesCampaignUseCase,
    private val createSalesCampaignUseCase: CreateSalesCampaignUseCase,
    private val modifySalesCampaignUseCase: ModifySalesCampaignUseCase,
    private val enableSalesCampaignUseCase: EnableSalesCampaignUseCase,
    private val deleteSalesCampaignUseCase: DeleteSalesCampaignUseCase,
) {
    @Operation(summary = "세일즈캠페인 단건 조회")
    @GetMapping("/internal/v4/sales-campaigns/{salesCampaignId:\\d+}")
    fun get(
        @PathVariable salesCampaignId: Long,
    ): StandardResponse<GetSalesCampaignDto> {
        val result = getSalesCampaignUseCase.getSalesCampaign(
            GetSalesCampaignUseCase.GetRequest(
                salesCampaignId = salesCampaignId
            )
        )

        return StandardResponse.success(GetSalesCampaignDto.from(result))
    }

    @Operation(summary = "세일즈캠페인 페이지 조회")
    @GetMapping("/internal/v4/sales-campaigns:page")
    fun page(
        @InjectOffsetPageRequest pageRequest: OffsetPageRequest
    ): StandardResponse<OffsetPage<GetSalesCampaignDto>> {
        val result = getSalesCampaignUseCase.getSalesCampaignPage(
            GetSalesCampaignUseCase.GetRequestPage(
                pageRequest = pageRequest
            )
        )

        return StandardResponse.success(
            result.map(GetSalesCampaignDto::from)
        )
    }

    @Operation(summary = "세일즈캠페인 생성")
    @PostMapping("/internal/v4/sales-campaigns:create")
    fun create(
        @RequestBody payload: CreateRequestPayload
    ): StandardResponse<CreateResultDto> {
        val result = createSalesCampaignUseCase.createSalesCampaign(
            CreateSalesCampaignUseCase.CreateRequest(
                name = payload.name,
                period = payload.period,
            )
        )

        return StandardResponse.success(
            CreateResultDto(
                salesCampaignId = result.salesCampaignId
            )
        )
    }

    @Operation(summary = "세일즈캠페인 수정")
    @PostMapping("/internal/v4/sales-campaigns/{salesCampaignId:\\d+}:modify")
    fun modify(
        @PathVariable salesCampaignId: Long,
        @RequestBody payload: ModifyRequestPayload
    ): StandardResponse<Unit> {
        modifySalesCampaignUseCase.modifySalesCampaign(
            ModifySalesCampaignUseCase.ModifyRequest(
                salesCampaignId = salesCampaignId,
                name = payload.name,
                period = payload.period,
            )
        )

        return StandardResponse.success()
    }

    @Operation(summary = "세일즈캠페인 활성화")
    @PostMapping("/internal/v4/sales-campaigns/{salesCampaignId:\\d+}:enable")
    fun enable(
        @PathVariable salesCampaignId: Long,
    ): StandardResponse<Unit> {
        enableSalesCampaignUseCase.enableSalesCampaign(
            EnableSalesCampaignUseCase.EnableRequest(
                salesCampaignId = salesCampaignId,
            )
        )

        return StandardResponse.success()
    }

    @Operation(summary = "세일즈캠페인 비활성화")
    @PostMapping("/internal/v4/sales-campaigns/{salesCampaignId:\\d+}:disable")
    fun disable(
        @PathVariable salesCampaignId: Long,
    ): StandardResponse<Unit> {
        enableSalesCampaignUseCase.disableSalesCampaign(
            EnableSalesCampaignUseCase.DisableRequest(
                salesCampaignId = salesCampaignId,
            )
        )

        return StandardResponse.success()
    }

    @Operation(summary = "세일즈캠페인 삭제")
    @PostMapping("/internal/v4/sales-campaigns/{salesCampaignId:\\d+}:delete")
    fun delete(
        @PathVariable salesCampaignId: Long,
    ): StandardResponse<Unit> {
        deleteSalesCampaignUseCase.deleteSalesCampaign(
            DeleteSalesCampaignUseCase.DeleteRequest(
                salesCampaignId = salesCampaignId,
            )
        )

        return StandardResponse.success()
    }

    data class GetSalesCampaignDto(
        @Schema(description = "세일즈캠페인 ID", required = true) val salesCampaignId: Long,
        @Schema(description = "세일즈캠페인 이름", required = true) val name: String,
        @Schema(description = "세일즈캠페인 상태", required = true) val status: SalesCampaignStatus,
        @Schema(description = "세일즈캠페인 기간", required = true) val period: DateTimePeriod,
        @Schema(description = "세일즈캠페인 활성화 여부", required = true) val enabled: Boolean
    ) {
        companion object {
            fun from(salesCampaign: GetSalesCampaignUseCase.GetSalesCampaign) = GetSalesCampaignDto(
                salesCampaignId = salesCampaign.salesCampaignId,
                name = salesCampaign.name,
                status = salesCampaign.status,
                period = salesCampaign.period,
                enabled = salesCampaign.enabled
            )
        }
    }

    data class CreateRequestPayload(
        @Schema(description = "세일즈캠페인 이름", required = true) val name: String,
        @Schema(description = "세일즈캠페인 기간", required = true) val period: DateTimePeriod,
    )


    data class CreateResultDto(
        @Schema(description = "세일즈캠페인 ID", required = true) val salesCampaignId: Long,
    )

    data class ModifyRequestPayload(
        @Schema(description = "세일즈캠페인 이름", required = true) val name: String,
        @Schema(description = "세일즈캠페인 기간", required = true) val period: DateTimePeriod,
    )
}