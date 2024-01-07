package api.rest.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.rest.models.UserModel;
import api.rest.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(Long id){
        return userRepository.findById(id);
    }

    public ArrayList<UserModel> getByPriority(Integer priority){
        return userRepository.findByPriority(priority);
    }

    public ArrayList<UserModel> getByName(String name){
        return userRepository.findByName(name);
    }
    public ArrayList<UserModel> getByNameAndPriority(String name, Integer priority) {
        if (name != null && priority != null) {
            // Handle both parameters
            return userRepository.findByNameAndPriority(name, priority);
        } else if (name != null) {
            // Handle only name parameter
            return userRepository.findByName(name);
        } else if (priority != null) {
            // Handle only priority parameter
            return userRepository.findByPriority(priority);
        } else {
            // Return an empty list or handle the case where no parameters are provided
            return new ArrayList<>();
        }
    }

    public boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}
