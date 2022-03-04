package uz.isystem.studentweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.studentweb.exception.ServerBadRequestException;
import uz.isystem.studentweb.model.Group;
import uz.isystem.studentweb.repository.GroupRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseService courseService;

    public Group get(Integer id) {
        Group group = getEntity(id);
        group.setCourse(courseService.get(group.getCourseId()));
        return group;
    }

    public List<Group> getAll() {
        List<Group> groupList = groupRepository.findAllByDeletedAtIsNull();
        for (Group group : groupList) {
            group.setCourse(courseService.get(group.getCourseId()));
        }
        return groupList;
    }

    public boolean create(Group group) {
        // checking course
        courseService.getEntity(group.getCourseId());
        group.setCreatedAt(LocalDateTime.now());
        group.setStatus(true);
        groupRepository.save(group);
        return true;
    }

    public boolean update(Integer id, Group group) {
        courseService.getEntity(group.getCourseId());
        Group old = getEntity(id);
        group.setId(id);
        group.setStatus(old.getStatus());
        group.setCreatedAt(old.getCreatedAt());
        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
        return true;
    }

    public boolean delete(Integer id) {
        Group group = getEntity(id);
        group.setDeletedAt(LocalDateTime.now());
        groupRepository.save(group);
        return true;
    }

    //Secondary function
    public Group getEntity(Integer id) {
        Optional<Group> optional = groupRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Group not found");
        }
        return optional.get();
    }
}
