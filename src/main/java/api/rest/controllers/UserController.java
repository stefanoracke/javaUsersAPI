package api.rest.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.rest.models.UserModel;
import api.rest.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping()
    public ArrayList<UserModel> getUsers(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "priority", required = false) Integer priority) {
        if (name != null && priority != null) {
            // Handle both parameters
            return this.userService.getByNameAndPriority(name, priority);
        } else if (name != null) {
            // Handle only name parameter
            return this.userService.getByName(name);
        } else if (priority != null) {
            // Handle only priority parameter
            return this.userService.getByPriority(priority);
        } else {
            // Handle case where no parameters are provided
            return this.userService.getUsers();
        }
}

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }


    

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return "Se eliminó con exitó el usuario con id " + id;
        } else {
            return "No se pudo eliminar el usuario con id " + id;
        }
    }

}
