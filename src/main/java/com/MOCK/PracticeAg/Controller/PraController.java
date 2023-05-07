package com.MOCK.PracticeAg.Controller;

import com.MOCK.PracticeAg.Entity.Book;
import com.MOCK.PracticeAg.Entity.User;
import com.MOCK.PracticeAg.Exception.IdNotFoundException;
import com.MOCK.PracticeAg.Exception.NameFoundException;
import com.MOCK.PracticeAg.Repository.BookRepo;
import com.MOCK.PracticeAg.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/path")
public class PraController {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/postuser")
    public ResponseEntity<User>createUser(@RequestBody User user){
        if(userRepo.findByuserName(user.getUserName())!=null){
            throw new NameFoundException("name already exists");


        }
        userRepo.save(user);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/postbook")
    public ResponseEntity<Book>createBook(@Valid @RequestBody Book book){
        bookRepo.save(book);
        return ResponseEntity.ok().body(book);
    }
    @GetMapping("/userdetails")
    public ResponseEntity<List<User>>getUser(){
        List<User>user=userRepo.findAll();
        if(!user.isEmpty()){
        return ResponseEntity.ok().body(user);
        }
        else return ResponseEntity.noContent().build();
    }
    @GetMapping("/bookdetails")
    public ResponseEntity<List<Book>>getBook(){
        List<Book>book=bookRepo.findAll();
        if(!book.isEmpty())
        return ResponseEntity.ok().body(book);
        else
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Boolean>deleteUser(@PathVariable Long id){
        User user=userRepo.findById(id).orElse(null);
        if(user!=null) {
            userRepo.deleteById(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deletebook(@PathVariable Long id){
        Book book=bookRepo.findById(id).orElse(null);
        if(book==null){
            throw new IdNotFoundException("id not found");
          //  return ResponseEntity.ok(false);
        }else {
            bookRepo.deleteById(id);
            return ResponseEntity.ok(true);
        }

    }
    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User>updateUser(@RequestBody User user , @PathVariable Long id){
        User usernew=null;
        Optional<User> userold=userRepo.findById(id);
        if(userold.isPresent()){
            usernew = userold.get();
            usernew.setId(id);
            usernew.setUserName(user.getUserName());
            userRepo.save(usernew);
            return ResponseEntity.ok().body(usernew);
        }
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/getbyname/{userName}")
    public ResponseEntity<User> getbyName(@PathVariable String userName){
        User user=userRepo.findByuserName(userName);
        if(user!=null){
            return ResponseEntity.ok(user);
        }else return ResponseEntity.noContent().build();
    }
    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<Optional<User>> getUserByid(@PathVariable Long id){
        Optional<User> user=userRepo.findById(id);
        if(user==null)return ResponseEntity.noContent().build();
        else return ResponseEntity.ok().body(user);
    }
}
