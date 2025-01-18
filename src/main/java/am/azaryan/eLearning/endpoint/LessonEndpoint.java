package am.azaryan.eLearning.endpoint;

import am.azaryan.eLearning.dto.lessonMapper.LessonDto;
import am.azaryan.eLearning.dto.lessonMapper.CreateLessonRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.UpdateLessonDto;
import am.azaryan.eLearning.dto.userMapper.UserDto;
import am.azaryan.eLearning.exceptions.ErrorResponse;
import am.azaryan.eLearning.response.Response;
import am.azaryan.eLearning.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonEndpoint {

    private final LessonService lessonService;

    @GetMapping()
    public ResponseEntity<List<LessonDto>> getLessons() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, LessonDto>> getLessonById(@PathVariable int id) {
        Response<ErrorResponse, LessonDto> lessonById = lessonService.findById(id);
        return ResponseEntity.ok(lessonById);
    }

    @PostMapping()
    public ResponseEntity<LessonDto> addLesson(@Valid @RequestBody CreateLessonRequestDto lesson) {
        LessonDto lessonDto = lessonService.save(lesson);
        if (lessonDto != null) {
            return ResponseEntity.ok(lessonDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping()
    public ResponseEntity<LessonDto> updateLesson(@RequestBody UpdateLessonDto lesson) {
        LessonDto lessonDto = lessonService.editLesson(lesson);
        if (lessonDto != null) {
            return ResponseEntity.ok(lessonDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonDto> deleteLessonById(@PathVariable int id) {
        if (lessonService.deleteById(id)) {
            return ResponseEntity.ok(lessonService.findById(id).getSuccessObject());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
