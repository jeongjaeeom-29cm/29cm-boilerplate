package kr.co._29cm.boilerplate.config.mapper

import org.mapstruct.InjectionStrategy
import org.mapstruct.MapperConfig
import org.mapstruct.MappingConstants

@MapperConfig(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = [SupportValidation::class]
)
class DefaultMapperConfig