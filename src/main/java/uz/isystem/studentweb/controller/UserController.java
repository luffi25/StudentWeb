package uz.isystem.studentweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.isystem.studentweb.dto.UserDto;
import uz.isystem.studentweb.dto.UserFilterDto;
import uz.isystem.studentweb.model.User;
import uz.isystem.studentweb.security.CustomUserDetail;
import uz.isystem.studentweb.service.UserService;
import uz.isystem.studentweb.util.SpringSecurityUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //ADMIN
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserDto user) {
        UserDto result = userService.create(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        UserDto result = userService.get(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<User> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody User user) {
        boolean result = userService.update(id, user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean result = userService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/page")
    public ResponseEntity<?> page(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        List<UserDto> result = userService.page(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody UserFilterDto userFilterDto) {
        List<UserDto> result = userService.filter(userFilterDto);
        return ResponseEntity.ok(result);
    }

    //User
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(){
        return ResponseEntity.ok(userService.get(SpringSecurityUtil.getUserId()));
    }
}
