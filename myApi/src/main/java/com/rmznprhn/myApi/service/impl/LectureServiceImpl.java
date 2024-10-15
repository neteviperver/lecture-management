package com.rmznprhn.myApi.service.impl;

import com.rmznprhn.myApi.common.GeneralException;
import com.rmznprhn.myApi.entity.Lecture;
import com.rmznprhn.myApi.repository.ILectureRepository;
import com.rmznprhn.myApi.service.ILectureService;
import liquibase.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements ILectureService {

    private final ILectureRepository lectureRepository;

    public LectureServiceImpl(ILectureRepository lectureRepository) {

        this.lectureRepository = lectureRepository;
    }

    @Override
    public Lecture save(Lecture lecture) {
        if (StringUtil.isEmpty(lecture.getName())){
            throw new GeneralException("Name cannot be empty");
        }
        if (lecture.getTeacher()==null){
            throw new GeneralException("Teacher cannot be empty");
        }

        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture getById(Integer id) {
        return lectureRepository.findById(id).orElseThrow(() -> new GeneralException("Lecture not found"));
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Page<Lecture> getAll(Pageable pageable) {
        return lectureRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!lectureRepository.existsById(id)){
            throw new GeneralException("Lecture not found");
        }
         lectureRepository.deleteById(id);

    }
}
