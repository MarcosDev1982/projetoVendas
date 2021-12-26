package com.example.marcosvendas.domain.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa fisica"),
    PESSOAJURIDICA(1, "Pessoa juridica");

    private int codig;

    private String descircao;

    private TipoCliente(int codig, String descircao) {
        this.codig = codig;
        this.descircao = descircao;

    }

    public int getCodig() {
        return codig;
    }

    public void setCodig(int codig) {
        this.codig = codig;
    }

    public String getDescircao() {
        return descircao;
    }

    public void setDescircao(String descircao) {
        this.descircao = descircao;
    }

    public static TipoCliente toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCodig())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }


}
