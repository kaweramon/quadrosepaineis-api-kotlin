package br.com.quadrosepaineis.quadrosepaineisapikotlin.product

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "product")
data class Product(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
                   @get:NotNull(message = "{product.name.notNull}")
                   @get:Size(min = 5, max = 50, message = "{product.name.size}")
                   var name: String? = null, var description: String? = null,
                   @get:NotNull(message = "{product.price.notNull}")
                   var price: Double? = null,
                   var registerDate: LocalDate? = null, var isActive: Boolean? = true, var sequence: Int? = null,
                   var photoUrl: String? = null)