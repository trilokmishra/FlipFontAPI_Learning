package api.apps;

import com.monotype.api_utils.core.Task;
import com.monotype.api_utils.core.TaskManager;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;

/**
 * Created by kohlih on 27-06-2017.
 */
public class POSTAuthenticateExecuteRequestTask extends Task<POSTAuthenticateTaskManager> {

    MTLogger objMTLogger;

    public POSTAuthenticateExecuteRequestTask(MTLogger objMTLogger) {
        this.objMTLogger = objMTLogger;
    }

    @Override
    public void perform(TaskManager<POSTAuthenticateTaskManager> taskManager) {
        startTimer();
        taskManager.getRestDriver().getRequestObj().executeRequest(taskManager.getRestDriver());
        setTaskSuccessfull();
        endTimer();
    }
}