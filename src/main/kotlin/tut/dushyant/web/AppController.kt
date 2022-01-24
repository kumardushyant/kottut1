package tut.dushyant.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.Optional
import tut.dushyant.data.DriverRepo
import tut.dushyant.dao.Driver
import tut.dushyant.util.MessageObj

@RestController
class AppController {

    @Autowired
    private lateinit var repo: DriverRepo

    private class DriverData (val id: Int = 0, val name: String, val email: String)

    @PostMapping("/driver")
    @Operation(summary = "Register a driver")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Registration successful", content = [Content(mediaType = "application/json", schema = Schema(implementation = Driver::class))]),
        ApiResponse(responseCode = "401", description = "User is not logged in. Please login to continue", content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageObj::class))])
    ])
    fun addDriver(@RequestBody driver: Driver): Driver {
        return repo.save(driver)
    }

    @GetMapping("/driver")
    @Operation(summary = "Get list of registered drivers")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "List of drivers", content = [Content(mediaType = "application/json", schema = Schema(implementation = Array<Driver>::class))]),
        ApiResponse(responseCode = "401", description = "User is not logged in. Please login to continue", content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageObj::class))])
    ])
    fun getDriver(): Iterable<Driver> {
        return repo.findAll()
    }

    @GetMapping("/driver/{id}")
    @Operation(summary = "Get a Driver", parameters = [
        Parameter(name = "id", required = true, description = "Unique id of driver")
    ])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Driver data", content = [Content(mediaType = "application/json", schema = Schema(implementation = Driver::class))]),
        ApiResponse(responseCode = "404", description = "Driver not found in database for given id", content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageObj::class))]),
        ApiResponse(responseCode = "401", description = "User is not logged in. Please login to continue", content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageObj::class))])
    ])
    fun getDriverById(@PathVariable id: Int): Driver {
        val valu: Optional<Driver> = repo.findById(id)
        if (valu.isPresent) {
            return valu.get()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found")
        }
    }

    @PutMapping("/driver")
    @Operation(summary = "Update a Driver", requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Driver to be updated",
                        required = true,
                        content = [Content(mediaType = "application/json", schema = Schema(implementation = Driver::class))]
                ))
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Driver updated", content = [Content(mediaType = "application/json", schema = Schema(implementation = Driver::class))]),
        ApiResponse(responseCode = "404", description = "Driver not found in database for given id", content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageObj::class))]),
        ApiResponse(responseCode = "401", description = "User is not logged in. Please login to continue", content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageObj::class))])
    ])
    fun updateDriver(@RequestBody driver: Driver): Driver {
        val id: Int = driver.id!!
        if (id == 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Driver input id is not provided")
        }
        val value: Optional<Driver> = repo.findById(id)
        if (value.isPresent) {
            val driDB = Driver(name = driver.name, email = driver.email)

            return repo.save(driDB)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found")
        }
    }
}