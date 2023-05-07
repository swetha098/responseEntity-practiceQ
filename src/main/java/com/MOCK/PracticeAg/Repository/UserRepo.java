package com.MOCK.PracticeAg.Repository;

import com.MOCK.PracticeAg.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    User findByuserName(String userName);

}
