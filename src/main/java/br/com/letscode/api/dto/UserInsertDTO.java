package br.com.letscode.api.dto;

import br.com.letscode.api.service.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends  UserDTO {

    private String password;

    public UserInsertDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
