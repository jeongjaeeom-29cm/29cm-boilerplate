package kr.co._29cm.boilerplate.http.tag

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "tag-api")
interface TagClient {
    @GetMapping(path = ["/tags/{id}"])
    fun tag(@PathVariable id: Long): String
}