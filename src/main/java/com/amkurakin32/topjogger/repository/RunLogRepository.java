package com.amkurakin32.topjogger.repository;

import com.amkurakin32.topjogger.model.db.RunLog;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RunLogRepository extends CrudRepository<RunLog, Long> {

  List<RunLog> findAllByUserId(Long userId);
}
