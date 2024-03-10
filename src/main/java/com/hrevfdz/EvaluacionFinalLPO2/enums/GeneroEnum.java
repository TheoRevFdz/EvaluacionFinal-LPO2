package com.hrevfdz.EvaluacionFinalLPO2.enums;

public enum GeneroEnum {
    GENEROS("generos"),
    GENEROS_LIST("generosList"),
    GENEROS_REGISTER("generosRegister"),
    GENEROS_TITLE("Géneros");

    public final String nameVar;

    private GeneroEnum(String nameVar){
        this.nameVar = nameVar;
    }
}
