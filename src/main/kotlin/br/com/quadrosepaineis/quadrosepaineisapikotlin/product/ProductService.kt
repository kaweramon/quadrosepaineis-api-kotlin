package br.com.quadrosepaineis.quadrosepaineisapikotlin.product

import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

interface ProductService {

    fun create(product: Product)

    fun uploadMainPhoto(id: Long, photo: MultipartFile)

    fun update(id: Long, product: Product)

    fun view(id: Long): Product?

    fun delete(id: Long)

    fun getPhoto(id: Long, response: HttpServletResponse)

    fun changeIsActiveProperty(id: Long, isActive: Boolean)
}