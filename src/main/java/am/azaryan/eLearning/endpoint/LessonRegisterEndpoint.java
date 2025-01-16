package am.azaryan.eLearning.endpoint;

import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterResponse;
import am.azaryan.eLearning.service.LessonRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessonRegister")
public class LessonRegisterEndpoint {

    private final LessonRegisterService lessonRegisterService;

    @GetMapping()
    public ResponseEntity<List<LessonRegisterResponse>> getLessonRegister() {
        return ResponseEntity.ok(lessonRegisterService.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<LessonRegisterResponse> getLessonRegister(@PathVariable int studentId) {
        LessonRegisterResponse lessonRegisterResponse = lessonRegisterService.findByStudentId(studentId);
        if (lessonRegisterResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lessonRegisterResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<LessonRegisterResponse> studentRegister(@Valid @RequestBody LessonRegisterRequestDto lessonRegisterRequestDto) {
        LessonRegisterResponse lessonRegisterResponse = lessonRegisterService.save(lessonRegisterRequestDto);
        if (lessonRegisterResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lessonRegisterResponse);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudentLesson(@PathVariable int studentId) {
        if (lessonRegisterService.deleteByStudentID(studentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
