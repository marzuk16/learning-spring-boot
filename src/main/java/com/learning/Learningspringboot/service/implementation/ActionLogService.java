package com.learning.Learningspringboot.service.implementation;

import com.learning.Learningspringboot.entity.ActionLog;
import com.learning.Learningspringboot.enums.Action;
import com.learning.Learningspringboot.enums.ModuleName;
import com.learning.Learningspringboot.repository.ActionLogRepository;
import com.learning.Learningspringboot.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ActionLogService extends BaseService {

    private final ActionLogRepository repository;

    public ActionLog publishActivity(Action action, ModuleName name, Long documentId, String comments, String data) {

        ActionLog log = new ActionLog();
        log.setAction(action);
        log.setModuleName(name);
        log.setDocumentId(documentId);
        log.setIpAddress(WebUtils.getCurrentRequest().getRequestURI());
        log.setComments(comments);
        log.setBackupData(data.getBytes(StandardCharsets.UTF_8));
        repository.save(log);
        return null;
    }
}
