package br.com.quadrosepaineis.quadrosepaineisapikotlin.product

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController(private val service: ProductService, private val repo: ProductRepository) {

    @PostMapping
    fun create(@Valid @RequestBody product: Product) = service.create(product)

    @PutMapping("/main-photo/{id}")
    fun uploadMainPhoto(@PathVariable id: Long, photo: MultipartFile) = service.uploadMainPhoto(id, photo)

    @PutMapping(name = "/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody product: Product) = service.update(id, product)

    @GetMapping
    fun list() = repo.findAll()

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long): Product? = service.view(id)

    @PutMapping("/{id}/isActive")
    fun changeIsActiveProperty(@PathVariable id: Long, @RequestBody isActive: Boolean) {
        service.changeIsActiveProperty(id, isActive)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @GetMapping("/photo/{id}")
    fun getPhoto(@PathVariable id: Long, response: HttpServletResponse) = service.getPhoto(id, response)

}