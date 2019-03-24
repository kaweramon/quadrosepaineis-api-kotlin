package br.com.quadrosepaineis.quadrosepaineisapikotlin.product

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM product where is_active = true;")
    fun getProductsLength(): Int

}