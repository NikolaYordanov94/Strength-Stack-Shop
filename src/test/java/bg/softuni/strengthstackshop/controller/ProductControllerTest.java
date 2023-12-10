package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.enums.Category;
import bg.softuni.strengthstackshop.service.OrderService;
import bg.softuni.strengthstackshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.strengthstackshop.service.ProductService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;




@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser
    public void testAllProductsView() throws Exception {
        mockMvc.perform(get("/offers"))
                .andExpect(status().isOk())
                .andExpect(view().name("offers"))
                .andExpect(model().attributeExists("allOffers"));
    }

    @Test
    @WithMockUser
    public void testAllSupplementsView() throws Exception {

        mockMvc.perform(get("/offers-supplements"))
                .andExpect(status().isOk())
                .andExpect(view().name("offers-supplements"))
                .andExpect(model().attributeExists("allSupplements"));
    }

    @Test
    @WithMockUser // Use this annotation if the endpoint requires authenticated users
    public void testAllGearView() throws Exception {
        // Mock the response from productService.getAllGear()
        // If necessary, use when(productService.getAllGear()).thenReturn(...);

        mockMvc.perform(get("/offers-gear"))
                .andExpect(status().isOk())
                .andExpect(view().name("offers-gear"))
                .andExpect(model().attributeExists("allGear"));
    }

    @Test
    @WithMockUser // Use this annotation if the endpoint requires authenticated users
    public void testAllClothesView() throws Exception {
        // Mock the response from productService.getAllClothes()
        // If necessary, use when(productService.getAllClothes()).thenReturn(...);

        mockMvc.perform(get("/offers-clothes"))
                .andExpect(status().isOk())
                .andExpect(view().name("offers-clothes"))
                .andExpect(model().attributeExists("allClothes"));
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN", "USER"})
    public void testPostAddProductSuccess() throws Exception {
        mockMvc.perform(post("/product-add")
                                .with(csrf()) // Include CSRF token
                                .param("price", "10.00")
                        .param("description", "qk produkt")
                        .param("createdAt", String.valueOf(LocalDate.now()))
                        .param("brand", "izmislen brand")
                        .param("pictureUrl", "alabala")
                        .param("category", String.valueOf(Category.SUPPLEMENTS))

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));
    }
}
