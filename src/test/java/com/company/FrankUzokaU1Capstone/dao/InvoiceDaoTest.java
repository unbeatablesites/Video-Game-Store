package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.dao.InvoiceDao;
import com.company.FrankUzokaU1Capstone.models.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for (Invoice invoice: invoices) {
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setName("Jane Smith");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Jersey City");
        invoice.setState("NJ");
        invoice.setZipcode("11111");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("20.00"));
        invoice.setQuantity(25);
        invoice.setSubtotal(new BigDecimal("20.00"));
        invoice.setTax(new BigDecimal(".05"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("21.49"));

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice1, invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice1);
    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setName("Jane Smith");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Jersey City");
        invoice.setState("NJ");
        invoice.setZipcode("11111");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("20.00"));
        invoice.setQuantity(25);
        invoice.setSubtotal(new BigDecimal("20.00"));
        invoice.setTax(new BigDecimal(".05"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("21.49"));

        invoice = invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Jersey City");
        invoice.setState("NJ");
        invoice.setZipcode("11111");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("300.00"));
        invoice.setQuantity(20);
        invoice.setSubtotal(new BigDecimal("300.00"));
        invoice.setTax(new BigDecimal(".05"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("314.99"));
        invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(2, invoiceList.size());
    }
}