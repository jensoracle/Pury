package com.nikitakozlov.pury.result;

import com.nikitakozlov.pury.ResultHandler;
import com.nikitakozlov.pury.profile.ProfilerId;
import com.nikitakozlov.pury.result.model.ProfileResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ResultManagerTest {

    private static final String KEY_1 = "key 1";
    private static final String KEY_2 = "key 2";

    @Mock
    ResultHandler resultHandler1;

    @Mock
    ResultHandler resultHandler2;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void dispatchResult_ShouldCallAllResultHandlers() {
        ProfilerId profilerId = mock(ProfilerId.class);
        ResultManager resultManager = new ResultManager();
        resultManager.addResultHandler(KEY_1, resultHandler1);
        resultManager.addResultHandler(KEY_2, resultHandler2);

        ProfileResult result = mock(ProfileResult.class);
        resultManager.dispatchResult(result, profilerId);
        verify(resultHandler1).handleResult(result, profilerId);
        verify(resultHandler2).handleResult(result, profilerId);
    }

    @Test
    public void dispatchResult_ShouldNotCallRemovedResultHandler() {
        ProfilerId profilerId = mock(ProfilerId.class);
        ResultManager resultManager = new ResultManager();
        resultManager.addResultHandler(KEY_1, resultHandler1);
        resultManager.addResultHandler(KEY_2, resultHandler2);
        resultManager.removeResultHandler(KEY_1);

        ProfileResult result = mock(ProfileResult.class);
        resultManager.dispatchResult(result, profilerId);
        verify(resultHandler1, never()).handleResult(result, profilerId);
        verify(resultHandler2).handleResult(result, profilerId);
    }
}