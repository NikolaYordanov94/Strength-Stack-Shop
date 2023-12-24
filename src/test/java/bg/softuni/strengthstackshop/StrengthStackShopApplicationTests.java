package bg.softuni.strengthstackshop;

import bg.softuni.strengthstackshop.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StrengthStackShopApplicationTests {

    @Autowired
    private HomeController homeController;

    @Test
    void contextLoads() {
        assertNotNull(homeController);
    }

}
