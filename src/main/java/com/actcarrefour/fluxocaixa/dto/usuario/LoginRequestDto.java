package com.actcarrefour.fluxocaixa.dto.usuario;

public class LoginRequestDto {

    private String email;

    private String senha;

    // #region Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    // #endregion
}
