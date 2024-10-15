package com.rmznprhn.myApi.repository;

import com.rmznprhn.myApi.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILectureRepository extends JpaRepository<Lecture, Integer> {

}
