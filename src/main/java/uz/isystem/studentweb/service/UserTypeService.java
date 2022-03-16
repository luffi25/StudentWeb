package uz.isystem.studentweb.service;

import org.springframework.stereotype.Service;
import uz.isystem.studentweb.dto.UserTypeDto;
import uz.isystem.studentweb.exception.ServerBadRequestException;
import uz.isystem.studentweb.model.UserType;
import uz.isystem.studentweb.repository.UserTypeRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    //Main functions
    public UserTypeDto get(Integer id) {
        UserType entity = getEntity(id);
        UserTypeDto dto = new UserTypeDto();
        convertEntityToDto(entity, dto);
        return dto;
    }

    public UserType getEntityByName(String name){
        Optional<UserType> optional = userTypeRepository.findByNameAndDeletedAtIsNull(name);
        if (optional.isEmpty()){
            throw new ServerBadRequestException("Usertype not found");
        }
        return optional.get();
    }

    public List<UserTypeDto> getByName(String name) {
        List<UserType> userTypeList = userTypeRepository.findAllByName(name);
        if (userTypeList.isEmpty()) {
            throw new ServerBadRequestException("User type with this name not found");
        }
        List<UserTypeDto> userTypeDtoList = new LinkedList<>();
        for (UserType entity : userTypeList) {
            UserTypeDto userTypeDto = new UserTypeDto();
            convertEntityToDto(entity, userTypeDto);
            userTypeDtoList.add(userTypeDto);
        }
        return userTypeDtoList;
    }

    public UserTypeDto create(UserTypeDto dto) {
        UserType entity = new UserType();
        convertDtoToEntity(dto, entity);
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
        userTypeRepository.save(entity);
        convertEntityToDto(entity, dto);
        return dto;
    }

    public UserTypeDto update(Integer id, UserTypeDto dto) {
        UserType entity = getEntity(id);
        entity.setUpdatedAt(LocalDateTime.now());
        convertDtoToEntity(dto, entity);
//        oldEntity.setName(dto.getName());
//        oldEntity.setDisplayName(dto.getDisplayName());
        userTypeRepository.save(entity);
        convertEntityToDto(entity, dto);
        return dto;
    }

    public Boolean delete(Integer id) {
        UserType userType = getEntity(id);
        userType.setDeletedAt(LocalDateTime.now());
        //TODO: status set true
        userType.setStatus(false);
        userTypeRepository.save(userType);
        return true;
    }

    public List<UserTypeDto> findAll() {
        List<UserType> entityList = userTypeRepository.findAll();
        List<UserTypeDto> dtoList = new LinkedList<>();
        for (UserType userType : entityList) {
            UserTypeDto userTypeDto = new UserTypeDto();
            convertEntityToDto(userType, userTypeDto);
            dtoList.add(userTypeDto);
        }
        return dtoList;
    }

    //Secondary functions
    public UserType getEntity(Integer id) {
        Optional<UserType> optional = userTypeRepository.findById(id);

        if (optional.isEmpty() || optional.get().getDeletedAt() != null) {
            throw new ServerBadRequestException("User Type Not found");
        }

        return optional.get();
    }

    public void convertDtoToEntity(UserTypeDto userTypeDto, UserType userType) {
        userType.setName(userTypeDto.getName());
        userType.setDisplayName(userTypeDto.getDisplayName());
    }

    public void convertEntityToDto(UserType userType, UserTypeDto userTypeDto) {
        userTypeDto.setId(userType.getId());
        userTypeDto.setName(userType.getName());
        userTypeDto.setDisplayName(userType.getDisplayName());
        userTypeDto.setCreatedAt(userType.getCreatedAt());
        userTypeDto.setUpdatedAt(userType.getUpdatedAt());
        userTypeDto.setStatus(userType.getStatus());
    }
}
