package br.com.tais.cadastro.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExternalPetsDto implements Serializable {

    private String id;
    private String name;
    private String temperament;


    public ExternalPetsDto() {
    }

    public ExternalPetsDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
