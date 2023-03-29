package com.actcarrefour.fluxocaixa.dto.usuario;

public class LoginResponseDto {

    private String token;

    private UsuarioResponseDto usuario;

    // #region Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioResponseDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDto usuario) {
        this.usuario = usuario;
    }
    // #endregion
}
