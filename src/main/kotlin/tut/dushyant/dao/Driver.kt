package tut.dushyant.dao

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
@Schema(description = "Driver data object")
class Driver(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @field:Schema(required = false, description = "Unique id in database for driver")
    val id: Int? = 0,
    @field:Schema(required = true, description = "Name for driver", accessMode = Schema.AccessMode.READ_WRITE)
    val name: String,
    @field:Schema(required = true, description = "Contact email for driver", accessMode = Schema.AccessMode.READ_WRITE)
    val email: String
    )

