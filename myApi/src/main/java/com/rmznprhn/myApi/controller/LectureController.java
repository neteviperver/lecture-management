package com.rmznprhn.myApi.controller;

import com.rmznprhn.myApi.entity.Lecture;
import com.rmznprhn.myApi.entity.User;
import com.rmznprhn.myApi.service.impl.LectureServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    private final LectureServiceImpl lectureService;
    public LectureController(LectureServiceImpl lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping
    ResponseEntity<Page<Lecture>> getUsers(@RequestParam(defaultValue ="0") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(lectureService.getAll(PageRequest.of(page, pageSize, Sort.by("id"))));
    }
    @PostMapping
    ResponseEntity<Lecture> create(@RequestBody Lecture lecture) {
        return ResponseEntity.ok(lectureService.save(lecture));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Lecture> deleteUser(@PathVariable Integer id) {
        lectureService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    ResponseEntity<Lecture> getUser(@PathVariable Integer id) {

        return ResponseEntity.ok(lectureService.getById(id));
    }
}
