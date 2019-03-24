package br.com.quadrosepaineis.quadrosepaineisapikotlin.product

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ProductServiceTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var productService: ProductServiceImpl

    @Before
    fun setUp() {
        this.productService = Mockito.mock(ProductServiceImpl::class.java)
    }

    @Test
    fun testCreate() {
        val prod = Product()
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(prod)
        productService.create(prod)
        Mockito.verify(productService, Mockito.times(1)).create(prod)
    }

    @Test
    fun testUpdate() {
        val prod = Product()
        Mockito.`when`(productRepository.existsById(1L)).thenReturn(true)
        productService.update(1L, prod)
        Mockito.verify(productService, Mockito.times(1)).update(1L, prod)
    }

    @Test
    fun testView() {
        Mockito.`when`(productRepository.existsById(1L)).thenReturn(true)
        Mockito.`when`(productRepository.findById(1L)).thenReturn(Mockito.any())
        Mockito.`when`(productService.view(1L)).thenReturn(Mockito.any())
        productService.view(1L)
        Mockito.verify(productService, Mockito.times(1)).view(1L);
    }

    @Test
    fun testChangeIsActiveProperty() {
        Mockito.`when`(productRepository.existsById(Mockito.anyLong())).thenReturn(true)
        Mockito.`when`(productRepository.findById(Mockito.anyLong())).thenReturn(Mockito.any())
        var prod = Product()
        Mockito.`when`(productRepository.save(prod)).thenReturn(
                prod)
        productService.changeIsActiveProperty(Mockito.anyLong(), Mockito.anyBoolean())
        Mockito.verify(productService, Mockito.times(1)).changeIsActiveProperty(
                Mockito.anyLong(), Mockito.anyBoolean())
    }


}