package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoBoleto(PagamentoComBoleto pago, Date instantePedido) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instantePedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pago.setDataPagamento(calendar.getTime());

    }
}
