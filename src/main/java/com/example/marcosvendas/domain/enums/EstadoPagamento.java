package com.example.marcosvendas.domain.enums;


public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descição;

    private EstadoPagamento(int cod, String descição) {
        this.cod = cod;
        this.descição = descição;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescição() {
        return descição;
    }

    public void setDescição(String descição) {
        this.descição = descição;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
