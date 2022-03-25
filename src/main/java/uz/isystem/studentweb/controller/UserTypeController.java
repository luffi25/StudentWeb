package uz.isystem.studentweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.studentweb.dto.UserTypeDto;
import uz.isystem.studentweb.model.UserType;
import uz.isystem.studentweb.service.UserTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-type")
public class UserTypeController {
    @Autowired
    private UserTypeService userTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        UserTypeDto result = userTypeService.get(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestParam("name") String name){
        List<UserTypeDto> result = userTypeService.getByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<UserTypeDto> result = userTypeService.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> getByName(@Valid @RequestBody UserTypeDto userTypeDto){
        UserTypeDto result = userTypeService.create(userTypeDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserTypeDto userType){
        UserTypeDto result = userTypeService.update(id, userType);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        Boolean result = userTypeService.delete(id);
        return ResponseEntity.ok(result);
    }
}
