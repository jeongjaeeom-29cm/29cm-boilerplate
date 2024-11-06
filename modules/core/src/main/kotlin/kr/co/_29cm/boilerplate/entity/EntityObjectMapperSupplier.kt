package kr.co._29cm.boilerplate.entity

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import io.hypersistence.utils.hibernate.type.util.ObjectMapperSupplier
import java.util.Locale
import java.util.TimeZone

class EntityObjectMapperSupplier : ObjectMapperSupplier {
    override fun get(): ObjectMapper = entityJsonMapper

    companion object {
        private val entityJsonMapper = JsonMapper.builder()
            .findAndAddModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .defaultTimeZone(TimeZone.getTimeZone("Asia/Seoul"))
            .defaultLocale(Locale.KOREA)
            .build()
    }
}