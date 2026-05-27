package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CController {
    static List<Course> courseList = new ArrayList<>();

    public CController() {
        courseList.add(new Course(202,"Full Stack",4));
        courseList.add(new Course(99,"Maths",3));
        courseList.add(new Course(101,"DBMS",4));
        courseList.add(new Course(249,"DS",2));
        courseList.add(new Course(304,"Java",3));
    }
    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Course> getCoursePathVariable(@PathVariable("id") int courseCode){
        for(Course c : courseList){
            if(c.getCourseCode()==courseCode){
                return new ResponseEntity<>(c,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("search")
    public ResponseEntity<Course> courseRequestParam(@RequestParam int id){
        for(Course c : courseList){
            if(c.getCourseCode()==id){
                return new ResponseEntity<>(c,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //stream usage with filter
    @PostMapping("newCourse")
    public ResponseEntity<String> createCourse(@RequestBody Course course){
        courseList.add(course);
        return new ResponseEntity<>("Course added",HttpStatus.OK);
    }
    @PutMapping("updateCourse/{courseCode}")
    public ResponseEntity<String> updateCourse(@PathVariable int courseCode, @RequestBody Course Ucourse){
        for(Course c : courseList) {
            if (c.getCourseCode() == courseCode) {
                c.setSubjectName(Ucourse.getSubjectName());
                c.setCredits(Ucourse.getCredits());
                return new ResponseEntity<>("Course Updated", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Course not fount", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("deleteCourse/{code}")
    public ResponseEntity<String> deleteCourse(@PathVariable int code){
        for(Course c : courseList){
            if(c.getCourseCode()==code) {
                courseList.remove(c);
                return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

            }
        }
        return new ResponseEntity<>("Course not fount", HttpStatus.NOT_FOUND);

    }

}
