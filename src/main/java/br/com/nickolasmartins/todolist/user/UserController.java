package br.com.nickolasmartins.todolist.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
       var user = this.userRepository.findByUsername(userModel.getUsername());

       
       if (user != null){
        //mensagem de erro
        System.out.println("Usuário já existe");
        //status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário já existe!!");
       }

       var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

       userModel.setPassword(passwordHashred);
       var userCreated = this.userRepository.save(userModel);
       return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
