package com;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 P = monto prestamo
 i = tasa de interes mensual
 n = plazo de credito en meses

 cuota mensual = (p * i) / (1 - (1 + i)^-n)
 */
public class CreditCalculatorLambda implements RequestHandler<BankRequest, BankResponse> {

    @Override
    public BankResponse handleRequest(BankRequest bankRequest, Context context) {

        MathContext mathContext = MathContext.DECIMAL128;

        //Redondea a 2 decimales
        BigDecimal amount = bankRequest.getAmount().setScale(2, RoundingMode.HALF_UP);

        //obtenemos la tasa - redondeada a 2 decimales - divide por 100
        BigDecimal monthRate = bankRequest.getRate().setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), mathContext);

        //obtenemos la tasa cuando el usuario tiene una cuenta, -0.2 y se redondea a 2 deciamles y se divide por 100
        BigDecimal monthyRateWithAccount = bankRequest.getRate().subtract(BigDecimal.valueOf(0.2)).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), mathContext);

        //
        Integer term = bankRequest.getTerm();

        BigDecimal cuotaMensual = CalculateQuota(amount, monthRate, term, mathContext);
        BigDecimal cuotaMensualWithAccount = CalculateQuota(amount, monthyRateWithAccount, term, mathContext);

        BankResponse bankResponse = new BankResponse();
        bankResponse.setQuota(cuotaMensual);
        bankResponse.setRate(monthRate.multiply(BigDecimal.valueOf(100), mathContext).setScale(2, RoundingMode.HALF_UP)); // Convertir a porcentaje
        bankResponse.setTerm(term);
        bankResponse.setQuotaWithAccount(cuotaMensualWithAccount);
        bankResponse.setTermWithAccount(cuotaMensualWithAccount);
        bankResponse.setRateWithAcoount(monthyRateWithAccount);

        return bankResponse;
    }

    public BigDecimal CalculateQuota(BigDecimal amount, BigDecimal rate, Integer term, MathContext mathContext) {

        // calcular 1 + i
        BigDecimal onePlusRate = BigDecimal.ONE.add(rate);

        // elevar a la potencia -n
        BigDecimal onePlusRateToN = onePlusRate.pow(-term, mathContext);

        // calcular cuota mensual
        BigDecimal numerador = (amount.multiply(rate, mathContext));
        BigDecimal denominador = BigDecimal.ONE.subtract(onePlusRateToN, mathContext);

        BigDecimal pagoMensual = numerador.divide(denominador, mathContext);

        // Redondear a 2 decimales
        return pagoMensual.setScale(2, RoundingMode.HALF_UP);

    }
}