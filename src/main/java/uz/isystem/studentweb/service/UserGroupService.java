package uz.isystem.studentweb.service;

import org.springframework.stereotype.Service;
import uz.isystem.studentweb.exception.ServerBadRequestException;
import uz.isystem.studentweb.model.UserGroup;
import uz.isystem.studentweb.repository.UserGroupRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserService userService;
    private final GroupService groupService;

    public UserGroupService(UserGroupRepository userGroupRepository,
                            UserService userService,
                            GroupService groupService) {
        this.userGroupRepository = userGroupRepository;
        this.userService = userService;
        this.groupService = groupService;
    }

    public UserGroup get(Integer id) {
        UserGroup userGroup = getEntity(id);
//        userGroup.setUser(userService.get(userGroup.getUserId()));
        userGroup.setGroup(groupService.get(userGroup.getGroupId()));
        return userGroup;
    }

    public List<UserGroup> getAll() {
        List<UserGroup> userGroupList = userGroupRepository.findAllByDeletedAtIsNull();
        if (userGroupList.isEmpty()) {
            throw new ServerBadRequestException("Users not found!");
        }
        for (UserGroup userGroup : userGroupList) {
            userGroup.setGroup(groupService.get(userGroup.getGroupId()));
//            userGroup.setUser(userService.get(userGroup.getUserId()));
        }
        return userGroupList;
    }

    public boolean create(UserGroup userGroup) {
        checkFields(userGroup.getUserId(), userGroup.getGroupId());
        userGroup.setStatus(true);
        userGroup.setCreatedAt(LocalDateTime.now());
        userGroupRepository.save(userGroup);
        return true;
    }

    public boolean update(Integer id, UserGroup userGroup) {
        checkFields(userGroup.getUserId(), userGroup.getGroupId());
        UserGroup old = getEntity(id);
        userGroup.setId(id);
        userGroup.setStatus(true);
        userGroup.setCreatedAt(old.getCreatedAt());
        userGroup.setUpdatedAt(LocalDateTime.now());
        userGroupRepository.save(userGroup);
        return true;
    }

    public boolean delete(Integer id){
        UserGroup userGroup = getEntity(id);
        userGroup.setDeletedAt(LocalDateTime.now());
        userGroupRepository.save(userGroup);
        return true;
    }
    //Secondary Functions
    public UserGroup getEntity(Integer id) {
        Optional<UserGroup> optional = userGroupRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("User Group not found!");
        }
        return optional.get();
    }

    public void checkFields(Integer userId, Integer groupId) {
        userService.getEntity(userId);
        groupService.getEntity(groupId);
    }
}
