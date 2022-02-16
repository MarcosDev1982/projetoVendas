package com.example.marcosvendas.domain.enums;


public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");


    private int cod;
    private String descição;

    private Perfil(int cod, String descição) {
        this.cod = cod;
        this.descição = descição;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
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

}
