package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;


import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }
    public Avatar findAvatar(Long studentId) {
        logger.info("Was invoked method for get avatar");
        return avatarRepository.findByStudentId(studentId).orElseThrow(EntityNotFoundException::new);
    }
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked method for update avatar");
        Student student = studentService.get(studentId);
        Path filePath = buildFilePath(student, avatarFile.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
    public Collection<Avatar> getPage(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for get page");
        return avatarRepository.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent();
    }
    private String getExtensions(String fileName) {
        logger.info("Was invoked method for get extension");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    private  Path buildFilePath(Student student, String fileName) {
        logger.info("Was invoked method for build file path");
        return Path.of(avatarsDir, student.getId() + "_" + student.getName() + "." + getExtensions(fileName));
    }
}
