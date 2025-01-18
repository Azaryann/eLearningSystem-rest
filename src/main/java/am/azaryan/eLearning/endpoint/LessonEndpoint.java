package am.azaryan.eLearning.endpoint;

import am.azaryan.eLearning.dto.lessonMapper.LessonDto;
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
    public ResponseEntity<List<LessonDto>> getLessons() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable int id) {
        LessonDto course = lessonService.findById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.ok().build();
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
    public ResponseEntity<LessonDto> updateCourse(@RequestBody UpdateLessonDto lesson) {
        LessonDto lessonDto = lessonService.editLesson(lesson);
        if (lessonDto != null) {
            return ResponseEntity.ok(lessonDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonDto> deleteCourseById(@PathVariable int id) {
        if (lessonService.deleteById(id)) {
            return ResponseEntity.ok(lessonService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
