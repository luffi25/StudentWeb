package uz.isystem.studentweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.studentweb.model.UserGroup;
import uz.isystem.studentweb.service.UserGroupService;

import java.util.List;

@RestController
@RequestMapping("user-group")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        UserGroup result = userGroupService.get(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<UserGroup> result = userGroupService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserGroup userGroup) {
        boolean result = userGroupService.create(userGroup);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserGroup userGroup) {
        boolean result = userGroupService.update(id, userGroup);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean result = userGroupService.delete(id);
        return ResponseEntity.ok(result);
    }
}
