package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Occupation;
import com.lambdaschool.foundation.repository.OccupationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "occupationService")
public class OccupationServiceImpl implements OccupationService {
    @Autowired
    private OccupationRepository occupationrepo;

    @Transactional
    @Override
    public Occupation save(Occupation occupation) {
        return occupationrepo.save(occupation);
    }
}
