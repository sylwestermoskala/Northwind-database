package service;

import model.Supplier;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierServiceIntegrationTest {

    private SupplierService supplierService;

    @Before
    public void setUp() {
        this.supplierService = new SupplierService();
    }

    @Test
    public void findsSupplierById() {
        Supplier supplier = supplierService.findById(1);
        assertThat(supplier.getId()).isEqualTo(1);
    }

    @Test
    public void updatesSupplier() {
        //checking original name
        Supplier supplier = supplierService.findById(1);
        assertThat(supplier.getCompanyName()).isEqualTo("Exotic Liquids");

        //updating name
        supplier.setCompanyName("test_name");
        supplierService.update(supplier);
        supplier = supplierService.findById(1);
        assertThat(supplier.getCompanyName()).isEqualTo("test_name");

        //reseting to original
        supplier.setCompanyName("Exotic Liquids");
        supplierService.update(supplier);
    }

    @Test
    public void deletesSupplierById() {
//        //checking original exists
//        Supplier supplier = supplierService.findById(1);
//        assertThat(supplier).isNotNull();
//
//        //removing
//        supplierService.delete(1);
//        assertThat(supplierService.findById(1)).isNull();
    }

    @Test
    public void findsAll() {
        List<Supplier> suppliers = supplierService.findAll();
        assertThat(suppliers.size()).isEqualTo(29);
    }

}