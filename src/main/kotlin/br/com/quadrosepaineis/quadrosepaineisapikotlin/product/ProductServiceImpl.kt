package br.com.quadrosepaineis.quadrosepaineisapikotlin.product

import br.com.quadrosepaineis.quadrosepaineisapikotlin.exceptionhandler.ResourceNotFoundException
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.time.LocalDate
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletResponse

@Service
open class ProductServiceImpl(private val prodRepository: ProductRepository) : ProductService {

    val PATH_PRODUCT_IM: String = "produtos"

    val fileSeparator = System.getProperty("file.separator")

    override fun create(product: Product) {
        product.registerDate = LocalDate.now()
        product.isActive = true
        product.sequence = prodRepository.getProductsLength() + 1
        prodRepository.save(product)
    }

    override fun uploadMainPhoto(id: Long, photo: MultipartFile) {
        if(isProductExists(id)) {
            var path: String = PATH_PRODUCT_IM + fileSeparator + id
            createImgDirIfNotExists(path)
            path += fileSeparator + "main.jpg"
            var bufferedImage: BufferedImage = ImageIO.read(photo.inputStream)

            if (bufferedImage != null) {
                var fileImg: File = File(path)
                ImageIO.write(bufferedImage, "jpg", fileImg)
            }
        }
    }

    override fun update(id: Long, product: Product) {
        if(isProductExists(id)) {
            prodRepository.save(product)
        }
    }

    override fun view(id: Long): Product? {
        if(isProductExists(id)) {
            return prodRepository.findById(id).get()
        }
        return null
    }

    override fun delete(id: Long) {
        if (isProductExists(id))
            prodRepository.deleteById(id)
    }

    override fun getPhoto(id: Long, response: HttpServletResponse) {
        if (isProductExists(id)) {
            val inputStreamPhoto: InputStream = FileInputStream(
                    File(PATH_PRODUCT_IM + fileSeparator + id + fileSeparator + "main.jpg"))
            response.contentType = MediaType.IMAGE_JPEG_VALUE
            IOUtils.copy(inputStreamPhoto, response.outputStream)
        }
    }

    override fun changeIsActiveProperty(id: Long, isActive: Boolean) {
        if (isProductExists(id)) {
            val prodDB = prodRepository.findById(id).get()
            prodDB.isActive = isActive
            prodRepository.save(prodDB)
        }
    }

    private fun createImgDirIfNotExists(path: String) {
        val dir = File(path)
        if (!dir.exists())
            dir.mkdirs()
    }

    private fun isProductExists(id: Long): Boolean {
        if (prodRepository.existsById(id))
            return true
        else
            throw ResourceNotFoundException("Produto Não encontrado!")
    }

}