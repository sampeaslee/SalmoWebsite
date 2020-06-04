package salmo.SalmoWebsite;

import org.springframework.data.repository.CrudRepository;

//Spring automatically implements this repository interface 
//in a bean that has the same name 
public interface UserRepository extends CrudRepository<User, Integer>{

}
