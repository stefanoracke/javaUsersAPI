package api.rest.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.rest.models.UserModel;



@Repository
public interface UserRepository extends CrudRepository<UserModel, Long>{
    public abstract ArrayList<UserModel> findByPriority(Integer priority);
    public abstract ArrayList<UserModel> findByName(String name);
    public abstract ArrayList<UserModel> findByNameAndPriority(String name, Integer priority);
}
