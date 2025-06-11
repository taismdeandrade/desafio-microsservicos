package br.com.tais.cadastro.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExternalApiDto implements Serializable {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
