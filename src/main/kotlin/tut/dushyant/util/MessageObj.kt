package tut.dushyant.util

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Object used for composing error message")
data class MessageObj(
    @field:Schema(required = true, description = "Error code")
    val code:Int,
    @field:Schema(required = true, description = "Error message for end consumer")
    val message: String)
