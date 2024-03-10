package com.hrevfdz.EvaluacionFinalLPO2.enums;

public enum LibroEnum {
    LIBROS("libros"),
    LIBROS_LIST("librosList"),
    LIBROS_REGISTER("librosRegister"),
    LIBROS_EDIT("librosEdit"),
    LIBROS_TITLE("Libros");

    public final String nameVar;

    private LibroEnum(String nameVar){
        this.nameVar = nameVar;
    }
}
