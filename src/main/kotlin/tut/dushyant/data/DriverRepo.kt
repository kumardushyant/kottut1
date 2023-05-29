package tut.dushyant.data

import org.springframework.data.repository.CrudRepository
import tut.dushyant.dao.Driver

interface DriverRepo: CrudRepository<Driver, Int> {}