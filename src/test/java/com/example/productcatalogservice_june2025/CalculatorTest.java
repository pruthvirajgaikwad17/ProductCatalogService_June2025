package com.example.productcatalogservice_june2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void testAdd_On2Integers_RunSuccessfullty() {
        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int res = calculator.add(1,2);

        // Assert
        assert(res == 3);
    }

    @Test
    public void testDivide_ByZero_ResultsInArithmeticException() {
        // Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class, () -> calculator.divide(3,0));
    }
}