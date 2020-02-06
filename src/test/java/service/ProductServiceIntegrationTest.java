package service;

import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceIntegrationTest {

    private ProductService productService;

    @Before
    public void setUp() {
        this.productService = new ProductService();
    }

    @Test
    public void findsProductById() {
        Product product = productService.findById(4);
        assertThat(product.getId()).isEqualTo(4);
    }

    @Test
    public void updatesProduct() {
        //checking original name
        Product product = productService.findById(4);
        assertThat(product.getProductName()).isEqualTo("Chef Anton's Cajun Seasoning");

        //updating name
        product.setProductName("test_name");
        productService.update(product);
        product = productService.findById(4);
        assertThat(product.getProductName()).isEqualTo("test_name");

        //reseting to original
        product.setProductName("Chef Anton's Cajun Seasoning");
        productService.update(product);
    }

    @Test
    public void deletesProductById() {
//        //checking original exists
//        Product product = productService.findById(3);
//        assertThat(product).isNotNull();
//
//        //removing
//        productService.delete(3);
//        assertThat(productService.findById(3)).isNull();
    }

    @Test
    public void findsAll() {
        List<Product> products = productService.findAll();
        assertThat(products.size()).isEqualTo(77);
    }

    @Test
    public void doesNotFindProductsWithNotExistingParametersUsingHqlNamed() {
        List<Product> products = productService.findByCompanyNameAndCategoryNameUsingHqlNamedParams("not_existing", "not_existing");
        assertThat(products).isNullOrEmpty();
    }

    @Test
    public void findsProductsWithExistingParametersUsingHqlNamed() {
        // To samo zapytanie o produkty z parametrami uzywajac HQL named params
        List<Product> products = productService.findByCompanyNameAndCategoryNameUsingHqlNamedParams("Tokyo Traders", "Meat/Poultry");
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void findsProductsWithExistingParametersUsingHqlNoNamed() {
        // To samo zapytanie o produkty z parametrami uzywajac HQL no named params
        List<Product> products = productService.findByCompanyNameAndCategoryNameUsingHqlNoNamedParams("Tokyo Traders", "Meat/Poultry");
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void findsProductsWithExistingParametersUsingCriteria() {
        // To samo zapytanie o produkty z parametrami uzywajac Criteria
        List<Product> products = productService.findByCompanyNameAndCategoryNameUsingCriteria("Tokyo Traders", "Meat/Poultry");
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void findsProductsWithExistingParametersUsingCriteriaQuery() {
        // To samo zapytanie o produkty z parametrami uzywajac Criteria Query
        List<Product> products = productService.findByCompanyNameAndCategoryNameUsingCriteriaQuery("Tokyo Traders", "Meat/Poultry");
        assertThat(products.size()).isEqualTo(1);
    }
}

