package com.product.services.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.product.services.dbo.DBSequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SequenceGeneratorTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private SequenceGenerator sequenceGenerator;

    public SequenceGeneratorTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateSequence() {
        DBSequence counter = new DBSequence();
        counter.setSeqNo(5L);

        when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class), any(Class.class)))
                .thenReturn(counter);

        Long result = sequenceGenerator.generateSequence("seqName");

        assertEquals(5L, result);
    }
}
