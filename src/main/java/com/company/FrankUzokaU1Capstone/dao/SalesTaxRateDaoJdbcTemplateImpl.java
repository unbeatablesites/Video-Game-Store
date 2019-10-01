package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.models.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_SALES_TAX_RATE_SQL =
            "select * from sales_tax_rate where state = ?";

    @Autowired
    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalesTaxRate getSalesTaxRate(String state) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALES_TAX_RATE_SQL, this::mapRowToSalesTaxRate, state);
        } catch (EmptyResultDataAccessException e) {
            // if there is no sales tax rate with this state, just return null
            return null;
        }
    }

    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));

        return salesTaxRate;
    }
}
