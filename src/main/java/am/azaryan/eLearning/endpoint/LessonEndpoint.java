package am.azaryan.eLearning.endpoint;

import am.azaryan.eLearning.dto.lessonMapper.LessonResponseDto;
import am.azaryan.eLearning.dto.lessonMapper.CreateLessonRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.UpdateLessonDto;
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
    public ResponseEntity<List<LessonResponseDto>> getLessons() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable int id) {
        LessonResponseDto course = lessonService.findById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<LessonResponseDto> addLesson(@Valid @RequestBody CreateLessonRequestDto lesson) {
        LessonResponseDto lessonResponseDto = lessonService.save(lesson);
        if (lessonResponseDto != null) {
            return ResponseEntity.ok(lessonResponseDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping()
    public ResponseEntity<LessonResponseDto> updateCourse(@RequestBody UpdateLessonDto lesson) {
        LessonResponseDto lessonResponseDto = lessonService.editLesson(lesson);
        if (lessonResponseDto != null) {
            return ResponseEntity.ok(lessonResponseDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonResponseDto> deleteCourseById(@PathVariable int id) {
        if (lessonService.deleteById(id)) {
            return ResponseEntity.ok(lessonService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
