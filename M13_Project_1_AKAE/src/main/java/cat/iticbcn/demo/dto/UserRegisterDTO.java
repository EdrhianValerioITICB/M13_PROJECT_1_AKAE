package cat.iticbcn.demo.dto;


/*
DTO with the information necessary to register a new user in the database{
    "username": "user1",
    "email": "user1@gmail.com",
    "password": "user1pass1",
    "password2": "user1pass1"
}
*/
public record UserRegisterDTO(String username, String email, String password, String password2) {


}

