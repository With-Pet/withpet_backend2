package com.withpet.backend.listener;


import com.withpet.backend.entity.Fad;
import com.withpet.backend.entity.QandA;
import com.withpet.backend.entity.repository.FadRepository;
import com.withpet.backend.util.BeanUtils;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.List;


public class FadEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        FadRepository fadRepository = BeanUtils.getBean(FadRepository.class);

        QandA qandA = (QandA) o;

        Fad fad = qandA.getFad();
        List<QandA> qandAList = fad.getQandAList();
        qandAList.add(qandA);
        fad.setQandAList(qandAList);
        fadRepository.save(fad);
    }
}
