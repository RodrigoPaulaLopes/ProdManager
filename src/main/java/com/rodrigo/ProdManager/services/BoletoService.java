package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {


    public void preencherPagamentoComBoleto(PagamentoBoleto pgto, Date instante){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instante);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pgto.setDataPagamento(cal.getTime());
    }
}
