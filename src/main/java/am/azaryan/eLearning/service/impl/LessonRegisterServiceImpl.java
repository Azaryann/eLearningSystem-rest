package am.azaryan.eLearning.service.impl;

import am.azaryan.eLearning.mapper.LessonRegisterMapper;
import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterResponse;
import am.azaryan.eLearning.entity.lesson.LessonRegister;
import am.azaryan.eLearning.repository.LessonRegisterRepository;
import am.azaryan.eLearning.service.LessonRegisterService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonRegisterServiceImpl implements LessonRegisterService {

    private final LessonRegisterRepository lessonRegisterRepository;

    private final LessonRegisterMapper lessonRegisterMapper;

    @Override
    public LessonRegisterResponse findByStudentId(int id) {
        Optional<LessonRegister> lessonRegister = lessonRegisterRepository.findLessonRegisterByStudent(id);
        return lessonRegister.map(lessonRegisterMapper::lessonRegistrationResponse).orElse(null);
    }

    @Override
    public List<LessonRegisterResponse> findAll() {
        List<LessonRegister> lessonRegisters = lessonRegisterRepository.findAll();
        List<LessonRegisterResponse> result = new ArrayList<>();
        for (LessonRegister lessonRegister : lessonRegisters) {
            result.add(lessonRegisterMapper.lessonRegistrationResponse(lessonRegister));
        }
        return result;
    }

    @Override
    public LessonRegisterResponse save(LessonRegisterRequestDto lessonRegisterRequestDto) {
        Optional<LessonRegister> lessonRegistration = lessonRegisterRepository.findLessonRegisterByStudent(lessonRegisterRequestDto.getStudentId());
        if (lessonRegistration.isPresent()) {
            return null;
        }
        return lessonRegisterMapper.lessonRegistrationResponse(lessonRegisterMapper.toLessonRegistration(lessonRegisterRequestDto));
    }

    @Override
    public boolean deleteByStudentID(int id) {
        Optional<LessonRegister> lessonRegister = lessonRegisterRepository.findLessonRegisterByStudent(id);
        if (lessonRegister.isPresent()) {
            lessonRegisterRepository.delete(lessonRegister.get());
            return true;
        }
        return false;
    }

}
